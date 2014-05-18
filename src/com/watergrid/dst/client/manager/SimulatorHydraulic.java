

package com.watergrid.dst.client.manager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.watergrid.dst.client.model.DataElement_FlowVelHeadL;
import com.watergrid.dst.client.model.DataElement_PressureHead;
import com.watergrid.dst.client.model.HydraulicResults;
import com.watergrid.dst.client.model.Scenario;

public class SimulatorHydraulic extends Simulator{

	public SimulatorHydraulic (ScenarioSimulatorManager simulatorManager,String DMAname,Scenario scenario,int scenarioIndex,boolean isReRun){
		super(simulatorManager,DMAname,scenario,scenarioIndex,isReRun);
	}



	@Override
	protected void createRequestXML() {
		
		requestXML="";			
		requestXML="<sim_input>" + "\n";
		requestXML=requestXML+timeParametersXML+"\n";
		requestXML=requestXML+actionsXML+"\n";
		requestXML=requestXML+"</sim_input>";
	}

	@Override
	public void sendRequestToServer() {
		
		String url = serverUrl+DMAname+"/hydro";
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

		HydraulicResults results = new HydraulicResults();
		
		//for overall max and mins
		float overallMinPressure=99999999999f;					//put a very big number as initial value
		float overallMaxPressure=-99999999999f;					//put a very small number as initial value
		float overallMinHead=99999999999f;
		float overallMaxHead=-99999999999f;
		float overallMinFlow=99999999999f;
		float overallMaxFlow=-99999999999f;
		float overallMinVelocity=99999999999f;
		float overallMaxVelocity=-99999999999f;
		float overallMinHeadLoss=99999999999f;
		float overallMaxHeadLoss=-99999999999f;
		
		try{
		
			response=response.substring(response.indexOf("<h"));	//this is to remove that initial part of the message that is not part of the real XML
			
			//RootPanel.get("bottom").add(new Label(response));
			
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
				NodeList linksList = stepElement.getElementsByTagName("link");
				
				//iterate over each node creating the hashMap for this time step
				HashMap<String,DataElement_PressureHead> nodesResults = new HashMap<String,DataElement_PressureHead>();
				for(int j=0;j<nodesList.getLength();j++){
					
					Node nodeNode = nodesList.item(j);
					Element nodeElement=(Element)nodeNode;
					
					String id = nodeElement.getAttribute("id");
					Float p = Float.valueOf(nodeElement.getAttribute("p"));
					Float h = Float.valueOf(nodeElement.getAttribute("h"));
					
					nodesResults.put(id, new DataElement_PressureHead(p,h));
					
					//for overall Max and Mins
					overallMinPressure=Math.min(overallMinPressure,p );
					overallMaxPressure=Math.max(overallMaxPressure,p);
					overallMinHead=Math.min(overallMinHead,h);
					overallMaxHead=Math.max(overallMaxHead,h);

					
				}
				results.getNodesResults().add(nodesResults);
				
				//iterate over each link creating the hash map for this time step
				HashMap<String,DataElement_FlowVelHeadL> linksResults = new HashMap<String,DataElement_FlowVelHeadL>();
				for(int j=0;j<linksList.getLength();j++){
					
					Node linkNode = linksList.item(j);
					Element linkElement=(Element)linkNode;
					
					String id = linkElement.getAttribute("id");
					Float f = Float.valueOf(linkElement.getAttribute("f"));
					Float v = Float.valueOf(linkElement.getAttribute("v"));
					Float hl = Float.valueOf(linkElement.getAttribute("h"));
					
					linksResults.put(id, new DataElement_FlowVelHeadL(f, v, hl));
					
					//for overall max and mins
					overallMinFlow=Math.min(overallMinFlow,f);
					overallMaxFlow=Math.max(overallMaxFlow,f);
					overallMinVelocity=Math.min(overallMinVelocity,v);
					overallMaxVelocity=Math.max(overallMaxVelocity,v);
					overallMinHeadLoss=Math.min(overallMinHeadLoss,hl);
					overallMaxHeadLoss=Math.max(overallMaxHeadLoss,hl);
				}
				results.getLinksResults().add(linksResults);
				
				//get Max and Mins of the different variables

				Element pressureElement=(Element)stepElement.getElementsByTagName("pressure").item(0);					//it is the only element with that Tag, so its element 0
				Float maxPressure=Float.valueOf(pressureElement.getAttribute("max"));
				Float minPressure=Float.valueOf(pressureElement.getAttribute("min"));
				results.getMaxPressures().add(maxPressure);
				results.getMinPressures().add(minPressure);
				
				Element headElement=(Element)stepElement.getElementsByTagName("head").item(0);					//it is the only element with that Tag, so its element 0
				Float maxHead=Float.valueOf(headElement.getAttribute("max"));
				Float minHead=Float.valueOf(headElement.getAttribute("min"));
				results.getMaxHeads().add(maxHead);
				results.getMinHeads().add(minHead);
				
				Element flowElement=(Element)stepElement.getElementsByTagName("flow").item(0);					//it is the only element with that Tag, so its element 0
				Float maxFlow=Float.valueOf(flowElement.getAttribute("max"));
				Float minFlow=Float.valueOf(flowElement.getAttribute("min"));
				results.getMaxFlows().add(maxFlow);
				results.getMinFlows().add(minFlow);
				
				Element velocityElement=(Element)stepElement.getElementsByTagName("velocity").item(0);					//it is the only element with that Tag, so its element 0
				Float maxVelocity=Float.valueOf(velocityElement.getAttribute("max"));
				Float minVelocity=Float.valueOf(velocityElement.getAttribute("min"));
				results.getMaxVelocities().add(maxVelocity);
				results.getMinVelocities().add(minVelocity);
				
				Element headlossElement=(Element)stepElement.getElementsByTagName("headloss").item(0);					//it is the only element with that Tag, so its element 0
				Float maxHeadloss=Float.valueOf(headlossElement.getAttribute("max"));
				Float minHeadloss=Float.valueOf(headlossElement.getAttribute("min"));
				results.getMaxHeadloss().add(maxHeadloss);
				results.getMinHeadloss().add(minHeadloss);
			}
			results.setResultsTime(DateTimeFormat.getShortTimeFormat().format(new Date()));
			
			
			isOK=true;
			errorLog="NO Error";
			scenario.setHydroHasRun(true);

			//set overall max and mins to results
			results.setOverallMinPressure(overallMinPressure);
			results.setOverallMaxPressure(overallMaxPressure);
			results.setOverallMinHead(overallMinHead);
			results.setOverallMaxHead(overallMaxHead);
			results.setOverallMinFlow(overallMinFlow);
			results.setOverallMaxFlow(overallMaxFlow);
			results.setOverallMinVelocity(overallMinVelocity);
			results.setOverallMaxVelocity(overallMaxVelocity);
			results.setOverallMinHeadLoss(overallMinHeadLoss);
			results.setOverallMaxHeadLoss(overallMaxHeadLoss);
			
			//set the created Result object to the scenario
			scenario.setHydraulicResults(results);
			
		}catch (Exception e){
			isOK=false;
			errorLog="Partsing Error";
		
		} finally{
			callBackManager();
		}

	}
	
	
	private void callBackManager(){

			simulatorManager.informSimulationFinished(scenarioIndex, "hydraulic", isOK, errorLog,isReRun);
	}



}
