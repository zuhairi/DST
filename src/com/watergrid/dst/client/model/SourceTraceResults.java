package com.watergrid.dst.client.model;

import java.util.ArrayList;
import java.util.HashMap;

public class SourceTraceResults {

	String time; 	//time at which we got the results HH:MM
	
	//SOURCE TRACE %
	
	//number of seconds of each step. each position in the array represents a time step
	ArrayList<Long> timeSteps;
	
	//min and max of every variable. each position in the array represents a time step
	//this data comes in the results XML
	ArrayList<Float> minTracePct;			
	ArrayList<Float> maxTracePct;

	
	//overall Miax and Min
	//calculated in the application while parsing results. This is for convenience, because we usually need a lot this variables (e.g. summary, creating scales, etc.)
	float overallMinTracePct;
	float overallMaxTracePct;

	//the following ArrayLists contain the variables results of the links and nodes. Each element of the array list represents a time step.
	//each element is a HashMap that contains all the results of the time step, where the key is the componentID and the value is one of the custom created DataElement that store all the needed data
	
	ArrayList<HashMap<String,DataElement_TracePct>> nodesResults;
	
	public SourceTraceResults(){
		
		//initialize collections
		
		timeSteps = new ArrayList<Long>();
		minTracePct=new ArrayList<Float>();	
		maxTracePct=new ArrayList<Float>();
		
		nodesResults= new ArrayList<HashMap<String,DataElement_TracePct>>();		
	}
	
	
	//getters

	public ArrayList<Long> getTimeSteps() {
		return timeSteps;
	}

	public ArrayList<Float> getMinTracePct() {
		return minTracePct;
	}

	public ArrayList<Float> getMaxTracePct() {
		return maxTracePct;
	}

	public ArrayList<HashMap<String, DataElement_TracePct>> getNodesResults() {
		return nodesResults;
	}
	
	public String getResultsTime(){
		return time;
	}
	
	public void setResultsTime(String time){
		this.time=time;
	}


	//getters and setters for overall max and min variables

	
	public float getOverallMinTracePct() {
		return overallMinTracePct;
	}


	public float getOverallMaxTracePct() {
		return overallMaxTracePct;
	}


	public void setOverallMinTracePct(float overallMinTracePct) {
		this.overallMinTracePct = overallMinTracePct;
	}


	public void setOverallMaxTracePct(float overallMaxTracePct) {
		this.overallMaxTracePct = overallMaxTracePct;
	}
	

	
}
