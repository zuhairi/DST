package com.watergrid.dst.client.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.watergrid.dst.client.dataobjects.ComponentLevel;
import com.watergrid.dst.client.dataobjects.LinkHydraulicResult;
import com.watergrid.dst.client.dataobjects.NodeHydraulicResult;
import com.watergrid.dst.client.model.AgeResults;
import com.watergrid.dst.client.model.DMAmodel;
import com.watergrid.dst.client.model.DataElement_FlowVelHeadL;
import com.watergrid.dst.client.model.DataElement_PressureHead;
import com.watergrid.dst.client.model.HydraulicResults;

public class ReportCreatorHydraulic {
	
	
					///////////////////////////
					// HYDRO RESULTS SUMMARY
					//////////////////////////

	//this creates an array with a summary of the most important data of the results
	public static ArrayList<Float> createHydraulicResultSummary(HydraulicResults results) {

		// 0: min pressure
		// 1: max pressure
		// 2: min head
		// 3: max head
		// 4: min flow
		// 5: max flow
		// 6: min velocity
		// 7: max velocity
		// 8: min head loss
		// 9: max head loss
		ArrayList<Float> summary = new ArrayList<Float>();

		summary.add(results.getOverallMinPressure());
		summary.add(results.getOverallMaxPressure());
		summary.add(results.getOverallMinHead());
		summary.add(results.getOverallMaxHead());
		summary.add(results.getOverallMinFlow());
		summary.add(results.getOverallMaxFlow());
		summary.add(results.getOverallMinVelocity());
		summary.add(results.getOverallMaxVelocity());
		summary.add(results.getOverallMinHeadLoss());
		summary.add(results.getOverallMaxHeadLoss());

		return summary;
	}
	
					///////////////////////////
					// NODES RESULTS
					//////////////////////////
	
	//the objective of this method is to create a NodeHydraulicResult object for each node which contains data about it in the results, such as the max, min, and avg of each of the measured variables
	public static ArrayList<NodeHydraulicResult> createNodeHydraulicResults(HydraulicResults results){
		
		
		//Array we going to build and return with all the data
		ArrayList<NodeHydraulicResult> nodeReport = new ArrayList<NodeHydraulicResult>();
		
		//Get the results of the nodes
		ArrayList<HashMap<String,DataElement_PressureHead>> nodeResults = results.getNodesResults();
		
		//first get a list with all the Node IDs. To do this we can just get it from the results of the first time Step (or any time step). We get the list of KEYS of the hashMap
		Iterator<String> iter=nodeResults.get(0).keySet().iterator();
		String [] nodeIDs= new String[nodeResults.get(0).keySet().size()];
		int k=0;
		while(iter.hasNext()){
			nodeIDs[k]=iter.next();
			k++;
		}
		
		//get the number of steps in the simulation
		int numberOfSteps= nodeResults.size();
		
		
		//now, for each node get the Max, Min, and Avg of pressure and head by getting the values of each node in each step hashMap
		for(int i=0;i<nodeIDs.length;i++){
			
			float minPressure;
			float maxPressure;
			float avgPressure;
			float minHead;
			float maxHead;
			float avgHead;
			float head;
			float pressure;
			String id = nodeIDs[i];
			
			
			minPressure=nodeResults.get(0).get(id).getPressure();		//initialize variable for comparison in each time step
			maxPressure=nodeResults.get(0).get(id).getPressure();
			minHead=nodeResults.get(0).get(id).getHead();
			maxHead=nodeResults.get(0).get(id).getHead();
			avgPressure=0.0f;
			avgHead=0.0f;
			
			
			//for each time step extract all data about the current node by examining each hashMap that represent each time step
			for(int j=0;j<nodeResults.size();j++){
				
				pressure=nodeResults.get(j).get(id).getPressure();
				head=nodeResults.get(j).get(id).getHead();
				
				if(pressure<minPressure){
					minPressure=pressure;
				}
				if(pressure>maxPressure){
					maxPressure=pressure;
				}
				if(head<minHead){
					minHead=head;
				}
				if(head>maxHead){
					maxHead=head;
				}
				
				avgPressure=avgPressure+pressure;
				avgHead=avgHead+head;
			}
			
			avgPressure=avgPressure/numberOfSteps;
			avgHead=avgHead/numberOfSteps;
			
			//create data object and add it to the nodeReport
			
			nodeReport.add(new NodeHydraulicResult(id, minPressure, maxPressure, avgPressure, minHead, maxHead, avgHead));
		}
		return nodeReport;
	}
	
	
	
					///////////////////////////
					// LINKS RESULTS
					//////////////////////////
	
	public static ArrayList<LinkHydraulicResult> createLinkHydraulicResults(HydraulicResults results){
		
		//Array we going to build and return with all the data
		ArrayList<LinkHydraulicResult> linkReport = new ArrayList<LinkHydraulicResult>();
		
		//Get the results of the links
		ArrayList<HashMap<String,DataElement_FlowVelHeadL>> linkResults = results.getLinksResults();
		
		//first get a list with all the LINK IDs. To do this we can just get it from the results of the first time Step (or any time step). We get the list of KEYS of the hashMap
		Iterator<String> iter=linkResults.get(0).keySet().iterator();
		String [] linkIDs= new String[linkResults.get(0).keySet().size()];
		int k=0;
		while(iter.hasNext()){
			linkIDs[k]=iter.next();
			k++;
		}
		
		//get the number of steps in the simulation
		int numberOfSteps= linkResults.size();
		
		
		//now, for each Link get the Max, Min, and Avg of Flow, velocity, and headLoss by getting the values of each link in each step hashMap
		for(int i=0;i<linkIDs.length;i++){
			
			float minFlow;
			float maxFlow;
			float avgFlow;
			float minVel;
			float maxVel;
			float avgVel;
			float minHeadLoss;
			float maxHeadLoss;
			float avgHeadLoss;
			float flow;
			float vel;
			float headLoss;
			String id = linkIDs[i];
			
			minFlow=linkResults.get(0).get(id).getFlow();			//initialize variable for comparison in each time step
			maxFlow=linkResults.get(0).get(id).getFlow();
			minVel=linkResults.get(0).get(id).getVelocity();
			maxVel=linkResults.get(0).get(id).getVelocity();
			minHeadLoss=linkResults.get(0).get(id).getHeadLoss();
			maxHeadLoss=linkResults.get(0).get(id).getHeadLoss();
			
			avgFlow=0.0f;
			avgVel=0.0f;
			avgHeadLoss=0.0f;
			
			
			//for each time step extract all data about the current link by examining each hashMap that represent each time step
			for(int j=0;j<linkResults.size();j++){
				
				flow=linkResults.get(j).get(id).getFlow();
				vel=linkResults.get(j).get(id).getVelocity();
				headLoss=linkResults.get(j).get(id).getHeadLoss();
				
				if(flow<minFlow){
					minFlow=flow;
				}
				if(flow>maxFlow){
					maxFlow=flow;
				}
				
				if(vel<minVel){
					minVel=vel;
				}
				if(vel>maxVel){
					maxVel=vel;
				}
				
				if(headLoss<minHeadLoss){
					minHeadLoss=headLoss;
				}
				if(headLoss>maxHeadLoss){
					maxHeadLoss=headLoss;
				}
				
				avgFlow=avgFlow+flow;
				avgVel=avgVel+vel;
				avgHeadLoss=avgHeadLoss+headLoss;
			}
			
			avgFlow=avgFlow/numberOfSteps;
			avgVel=avgVel/numberOfSteps;
			avgHeadLoss=avgHeadLoss/numberOfSteps;
			
			//create data object and add it to the nodeReport
			linkReport.add(new LinkHydraulicResult(id, minFlow, maxFlow, avgFlow, minVel, maxVel, avgVel, minHeadLoss, maxHeadLoss, avgHeadLoss));

		}
		
		return linkReport;
	}
	
					
								///////////////////////////
								// SCALE CREATION
								//////////////////////////
	
	//this method determines the 5 scale levels for the given variable of a given scenario
	//this creates a data structure which is more convenient to use when we want to show the results on the maps. It is based on a scale level and on the index of the component
	public static ArrayList<Float> createScale(HydraulicResults results,String variable){
		
		
		//get min and max of the variable
		
		float min=0;
		float max=0;
		
		if(variable.equals("pressure")){
			
			// MIN Pressure
			min=results.getOverallMinPressure();
			// MAX Pressure
			max=results.getOverallMaxPressure();
			
		}else if (variable.equals("head")){
			
			// MIN Head
			min=results.getOverallMinHead();
			// MAX Head
			max=results.getOverallMaxHead();
			
		}else if(variable.equals("flow")){
			
			// MIN flow
			min=results.getOverallMinFlow();
			// MAX flow
			max=results.getOverallMaxFlow();
			
			
		}else if (variable.equals("velocity")){
			
			// MIN vel
			min=results.getOverallMinVelocity();
			// MAX vel
			max=results.getOverallMaxVelocity();
		}
		
		else if (variable.equals("headLoss")){
			
			// MIN headloss
			min=results.getOverallMinHeadLoss();
			// MAX Headloss
			max=results.getOverallMaxHeadLoss();
		}
		
		//create scale
		ArrayList<Float> scale = new ArrayList<Float>();
		float scaleStep=(max-min)/5;
		scale.add(min+scaleStep);
		scale.add(min+(2*scaleStep));
		scale.add(min+(3*scaleStep));
		scale.add(min+(4*scaleStep));
		scale.add(max);
		
		return scale;
	}
	
						///////////////////////////
						// NODE RESULTS BY SCALE LEVEL
						//////////////////////////
	
	
	//this method creates an ArrayList that represents each time step and the results of each component in terms of level based on a scale
	public static ArrayList<ComponentLevel> createNodeScaleLevelResults(HydraulicResults results,String variable,int timeStep,ComponentIdentifier compIdentifier){
		
		
		//first get the scale to be able to determine the level of each component	
		ArrayList<Float> scale = ReportCreatorHydraulic.createScale(results, variable);
		float level1= scale.get(0);
		float level2= scale.get(1);
		float level3= scale.get(2);
		float level4= scale.get(3);
		float level5= scale.get(4);
		
		
		 ArrayList<HashMap<String,DataElement_PressureHead>> nodeResults=results.getNodesResults();
		
			
			ArrayList<ComponentLevel> levelResults = new ArrayList<ComponentLevel>();		//ArrayList with level results of this time step
			
			HashMap<String,DataElement_PressureHead> timeStepResult = nodeResults.get(timeStep);	//get the hashMap with the results of this time step
			 
			Iterator<String> iter = timeStepResult.keySet().iterator();						//get all the component IDs in the hashMap, and iterate over each to get the information and create the object
			//iterate over each component
			while(iter.hasNext()){
				
				String nodeID=iter.next();
				String componentType=compIdentifier.getComponentType(nodeID);
				int componentIndex=compIdentifier.getComponentIndex(nodeID);
				float reading=0;
				int scaleLevel=0;
				
				//get reading of that component depending on type of variable
				if(variable.equals("pressure")){
					reading=timeStepResult.get(nodeID).getPressure();
				}else if(variable.equals("head")){
					reading=timeStepResult.get(nodeID).getHead();
				}
				
				//determine scale level
				if(reading<=level1){
					scaleLevel=1;
				}else if(reading>level1 && reading <= level2){
					scaleLevel=2;
				}else if(reading>level2 && reading <= level3){
					scaleLevel=3;
				}else if(reading>level3 && reading <= level4){
					scaleLevel=4;
				}else {
					scaleLevel=5;
				}
				
				
				//create object with data and add it to the ArrayList
				levelResults.add(new ComponentLevel(componentType, componentIndex, scaleLevel));

			}

		
		return levelResults;
	}
	
						///////////////////////////
						// LINK RESULTS BY SCALE LEVEL
						//////////////////////////
		
				
	//this method creates an ArrayList that represents each time step and the results of each component in terms of level based on a scale
	public static ArrayList<ComponentLevel> createLinkScaleLevelResults(HydraulicResults results, String variable,int timeStep,ComponentIdentifier compIdentifier){
	
		
		//first get the scale to be able to determine the level of each component	
		ArrayList<Float> scale = ReportCreatorHydraulic.createScale(results, variable);
		float level1= scale.get(0);
		float level2= scale.get(1);
		float level3= scale.get(2);
		float level4= scale.get(3);
		float level5= scale.get(4);
		
		
		 ArrayList<HashMap<String,DataElement_FlowVelHeadL>> linkResults=results.getLinksResults();
		
			
			ArrayList<ComponentLevel> levelResults = new ArrayList<ComponentLevel>();				//ArrayList with level results of this time step
			
			HashMap<String,DataElement_FlowVelHeadL> timeStepResult = linkResults.get(timeStep);	//get the hashMap with the results of this time step
			 
			Iterator<String> iter = timeStepResult.keySet().iterator();								//get all the component IDs in the hashMap, and iterate over each to get the information and create the object
			//iterate over each component
			while(iter.hasNext()){
				
				String nodeID=iter.next();
				String componentType=compIdentifier.getComponentType(nodeID);
				int componentIndex=compIdentifier.getComponentIndex(nodeID);
				float reading=0;
				int scaleLevel=0;
				
				//get reading of that component depending on type of variable
				if(variable.equals("flow")){
					reading=timeStepResult.get(nodeID).getFlow();
				}else if(variable.equals("velocity")){
					reading=timeStepResult.get(nodeID).getVelocity();
				}else if(variable.equals("headLoss")){
					reading=timeStepResult.get(nodeID).getHeadLoss();
				}
				
				//determine scale level
				if(reading<=level1){
					scaleLevel=1;
				}else if(reading>level1 && reading <= level2){
					scaleLevel=2;
				}else if(reading>level2 && reading <= level3){
					scaleLevel=3;
				}else if(reading>level3 && reading <= level4){
					scaleLevel=4;
				}else {
					scaleLevel=5;
				}
				
				
				//create object with data and add it to the ArrayList
				levelResults.add(new ComponentLevel(componentType, componentIndex, scaleLevel));
			}
			
		
		return levelResults;

	}
	
			///////////////////////////
			// COMPLETE DATA
			//////////////////////////
	
	
	public static ArrayList<Float> getComponentCompleteData(HydraulicResults result, String componentId,String variable){
	
		ArrayList<Float> data = new ArrayList<Float>();
		
		if(variable.equals("pressure")){
			for(int i=0;i<result.getNodesResults().size();i++){
				data.add(result.getNodesResults().get(i).get(componentId).getPressure());
			}
		}else if(variable.equals("head")){
			for(int i=0;i<result.getNodesResults().size();i++){
				data.add(result.getNodesResults().get(i).get(componentId).getHead());
			}
		}else if(variable.equals("flow")){
			for(int i=0;i<result.getLinksResults().size();i++){
				data.add(result.getLinksResults().get(i).get(componentId).getFlow());
			}
		}else if(variable.equals("velocity")){
			for(int i=0;i<result.getLinksResults().size();i++){
				data.add(result.getLinksResults().get(i).get(componentId).getVelocity());
			}
		}else if(variable.equals("headLoss")){
			for(int i=0;i<result.getLinksResults().size();i++){
				data.add(result.getLinksResults().get(i).get(componentId).getHeadLoss());
			}
		}
			
		
		
		return data;
	}

}
