package com.watergrid.dst.client.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ChemicalConResults {
	
	
	String time; 	//time at which we got the results HH:MM
	
	//CHEMICAL CONCENTRATION IN mg/L
	
	//number of seconds of each step. each position in the array represents a time step
	ArrayList<Long> timeSteps;
	
	//min and max of every variable. each position in the array represents a time step
	//this data comes in the results XML
	ArrayList<Float> minChemicalCon;			
	ArrayList<Float> maxChemicalCon;
	
	//overall Miax and Min
	//calculated in the application while parsing results. This is for convenience, because we usually need a lot this variables (e.g. summary, creating scales, etc.)
	float overallMinChemicalCon;
	float overallMaxChemicalCon;


	//the following ArrayLists contain the variables results of the links and nodes. Each element of the array list represents a time step.
	//each element is a HashMap that contains all the results of the time step, where the key is the componentID and the value is one of the custom created DataElement that store all the needed data
	
	ArrayList<HashMap<String,DataElement_ChemicalCon>> nodesResults;
	
	public ChemicalConResults(){
		
		//initialize collections
		
		timeSteps = new ArrayList<Long>();
		minChemicalCon=new ArrayList<Float>();	
		maxChemicalCon=new ArrayList<Float>();
		
		nodesResults= new ArrayList<HashMap<String,DataElement_ChemicalCon>>();		
	}

	
	//getters
	
	public ArrayList<Long> getTimeSteps() {
		return timeSteps;
	}

	public ArrayList<Float> getMinChemicalCon() {
		return minChemicalCon;
	}

	public ArrayList<Float> getMaxChemicalCon() {
		return maxChemicalCon;
	}

	public ArrayList<HashMap<String, DataElement_ChemicalCon>> getNodesResults() {
		return nodesResults;
	}
	
	public String getResultsTime(){
		return time;
	}
	
	public void setResultsTime(String time){
		this.time=time;
	}

	
	//getters and setters for overall max and min variables

	public float getOverallMinChemicalCon() {
		return overallMinChemicalCon;
	}


	public float getOverallMaxChemicalCon() {
		return overallMaxChemicalCon;
	}


	public void setOverallMinChemicalCon(float overallMinChemicalCon) {
		this.overallMinChemicalCon = overallMinChemicalCon;
	}


	public void setOverallMaxChemicalCon(float overallMaxChemicalCon) {
		this.overallMaxChemicalCon = overallMaxChemicalCon;
	}
	
	
	

	

}
