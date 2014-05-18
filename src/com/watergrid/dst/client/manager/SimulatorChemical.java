

package com.watergrid.dst.client.manager;

import java.util.Date;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.watergrid.dst.client.model.AgeResults;
import com.watergrid.dst.client.model.ChemicalConResults;
import com.watergrid.dst.client.model.DataElement_Age;
import com.watergrid.dst.client.model.DataElement_ChemicalCon;
import com.watergrid.dst.client.model.Scenario;

public class SimulatorChemical extends Simulator{

	public SimulatorChemical (ScenarioSimulatorManager simulatorManager,String DMAname,Scenario scenario,int scenarioIndex,boolean isReRun){
		super(simulatorManager,DMAname,scenario,scenarioIndex,isReRun);
	}

	@Override
	protected void createRequestXML() {
		requestXML="";
		requestXML=requestXML+"<quality_request>";
		requestXML=requestXML+timeParametersXML;
		requestXML=requestXML+"<quality>";
		requestXML=requestXML+"<type value=\"2\"/>";				//type=2 is chemical Simulation
		requestXML=requestXML+"<bulk value =\""+String.valueOf(scenario.getBulkCoefficient())+"\"/>";
		requestXML=requestXML+"<wall value=\""+String.valueOf(scenario.getWallCoefficient())+"\"/>";
		requestXML=requestXML+"<chemicalcons value=\""+String.valueOf(scenario.getChemicalCons())+"\"/>";
		requestXML=requestXML+"<node id=\""+scenario.getSourceNodeId()+"\"/>";
		requestXML=requestXML+"</quality>";
		requestXML=requestXML+actionsXML;
		requestXML=requestXML+"</quality_request>";
	}

	@Override
	public void sendRequestToServer() {
		
		String url = serverUrl+DMAname+"/quality";
		String type="POST";
		String content=requestXML;
		
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST,GWT.getHostPageBaseURL()+"xml.proxy");		//the request is made to the proxy, which it then extracts the url packed in the content and makes the real request to the web service server
		rb.setRequestData("url="+URL.encodeQueryString(url)+"&type="+type+"&content="+content); //esto es un formato que yo me invente que despues se lee en el proxy segun los nombres de los campos: url, type, content
		rb.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		rb.setCallback(new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				
				if(response.getStatusCode()==200){
					parseResults(response.getText());
				}
				else{
					errorLog="Error: "+ response.getStatusCode();
					isOK=false;
					callBackManager();
				}
			}
			@Override
			public void onError(Request request, Throwable exception) {
				errorLog="Server Error";
				isOK=false;
				callBackManager();
			}
		});
		
		try{
			rb.send();
		}
		catch(RequestException e){
			errorLog="Fetch Error";
			isOK=false;
			callBackManager();
		}
	}
	
	
	private void parseResults(String response){
		
		//this method parses the results and Creates the results object, and sets it to the corresponding scenario
		
		//RootPanel.get("bottom").add(new Label(response));

		ChemicalConResults results = new ChemicalConResults();
		
		//for overall max and mins
		float overallMinChemicalCon=99999999999f;					//put a very big number as initial value
		float overallMaxChemicalCon=-99999999999f;					//put a very small number as initial value
		
		try{
		
			response=response.substring(response.indexOf("<q"));	//this is to remove that initial part of the message that is not part of the real XML
			
			
			Document doc = XMLParser.parse(response);
			NodeList stepsList = doc.getElementsByTagName("step");
			
			//ITERATE OVER EACH TIME STEP
			for(int i=0;i<stepsList.getLength();i++){
				
				Node stepNode = stepsList.item(i);
				Element stepElement=(Element)stepNode;
				
				//time value
				Long time = Long.valueOf(stepElement.getAttribute("t"));
				results.getTimeSteps().add(time);
				
				NodeList nodesList = stepElement.getElementsByTagName("node");
				
				//iterate over each node creating the hashMap for this time step
				HashMap<String,DataElement_ChemicalCon> nodesResults = new HashMap<String,DataElement_ChemicalCon>();
				for(int j=0;j<nodesList.getLength();j++){
					
					Node nodeNode = nodesList.item(j);
					Element nodeElement=(Element)nodeNode;
					
					String id = nodeElement.getAttribute("id");
					Float chemicalCon = Float.valueOf(nodeElement.getAttribute("value"));
					
					nodesResults.put(id, new DataElement_ChemicalCon(chemicalCon));
					
					//for overall Max and Mins
					overallMinChemicalCon=Math.min(overallMinChemicalCon,chemicalCon);
					overallMaxChemicalCon=Math.max(overallMaxChemicalCon,chemicalCon);
				}
				results.getNodesResults().add(nodesResults);
				
				
				//get Max and Mins of the different variables

				Element pressureElement=(Element)stepElement.getElementsByTagName("range").item(0);					//it is the only element with that Tag, so its element 0
				Float maxChemicalCon=Float.valueOf(pressureElement.getAttribute("maxvalue"));
				Float minChemicalCon=Float.valueOf(pressureElement.getAttribute("minvalue"));
				results.getMaxChemicalCon().add(maxChemicalCon);
				results.getMinChemicalCon().add(minChemicalCon);	
			}
			results.setResultsTime(DateTimeFormat.getShortTimeFormat().format(new Date()));
			
			isOK=true;
			errorLog="NO Error";
			scenario.setChemicalHasRun(true);
			
			//set overall max and mins to results
			results.setOverallMinChemicalCon(overallMinChemicalCon);
			results.setOverallMaxChemicalCon(overallMaxChemicalCon);

			//set the created Result object to the scenario
			scenario.setChemicalConResults(results);

			
		}catch (Exception e){
			isOK=false;
			errorLog="Partsing Error";
		
		} finally{
			callBackManager();
		}
	}
	
	private void callBackManager(){

			simulatorManager.informSimulationFinished(scenarioIndex, "chemical", isOK, errorLog,isReRun);
	}

}