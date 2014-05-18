package com.watergrid.dst.client.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.watergrid.dst.client.dataobjects.AlertInfo;
import com.watergrid.dst.client.model.DataElement_TracePct;
import com.watergrid.dst.client.model.HydraulicResults;
import com.watergrid.dst.client.model.SourceTraceResults;

public class AlertCreatorSourceTrace {
	
	//acceptable ranges
	static final float TRACEPCT_TOP=10000;
	static final float TRACEPCT_BTTM=-10000;
	
	
	public static int getAlertCount(SourceTraceResults results){
		
		
		//for each component determine if any of the variables is outside the acceptable ranges
		
		//if a component has values outside the range in different variables, we count each as a separate alert. BUT we don't make any difference if the alert is high value or low, meaning that if a component for some reason presents both in the same variable we just count it as one

		
		int alertCount=0;
		
		/////////
		//NODES
		/////////
		
		ArrayList<HashMap<String,DataElement_TracePct>> nodeResults = results.getNodesResults();
		
		//first get a list with all the Node IDs. To do this we can just get it from the results of the first time Step (or any time step). We get the list of KEYS of the hashMap
		Iterator<String> iter=nodeResults.get(0).keySet().iterator();
		String [] nodeIDs= new String[nodeResults.get(0).keySet().size()];
		int k=0;
		while(iter.hasNext()){
			nodeIDs[k]=iter.next();
			k++;
		}
		int numberOfSteps= nodeResults.size(); 			//get the number of steps in the simulation
		
		
		//for each component iterate over all the time steps and check if there is any value out of the acceptable range.
		//each variable goes in a separate for loop so we can break out when we find the first occurrence of an alert of a variable
		
		for(int i=0;i<nodeIDs.length;i++){
			
			String id = nodeIDs[i];
			
			//trace pct
			for(int j=0;j<numberOfSteps;j++){
				
				float tracePct=nodeResults.get(j).get(id).getTracePct();
				
				if(tracePct>=TRACEPCT_TOP || tracePct<=TRACEPCT_BTTM){
					alertCount++;
					break;
				}
			}
			
		}

		return alertCount;
		
	}
	
	//////////////////////////////////////////////////
	
	
	public static ArrayList<AlertInfo> getAlerts(SourceTraceResults results){
		
		ArrayList<AlertInfo> alerts = new ArrayList<AlertInfo>();
		
		/////////
		//NODES
		/////////
		
		ArrayList<HashMap<String,DataElement_TracePct>> nodeResults = results.getNodesResults();
		
		//first get a list with all the Node IDs. To do this we can just get it from the results of the first time Step (or any time step). We get the list of KEYS of the hashMap
		Iterator<String> iter=nodeResults.get(0).keySet().iterator();
		String [] nodeIDs= new String[nodeResults.get(0).keySet().size()];
		int k=0;
		while(iter.hasNext()){
			nodeIDs[k]=iter.next();
			k++;
		}
		int numberOfSteps= nodeResults.size(); 			//get the number of steps in the simulation
		
		
		//for each component iterate over all the time steps and check if there is any value out of the acceptable range.
		//each variable goes in a separate for loop so we can break out when we find the first occurrence of an alert of a variable
		
		for(int i=0;i<nodeIDs.length;i++){
			
			String id = nodeIDs[i];
			
			boolean hasAlert=false;
			AlertInfo alert = new AlertInfo(id);			//create the object, but only add it to the list if the component really presents an alert
			
			
			//trace pct HIGH
			for(int j=0;j<numberOfSteps;j++){
				
				float tracePct=nodeResults.get(j).get(id).getTracePct();
				
				if(tracePct>=TRACEPCT_TOP){
					hasAlert=true;
					alert.addVariableHighValue("trace Pct");
					break;
				}
			}
			//trace pct LOW
			for(int j=0;j<numberOfSteps;j++){
				
				float tracePct=nodeResults.get(j).get(id).getTracePct();
				
				if(tracePct<=TRACEPCT_BTTM){
					hasAlert=true;
					alert.addVariableLowValue("trace Pct");
					break;
				}
			}
			
			//add alertInfo object if the component present alerts
			if(hasAlert){
				alerts.add(alert);
			}
			
		}
		
		return alerts;
	}

}
