package com.watergrid.dst.client.model;

import java.util.ArrayList;

public class Scenario {

	static int counter=0;					//static variable to count instances created and use it to create a unique ID
	int uniqueID;
	
	String name;
	int scenarioIndex;					//DELETE NOT NEEDED //position in the list of scenarios held by the model
	String startTime;					//"12:00"
	String startDate;					//
	int totalDuration;					//in hours
	int iterationLength;				//in minutes
	String sourceNodeId;
	float bulkCoefficient;
	float wallCoefficient;
	float chemicalCons;
		
	ArrayList<ScenarioAction> actions;
	
	boolean hydroHasRun;							//whenever we add a new action we have to change this to false because results are not valid anymore
	boolean ageHasRun;
	boolean sourceHasRun;
	boolean chemicalHasRun;
	
	
	HydraulicResults hydraulicResults;
	AgeResults ageResults;
	SourceTraceResults sourceTraceResults;
	ChemicalConResults chemicalConResults;
	
	////////////////
	//constructors
	////////////////
	
	public Scenario(){
		actions = new ArrayList<ScenarioAction>();
		hydroHasRun=false;
		ageHasRun=false;
		sourceHasRun=false;
		chemicalHasRun=false;
		uniqueID=counter;
		counter++;
	}
	
	
	/////////////
	//getters
	/////////////
	
	public int getUniqueID(){
		return uniqueID;
	}
	public String getName(){
		return name;
	}
	public int getScenarioIndex(){
		return scenarioIndex;
	}
	public String getStartTime(){
		return startTime;
	}
	public String getStartDate(){
		return startDate;
	}
	public int getTotalDuration(){
		return totalDuration;
	}
	public int getIterationLength(){
		return iterationLength;
	}
	public String getSourceNodeId(){
		return sourceNodeId;
	}
	public float getBulkCoefficient(){
		return bulkCoefficient;
	}
	public float getWallCoefficient(){
		return wallCoefficient;
	}
	public float getChemicalCons(){
		return chemicalCons;
	}
	public boolean getHydroHasRun(){
		return hydroHasRun;
	}
	public boolean getAgeHasRun(){
		return ageHasRun;
	}
	public boolean getSourceHasRun(){
		return sourceHasRun;
	}
	public boolean getChemicalHasRun(){
		return chemicalHasRun;
	}
	public ArrayList<ScenarioAction> getActions(){
		return actions;
	}
	public HydraulicResults getHydraulicResults(){
		return hydraulicResults;
	}
	public AgeResults getAgeResults(){
		return ageResults;
	}
	public SourceTraceResults getSourceTraceResults(){
		return sourceTraceResults;
	}
	public ChemicalConResults getChemicalConResults(){
		return chemicalConResults;
	}
	
	//////////////
	//setters
	//////////////
	
	public void setName(String name){
		this.name=name;
	}
	public void setScenarioIndex(int scenarioIndex){
		this.scenarioIndex=scenarioIndex;
	}
	public void setStartTime(String startTime){
		this.startTime=startTime;
	}
	public void setStartDate(String startDate){
		this.startDate=startDate;
	}
	public void setTotalDuration(int totalDuration){
		this.totalDuration=totalDuration;
	}
	public void setIterationLength(int iterationLength){
		this.iterationLength=iterationLength;
	}
	public void setSourceNodeId(String sourceNodeId){
		this.sourceNodeId=sourceNodeId;
	}
	public void setBulkCoefficient(float bulkCoefficient){
		this.bulkCoefficient=bulkCoefficient;
	}
	public void setWallCoefficient(float wallCoefficient){
		this.wallCoefficient=wallCoefficient;
	}
	public void setChemicalCons(float chemicalCons){
		this.chemicalCons=chemicalCons;
	}
	public void setHydroHasRun(boolean hydroHasRun){
		this.hydroHasRun=hydroHasRun;
	}
	public void setAgeHasRun(boolean ageHasRun){
		this.ageHasRun=ageHasRun;
	}
	public void setSourceHasRun(boolean sourceHasRun){
		this.sourceHasRun=sourceHasRun;
	}
	public void setChemicalHasRun(boolean chemicalHasRun){
		this.chemicalHasRun=chemicalHasRun;
	}
	
	public void setHydraulicResults(HydraulicResults hydraulicResults){
		this.hydraulicResults=hydraulicResults;
	}
	public void setAgeResults(AgeResults ageResults){
		this.ageResults=ageResults;
	}
	public void setSourceTraceResults(SourceTraceResults sourceTraceResults){
		this.sourceTraceResults=sourceTraceResults;
	}
	public void setChemicalConResults(ChemicalConResults chemicalConResults){
		this.chemicalConResults=chemicalConResults;
	}
	
	////////////////////////
	//for handling actions
	///////////////////////
	
	public void addAction(ScenarioAction action){
		actions.add(action);
	}
	public void addAction(String componentType,String componentId,int componentIndex,String componentStatus,String actionTime,String actionDate){
		ScenarioAction action = new ScenarioAction(componentType, componentId, componentIndex, componentStatus, actionTime, actionDate);
		actions.add(action);
	}
	public void removeAction(int actionIndex){
		actions.remove(actionIndex);				//when removing, the other elements shift one position
	}
	
	
	
	
}
