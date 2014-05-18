package com.watergrid.dst.client.model;

import java.util.ArrayList;
import java.util.HashMap;


public class HydraulicResults {

	
	String time; 	//time at which we got the results HH:MM
	
	//number of seconds of each step. each position in the array represents a time step
	ArrayList<Long> timeSteps;
	
	//min and max of every variable. each position in the array represents a time step
	//this data comes in the results XML
	ArrayList<Float> minPressures;	
	ArrayList<Float> maxPressures;
	ArrayList<Float> minHeads;
	ArrayList<Float> maxHeads;
	ArrayList<Float> minFlows;
	ArrayList<Float> maxFlows;
	ArrayList<Float> minVelocities;
	ArrayList<Float> maxVelocities;
	ArrayList<Float> minHeadloss;
	ArrayList<Float> maxHeadloss;
	
	//overall Miax and Min
	//calculated in the application while parsing results. This is for convenience, because we usually need a lot this variables (e.g. summary, creating scales, etc.)
	float overallMinPressure;
	float overallMaxPressure;
	float overallMinHead;
	float overallMaxHead;
	float overallMinFlow;
	float overallMaxFlow;
	float overallMinVelocity;
	float overallMaxVelocity;
	float overallMinHeadLoss;
	float overallMaxHeadLoss;
	

	
	//the following ArrayLists contain the variables results of the links and nodes. Each element of the array list represents a time step.
	//each element is a HashMap that contains all the results of the time step, where the key is the componentID and the value is one of the custom created DataElement that store all the needed data
	
	ArrayList<HashMap<String,DataElement_PressureHead>> nodesResults;
	ArrayList<HashMap<String,DataElement_FlowVelHeadL>> linksResults;
	
	public HydraulicResults(){
		
		//initialize all the collections
		timeSteps = new ArrayList<Long>();
		minPressures=new ArrayList<Float>();	
		maxPressures=new ArrayList<Float>();
		minHeads=new ArrayList<Float>();
		maxHeads=new ArrayList<Float>();
		minFlows=new ArrayList<Float>();
		maxFlows=new ArrayList<Float>();
		minVelocities=new ArrayList<Float>();
		maxVelocities=new ArrayList<Float>();
		minHeadloss=new ArrayList<Float>();
		maxHeadloss=new ArrayList<Float>();
		
		nodesResults= new ArrayList<HashMap<String,DataElement_PressureHead>>();
		linksResults= new ArrayList<HashMap<String,DataElement_FlowVelHeadL>>();
	}
	
	//getters

	public ArrayList<Long> getTimeSteps() {
		return timeSteps;
	}

	public ArrayList<Float> getMinPressures() {
		return minPressures;
	}

	public ArrayList<Float> getMaxPressures() {
		return maxPressures;
	}

	public ArrayList<Float> getMinHeads() {
		return minHeads;
	}

	public ArrayList<Float> getMaxHeads() {
		return maxHeads;
	}

	public ArrayList<Float> getMinFlows() {
		return minFlows;
	}

	public ArrayList<Float> getMaxFlows() {
		return maxFlows;
	}

	public ArrayList<Float> getMinVelocities() {
		return minVelocities;
	}

	public ArrayList<Float> getMaxVelocities() {
		return maxVelocities;
	}

	public ArrayList<Float> getMinHeadloss() {
		return minHeadloss;
	}

	public ArrayList<Float> getMaxHeadloss() {
		return maxHeadloss;
	}

	public ArrayList<HashMap<String, DataElement_PressureHead>> getNodesResults() {
		return nodesResults;
	}

	public ArrayList<HashMap<String, DataElement_FlowVelHeadL>> getLinksResults() {
		return linksResults;
	}

	public String getResultsTime(){
		return time;
	}
	
	public void setResultsTime(String time){
		this.time=time;
	}

	
	//getters and setters for overall max and min variables
	
	public float getOverallMinPressure() {
		return overallMinPressure;
	}

	public float getOverallMaxPressure() {
		return overallMaxPressure;
	}

	public float getOverallMinHead() {
		return overallMinHead;
	}

	public float getOverallMaxHead() {
		return overallMaxHead;
	}

	public float getOverallMinFlow() {
		return overallMinFlow;
	}

	public float getOverallMaxFlow() {
		return overallMaxFlow;
	}

	public float getOverallMinVelocity() {
		return overallMinVelocity;
	}

	public float getOverallMaxVelocity() {
		return overallMaxVelocity;
	}

	public float getOverallMinHeadLoss() {
		return overallMinHeadLoss;
	}

	public float getOverallMaxHeadLoss() {
		return overallMaxHeadLoss;
	}

	public void setOverallMinPressure(float overallMinPressure) {
		this.overallMinPressure = overallMinPressure;
	}

	public void setOverallMaxPressure(float overallMaxPressure) {
		this.overallMaxPressure = overallMaxPressure;
	}

	public void setOverallMinHead(float overallMinHead) {
		this.overallMinHead = overallMinHead;
	}

	public void setOverallMaxHead(float overallMaxHead) {
		this.overallMaxHead = overallMaxHead;
	}

	public void setOverallMinFlow(float overallMinFlow) {
		this.overallMinFlow = overallMinFlow;
	}

	public void setOverallMaxFlow(float overallMaxFlow) {
		this.overallMaxFlow = overallMaxFlow;
	}

	public void setOverallMinVelocity(float overallMinVelocity) {
		this.overallMinVelocity = overallMinVelocity;
	}

	public void setOverallMaxVelocity(float overallMaxVelocity) {
		this.overallMaxVelocity = overallMaxVelocity;
	}

	public void setOverallMinHeadLoss(float overallMinHeadLoss) {
		this.overallMinHeadLoss = overallMinHeadLoss;
	}

	public void setOverallMaxHeadLoss(float overallMaxHeadLoss) {
		this.overallMaxHeadLoss = overallMaxHeadLoss;
	}
	
	
	
}
