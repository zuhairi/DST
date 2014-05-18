package com.watergrid.dst.client.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.watergrid.dst.client.dataobjects.ComponentLevel;
import com.watergrid.dst.client.dataobjects.NodeAgeResult;
import com.watergrid.dst.client.model.AgeResults;
import com.watergrid.dst.client.model.DMAmodel;
import com.watergrid.dst.client.model.DataElement_Age;
import com.watergrid.dst.client.model.DataElement_PressureHead;

public class ReportCreatorAge {
	
								///////////////////////////
								// AGE RESULTS SUMMARY
								//////////////////////////

	public static ArrayList<Float> createAgeResultSummary(AgeResults results) {


		// 0: min Age
		// 1: max Age
		ArrayList<Float> summary = new ArrayList<Float>();

		summary.add(results.getOverallMinAge());
		summary.add(results.getOverallMaxAge());

		return summary;
	}
	
	
								///////////////////////////
								// NODES RESULTS
								//////////////////////////
	
	//the objective of this method is to create a NodeAgeResult object for each node which contains data about it in the results, such as the max, min, and avg of each of the measured variables
	
	public static ArrayList<NodeAgeResult> createNodeAgeResults(AgeResults results){
		
		
		//Array we going to build and return with all the data
		ArrayList<NodeAgeResult> nodeReport = new ArrayList<NodeAgeResult>();
		
		//Get the results of the nodes
		ArrayList<HashMap<String,DataElement_Age>> nodeResults = results.getNodesResults();
		
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
		
		
		//now, for each node get the Max, Min, and Avg of AGE by getting the values of each node in each step hashMap
		
		for(int i=0;i<nodeIDs.length;i++){
			
			float minAge;
			float maxAge;
			float avgAge;
			float age;
			String id = nodeIDs[i];
			
			
			minAge=nodeResults.get(0).get(id).getAge();		//initialize variable for comparison in each time step
			maxAge=nodeResults.get(0).get(id).getAge();
			avgAge=0.0f;
			
			//for each time step extract all data about the current node by examining each hashMap that represent each time step
			for(int j=0;j<nodeResults.size();j++){
				
				age=nodeResults.get(j).get(id).getAge();

				
				if(age<minAge){
					minAge=age;
				}
				if(age>maxAge){
					maxAge=age;
				}
				avgAge=avgAge+age;

			}
			
			avgAge=avgAge/numberOfSteps;

			
			//create data object and add it to the nodeReport
			
			nodeReport.add(new NodeAgeResult(id, minAge, maxAge, avgAge));

		}
		
		return nodeReport;

	}
	
	
					///////////////////////////
					// SCALE CREATION
					//////////////////////////

	//this method determines the 5 scale levels for the given variable of a given scenario
	//this creates a data structure which is more convenient to use when we want to show the results on the maps. It is based on a scale level and on the index of the component
	public static ArrayList<Float> createScale(AgeResults results,String variable){
		
		
		//get min and max of the variable
		
		float min=0;
		float max=0;
		
		if(variable.equals("age")){
			
			// MIN Age
			min = results.getOverallMinAge();
			// MAX Age
			max = results.getOverallMaxAge();
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
		public static ArrayList<ComponentLevel> createNodeScaleLevelResults(AgeResults results,String variable,int timeStep,ComponentIdentifier compIdentifier){
			
			//first get the scale to be able to determine the level of each component	
			ArrayList<Float> scale = ReportCreatorAge.createScale(results, variable);
			float level1= scale.get(0);
			float level2= scale.get(1);
			float level3= scale.get(2);
			float level4= scale.get(3);
			float level5= scale.get(4);
			
			
			 ArrayList<HashMap<String,DataElement_Age>> nodeResults=results.getNodesResults();
			
				
				ArrayList<ComponentLevel> levelResults = new ArrayList<ComponentLevel>();		//ArrayList with level results of this time step
				
				HashMap<String,DataElement_Age> timeStepResult = nodeResults.get(timeStep);			//get the hashMap with the results of this time step
				 
				Iterator<String> iter = timeStepResult.keySet().iterator();						//get all the component IDs in the hashMap, and iterate over each to get the information and create the object
				//iterate over each component
				while(iter.hasNext()){
					
					String nodeID=iter.next();
					String componentType=compIdentifier.getComponentType(nodeID);
					int componentIndex=compIdentifier.getComponentIndex(nodeID);
					float reading=0;
					int scaleLevel=0;
					
					//get reading of that component depending on type of variable
					if(variable.equals("age")){
						reading=timeStepResult.get(nodeID).getAge();
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
		
		
		public static ArrayList<Float> getComponentCompleteData(AgeResults result, String componentId,String variable){
			
			ArrayList<Float> data = new ArrayList<Float>();
			
			for(int i=0;i<result.getNodesResults().size();i++){
				data.add(result.getNodesResults().get(i).get(componentId).getAge());
			}
			return data;
		}

}
