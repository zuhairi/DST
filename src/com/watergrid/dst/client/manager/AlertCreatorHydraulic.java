package com.watergrid.dst.client.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.watergrid.dst.client.dataobjects.AlertInfo;
import com.watergrid.dst.client.model.DataElement_FlowVelHeadL;
import com.watergrid.dst.client.model.DataElement_PressureHead;
import com.watergrid.dst.client.model.HydraulicResults;

public class AlertCreatorHydraulic {

	
	//acceptable ranges
	static final float PRESSURE_TOP=77.20f;
	static final float PRESSURE_BTTM=-100000;
	
	static final float HEAD_TOP=10000;
	static final float HEAD_BTTM=-10000;
	
	static final float FLOW_TOP=10000;
	static final float FLOW_BTTM=-10000;
	
	static final float VELOCITY_TOP=10000;
	static final float VELOCITY_BTTM=-10000;
	
	static final float HEADLOSS_TOP=1000;
	static final float HEADLOSS_BTTM=-10000;

	
	public static int getAlertCount(HydraulicResults results){
		
		
		//for each component determine if any of the variables is outside the acceptable ranges
		
		//if a component has values outside the range in different variables, we count each as a separate alert. BUT we don't make any difference if the alert is high value or low, meaning that if a component for some reason presents both in the same variable we just count it as one
		
		int alertCount=0;
		
		/////////
		//NODES
		/////////
		
		ArrayList<HashMap<String,DataElement_PressureHead>> nodeResults = results.getNodesResults();
		
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
			
			//pressure 
			for(int j=0;j<numberOfSteps;j++){
				
				float pressure=nodeResults.get(j).get(id).getPressure();
				
				if(pressure>=PRESSURE_TOP || pressure<=PRESSURE_BTTM){
					alertCount++;
					break;
				}
			}
			
			//head 
			for(int j=0;j<numberOfSteps;j++){
				
				float head=nodeResults.get(j).get(id).getHead();
				
				if(head>=HEAD_TOP || head<=HEAD_BTTM){
					alertCount++;
					break;
				}
			}
			
		}
		
		/////////
		//LINKS
		/////////
		
		
		ArrayList<HashMap<String,DataElement_FlowVelHeadL>> linkResults = results.getLinksResults();
		
		//first get a list with all the link IDs. To do this we can just get it from the results of the first time Step (or any time step). We get the list of KEYS of the hashMap
		iter=linkResults.get(0).keySet().iterator();
		String [] linkIDs= new String[linkResults.get(0).keySet().size()];
		k=0;
		while(iter.hasNext()){
			linkIDs[k]=iter.next();
			k++;
		}
		numberOfSteps= linkResults.size(); 			//get the number of steps in the simulation
		
		
		//for each component iterate over all the time steps and check if there is any value out of the acceptable range.
		//each variable goes in a separate for loop so we can break out when we find the first occurrence of an alert of a variable
		
		for(int i=0;i<linkIDs.length;i++){
			
			String id = linkIDs[i];
			
			//flow
			for(int j=0;j<numberOfSteps;j++){
				
				float flow=linkResults.get(j).get(id).getFlow();
				
				if(flow>=FLOW_TOP || flow<=FLOW_BTTM){
					alertCount++;
					break;
				}
			}
			
			//velocity
			for(int j=0;j<numberOfSteps;j++){
				
				float velocity=linkResults.get(j).get(id).getVelocity();
				
				if(velocity>=VELOCITY_TOP || velocity<=VELOCITY_BTTM){
					alertCount++;
					break;
				}
			}
			
			//headLoss
			for(int j=0;j<numberOfSteps;j++){
				
				float headLoss=linkResults.get(j).get(id).getHeadLoss();
				
				if(headLoss>=HEADLOSS_TOP || headLoss<=HEADLOSS_BTTM){
					alertCount++;
					break;
				}
			}

			
		}
		
		return alertCount;
	}
	
	
	
	//////////////////////////////////////////////////
	
	
	public static ArrayList<AlertInfo> getAlerts(HydraulicResults results){
		
		ArrayList<AlertInfo> alerts = new ArrayList<AlertInfo>();
		
		//for each component determine if any of the variables is outside the acceptable ranges
		
		
		/////////
		//NODES
		/////////
		
		ArrayList<HashMap<String,DataElement_PressureHead>> nodeResults = results.getNodesResults();
		
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
			
			//each variable High and low is done separately because they can have BOTH (u never know!)
			
			//pressure HIGH
			for(int j=0;j<numberOfSteps;j++){
				
				float pressure=nodeResults.get(j).get(id).getPressure();
				
				if(pressure>=PRESSURE_TOP){
					hasAlert=true;
					alert.addVariableHighValue("pressure");
					break;
				}
			}
			//pressure LOW
			for(int j=0;j<numberOfSteps;j++){
				
				float pressure=nodeResults.get(j).get(id).getPressure();
				
				if(pressure<=PRESSURE_BTTM){
					hasAlert=true;
					alert.addVariableLowValue("pressure");
					break;
				}
			}
			
			//head HIGH
			for(int j=0;j<numberOfSteps;j++){
				
				float head=nodeResults.get(j).get(id).getHead();
				
				if(head>=HEAD_TOP){
					hasAlert=true;
					alert.addVariableHighValue("head");
					break;
				}
			}
			
			//head LOW
			for(int j=0;j<numberOfSteps;j++){
				
				float head=nodeResults.get(j).get(id).getHead();
				
				if(head<=HEAD_BTTM){
					hasAlert=true;
					alert.addVariableLowValue("head");
					break;
				}
			}
			
			//add alertInfo object if the component present alerts
			if(hasAlert){
				alerts.add(alert);
			}
			
		}
		
		/////////
		//LINKS
		/////////
		
		
		ArrayList<HashMap<String,DataElement_FlowVelHeadL>> linkResults = results.getLinksResults();
		
		//first get a list with all the link IDs. To do this we can just get it from the results of the first time Step (or any time step). We get the list of KEYS of the hashMap
		iter=linkResults.get(0).keySet().iterator();
		String [] linkIDs= new String[linkResults.get(0).keySet().size()];
		k=0;
		while(iter.hasNext()){
			linkIDs[k]=iter.next();
			k++;
		}
		numberOfSteps= linkResults.size(); 			//get the number of steps in the simulation
		
		
		//for each component iterate over all the time steps and check if there is any value out of the acceptable range.
		//each variable goes in a separate for loop so we can break out when we find the first occurrence of an alert of a variable
		
		for(int i=0;i<linkIDs.length;i++){
			
			String id = linkIDs[i];
			
			boolean hasAlert=false;
			AlertInfo alert = new AlertInfo(id);			//create the object, but only add it to the list if the component really presents an alert
			
			//flow HIGH
			for(int j=0;j<numberOfSteps;j++){
				
				float flow=linkResults.get(j).get(id).getFlow();
				
				if(flow>=FLOW_TOP){
					hasAlert=true;
					alert.addVariableHighValue("flow");
					break;
				}
			}
			//flow LOW
			for(int j=0;j<numberOfSteps;j++){
				
				float flow=linkResults.get(j).get(id).getFlow();
				
				if(flow<=FLOW_BTTM){
					hasAlert=true;
					alert.addVariableLowValue("flow");
					break;
				}
			}
			
			//velocity HIGH
			for(int j=0;j<numberOfSteps;j++){
				
				float velocity=linkResults.get(j).get(id).getVelocity();
				
				if(velocity>=VELOCITY_TOP){
					hasAlert=true;
					alert.addVariableHighValue("velocity");
					break;
				}
			}
			//velocity LOW
			for(int j=0;j<numberOfSteps;j++){
				
				float velocity=linkResults.get(j).get(id).getVelocity();
				
				if(velocity<=VELOCITY_BTTM){
					hasAlert=true;
					alert.addVariableLowValue("velocity");
					break;
				}
			}
			
			//headLoss HIGH
			for(int j=0;j<numberOfSteps;j++){
				
				float headLoss=linkResults.get(j).get(id).getHeadLoss();
				
				if(headLoss>=HEADLOSS_TOP){
					hasAlert=true;
					alert.addVariableHighValue("headLoss");
					break;
				}
			}
			//headLoss LOW
			for(int j=0;j<numberOfSteps;j++){
				
				float headLoss=linkResults.get(j).get(id).getHeadLoss();
				
				if(headLoss<=HEADLOSS_BTTM){
					hasAlert=true;
					alert.addVariableLowValue("headLoss");
					break;
				}
			}
			
			//add alertInfo object if the component present at least one alert
			if(hasAlert){
				alerts.add(alert);
			}

			
		}
		
		return alerts;
	}
	
}
