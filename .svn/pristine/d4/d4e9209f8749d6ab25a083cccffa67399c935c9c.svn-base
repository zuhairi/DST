package com.watergrid.dst.client.model;

import java.util.ArrayList;
import java.util.HashMap;

public class AgeResults {
	
	
	String time; 	//time at which we got the results HH:MM
	
	//AGE IN HOURS
	
	//number of seconds of each step. each position in the array represents a time step
	ArrayList<Long> timeSteps;
	
	//min and max of every variable. each position in the array represents a time step
	//this data comes in the results XML
	ArrayList<Float> minAges;			
	ArrayList<Float> maxAges;
	
	//overall Miax and Min
	//calculated in the application while parsing results. This is for convenience, because we usually need a lot this variables (e.g. summary, creating scales, etc.)
	float overallMinAge;
	float overallMaxAge;


	//the following ArrayLists contain the variables results of the links and nodes. Each element of the array list represents a time step.
	//each element is a HashMap that contains all the results of the time step, where the key is the componentID and the value is one of the custom created DataElement that store all the needed data
	
	ArrayList<HashMap<String,DataElement_Age>> nodesResults;


	public AgeResults(){
		
		//initialize collections
		
		timeSteps = new ArrayList<Long>();
		minAges=new ArrayList<Float>();	
		maxAges=new ArrayList<Float>();
		
		nodesResults= new ArrayList<HashMap<String,DataElement_Age>>();		
	}
	
	
	//getters
	
	public ArrayList<Long> getTimeSteps() {
		return timeSteps;
	}


	public ArrayList<Float> getMinAges() {
		return minAges;
	}


	public ArrayList<Float> getMaxAges() {
		return maxAges;
	}


	public ArrayList<HashMap<String, DataElement_Age>> getNodesResults() {
		return nodesResults;
	}
	
	public String getResultsTime(){
		return time;
	}
	
	public void setResultsTime(String time){
		this.time=time;
	}


	//getters and setters for overall max and min variables
	
	public float getOverallMinAge() {
		return overallMinAge;
	}


	public float getOverallMaxAge() {
		return overallMaxAge;
	}


	public void setOverallMinAge(float overallMinAge) {
		this.overallMinAge = overallMinAge;
	}


	public void setOverallMaxAge(float overallMaxAge) {
		this.overallMaxAge = overallMaxAge;
	}
	
	

	
	
	
}
