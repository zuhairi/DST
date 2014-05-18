//this class manages all the things related with the model. It is the one class that has reference to the instance of the model.
//this class is created by the AppPresenter and it is used by the VIEWpresenters to interact with the model
package com.watergrid.dst.client.manager;

import java.util.ArrayList;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.watergrid.dst.client.dataobjects.AlertInfo;
import com.watergrid.dst.client.dataobjects.ComponentLevel;
import com.watergrid.dst.client.dataobjects.LinkHydraulicResult;
import com.watergrid.dst.client.dataobjects.NodeAgeResult;
import com.watergrid.dst.client.dataobjects.NodeChemicalResult;
import com.watergrid.dst.client.dataobjects.NodeHydraulicResult;
import com.watergrid.dst.client.dataobjects.NodeSourceTraceResult;
import com.watergrid.dst.client.dataobjects.ResultsSummary;
import com.watergrid.dst.client.model.DMAmodel;
import com.watergrid.dst.client.model.Scenario;
import com.watergrid.dst.client.model.Junction;
import com.watergrid.dst.client.model.Piping;
import com.watergrid.dst.client.model.Reservoir;
import com.watergrid.dst.client.model.Valve;
import com.watergrid.dst.client.presenter.AppPresenter;



public class ModelManager {
	
	//appPresenter
	AppPresenter appPresenter;	//for callBacks when asynchronous processes are finished
	String DMAname;				//keep track of the DMAname for further actions, like simulations
	//model
	DMAmodel model=null;
	//helpers
	ModelFactory modelFactory;
	ScenarioSimulatorManager scenarioSimulatorManager;
	ComponentIdentifier compIndentifier;


	
	public ModelManager(AppPresenter appPresenter){
		
		this.appPresenter=appPresenter;
		//instantiate helper Classes
		modelFactory = new ModelFactory(this);
		scenarioSimulatorManager = new ScenarioSimulatorManager(this);
	}
	
	
	public DMAmodel getModel(){
		return model; 				//presenters that want to use the model have to get it from this ModelManager. They use it to read information. To modify or add they do it via this model manager
	}
	
	
	//the following methods are called by the AppPresenter or by the view presenter directly (they get the modelManager instance via the AppPrsenter)
	
	
												//////////////////////////////////////////////
												//////////////////////////////////////////////
												//											//
												// 			MODEL MANIPULATION				//
												//											//
												//////////////////////////////////////////////
												//////////////////////////////////////////////

	
	
			/////////////////////////////
			///   MODEL CREATION
			/////////////////////////////
	
	//this method requests the ModelFactory to create a model using the DMAname given
	public void createModel(String DMAname){
		this.DMAname=DMAname;
		modelFactory.createModel(DMAname);
	}
	
	//method called by ModelFactory when the model creation has been finished
	public void createModel_callBack(DMAmodel model,boolean isCreationSuccessfull, String errorLog){
		if (isCreationSuccessfull){
			this.model=model;
			appPresenter.informLoadingModelFinished(true,errorLog);	//to inform loading has been finished Correctly
			compIndentifier = new ComponentIdentifier(model);	// create the component identifier with all the model information
		}
		else{
			appPresenter.informLoadingModelFinished(false,errorLog);//to inform loading has been finished not successfully
		}
	}
	
			//////////////////////////////////////
			///   SCENARIO CREATION (and adds it to model)
			//////////////////////////////////////

	//this methods are called by ScenarioManager presenter
	public void createScenario(String name,String startTime,int totalDuration,int iterationLength,String sourceNodeId,float bulkCoeff,float wallCoeff,float chemicalCons ){
		Scenario scenario = new Scenario();
		scenario.setName(name);
		scenario.setStartTime(startTime);
		scenario.setTotalDuration(totalDuration);
		scenario.setIterationLength(iterationLength);
		scenario.setSourceNodeId(sourceNodeId);
		scenario.setBulkCoefficient(bulkCoeff);
		scenario.setWallCoefficient(wallCoeff);
		scenario.setChemicalCons(chemicalCons);
		model.addScenario(scenario);
	}
	
			//////////////////////////////////////
			///   SCENARIO REMOVER 
			//////////////////////////////////////

	//this methods are called by ScenarioManager presenter
	//delete by index in the list of scenario or by the reference of the scenario object itself
	public void deleteScenario(int scenarioIndex){
		model.removeScenario(scenarioIndex);
	}
	public void deleteScenario(Scenario scenario){
		model.removeScenario(scenario);
	}
	
			////////////////////////////////////////
			//  SCENARIO EDIT
			////////////////////////////////////////
	
	public void editScenario(int indexScenarioBeingEdited,String name,String startTime,int totalDuration,int iterationLength,String sourceNodeId,float bulkCoeff,float wallCoeff,float chemicalCons){
		
		Scenario editingScenario=model.getScenarios().get(indexScenarioBeingEdited);
		editingScenario.setName(name);
		editingScenario.setStartTime(startTime);
		editingScenario.setTotalDuration(totalDuration);
		editingScenario.setIterationLength(iterationLength);
		editingScenario.setSourceNodeId(sourceNodeId);
		editingScenario.setBulkCoefficient(bulkCoeff);
		editingScenario.setWallCoefficient(wallCoeff);
		editingScenario.setChemicalCons(chemicalCons);
	}
	
			////////////////////////////////////////
			//  ACTION CREATION
			////////////////////////////////////////
	
	public void addAction(int scenarioIndex,String componentType, String componentId,int componentIndex, String componentStatus, String actionTime,String actionDate){
		model.getScenarios().get(scenarioIndex).addAction(componentType, componentId, componentIndex, componentStatus, actionTime, actionDate);
	}
	
			////////////////////////////////////////
			//  ACTION REMOVER
			////////////////////////////////////////
	
	public void deleteAction(int scenarioIndex,int actionIndex){
		model.getScenarios().get(scenarioIndex).removeAction(actionIndex);
	}
	
	
			////////////////////////////////////////
			//  SCENARIO SIMULATION 
			////////////////////////////////////////
	
	//this is done using the ScenarioSimulatorManager helper class. It delegates the task to that class.
	//isReRun=false because it a manual simualtion
	public void runSimulation(int scenarioIndex, String simulationType){
		
		//we send the scenario object so it can get all the information from it and then add the results to it. We also send the index so it can inform us back when everything is done and we know which scenario it was
		Scenario scenario=model.getScenarios().get(scenarioIndex);
		scenarioSimulatorManager.runSimulation(DMAname,scenario,scenarioIndex, simulationType,false);
	}
	
	
	//this method is called by the Simulator manager informing when the simulation has finished and results are ready 
	//callBack
	//WARNING: the index is not valid if while the results are ready a scenario is deleted, because indexes change
	//in case we see this is an issue, replace all functionality of index with scenario's uniqueID
	public void informSimulationFinished(int scenarioIndex, String simulationType,boolean isOK, String errorLog,boolean isReRun){
			//inform app presenter that simulation has finished
			appPresenter.informSimulationFinished(scenarioIndex,model.getScenarios().get(scenarioIndex).getName(), simulationType, isOK, errorLog);
	}
	
	
			////////////////////////////////////////
			//  RE-RUN ALL SIMULATION 
			////////////////////////////////////////
	
	public void reRunAll(){
		scenarioSimulatorManager.reRunAll(model, DMAname);
	}
	
	//callBack when rerun has finished and send an alert report
	public void informSimulationFinishedReRun(){
		
		//create report of alerts iterating over all existing results
		ArrayList<String> report = new ArrayList<String>();
		for (int i=0;i<model.getScenarios().size();i++){
			
			Scenario scenario = model.getScenarios().get(i);
			
			if(scenario.getHydroHasRun()==true){
				report = updateReport(report, i, "hydraulic");
			}
			if(scenario.getAgeHasRun()==true){
				report = updateReport(report, i, "age");
			}
			if(scenario.getSourceHasRun()==true){
				report = updateReport(report, i, "sourceTrace");
			}
			if(scenario.getChemicalHasRun()==true){
				report = updateReport(report, i, "chemical");
			}
		}
		
		//inform appPresenter that reRun has finish sending alert report
		appPresenter.informSimulationFinishedReRun(report);
	}
	
	//this method just checks if a simulation results has alerts, and if it does it adds a report item to the report list indicating simulation type, scenario name and number of alerts
	private ArrayList<String> updateReport(ArrayList<String> report,int scenarioIndex,String simulationType){
		int alertCount = this.getAlertCount(scenarioIndex, simulationType);
		if(alertCount>0){
			report.add(model.getScenarios().get(scenarioIndex).getName()+" - "+ simulationType + " : " + alertCount +" alert(s)");
		}
		return report;
	}
	
	


	
												//////////////////////////////////////////////
												//////////////////////////////////////////////
												//											//
												// 			INFORMATION GENERATION			//
												//											//
												//////////////////////////////////////////////
												//////////////////////////////////////////////

	
			//////////////////////////////////////
			/// RESULTS SUMMARY
			//////////////////////////////////////
	
	//this method creates a list of all the available results (each represented by a ResultSummary object)
	public ArrayList<ResultsSummary> createResultsSummary(){
		return ReportCreatorGeneral.createResultsSummary(model);
	}
	
			//////////////////////////////////////
			/// RESULTS SUMMARY  OF A GIVEN SIMULATION using ReportCreator helper classes
			//////////////////////////////////////
	
	//this methods use the ReportCreator classes to create arrayLists with summary data of each type of experiment
	
	public ArrayList<Float> createHydraulicResultSummary(int scenarioIndex){
		return ReportCreatorHydraulic.createHydraulicResultSummary(model.getScenarios().get(scenarioIndex).getHydraulicResults());
	}
	
	public ArrayList<Float> createAgeResultSummary(int scenarioIndex){
		return ReportCreatorAge.createAgeResultSummary(model.getScenarios().get(scenarioIndex).getAgeResults());
	}
	
	public ArrayList<Float> createSourceTraceResultSummary(int scenarioIndex){
		return ReportCreatorSourceTrace.createSourceTraceResultSummary(model.getScenarios().get(scenarioIndex).getSourceTraceResults());
	}
	
	public ArrayList<Float> createChemicalResultSummary(int scenarioIndex){
		return ReportCreatorChemical.createChemicalResultSummary(model.getScenarios().get(scenarioIndex).getChemicalConResults());
	}
	
	
			//////////////////////////////////////
			/// DETAILED RESULTS FOR COMPONENTS using ReportCreator helper classes
			//////////////////////////////////////
	
	//this methods use the ReportCreator classes to create arraylists of custom dataGrid objects with the results of each node or link 
	
	public ArrayList<NodeHydraulicResult> createNodeHydraulicResults(int scenarioIndex){
		return ReportCreatorHydraulic.createNodeHydraulicResults(model.getScenarios().get(scenarioIndex).getHydraulicResults());
	}
	
	public ArrayList<LinkHydraulicResult> createLinkHydraulicResults(int scenarioIndex){
		return ReportCreatorHydraulic.createLinkHydraulicResults(model.getScenarios().get(scenarioIndex).getHydraulicResults());
	}
	
	
	public ArrayList<NodeAgeResult> createNodeAgeResults(int scenarioIndex){
		return ReportCreatorAge.createNodeAgeResults(model.getScenarios().get(scenarioIndex).getAgeResults());
	}
	
	public ArrayList<NodeSourceTraceResult> createNodeSourceTraceResults(int scenarioIndex){
		return ReportCreatorSourceTrace.createNodeSourceTraceResults(model.getScenarios().get(scenarioIndex).getSourceTraceResults());
	}
	
	public ArrayList<NodeChemicalResult> createNodeChemicalConResults(int scenarioIndex){
		return ReportCreatorChemical.createNodeChemicalResults(model.getScenarios().get(scenarioIndex).getChemicalConResults());
	}
	
			//////////////////////////////////////
			///	COMPLETE DATA OF A COMPONENT
			//////////////////////////////////////
	
	public ArrayList<Float> getComponentCompleteData(int scenarioIndex,String simulationType,String componentId,String variable){
		
		
		if (simulationType.equals("hydraulic")){
			return ReportCreatorHydraulic.getComponentCompleteData(model.getScenarios().get(scenarioIndex).getHydraulicResults(), componentId, variable);
		}else if (simulationType.equals("age")){
			return ReportCreatorAge.getComponentCompleteData(model.getScenarios().get(scenarioIndex).getAgeResults(), componentId, variable);
		}else if (simulationType.equals("sourceTrace")){
			return ReportCreatorSourceTrace.getComponentCompleteData(model.getScenarios().get(scenarioIndex).getSourceTraceResults(), componentId, variable);
		}else if (simulationType.equals("chemical")){
			return ReportCreatorChemical.getComponentCompleteData(model.getScenarios().get(scenarioIndex).getChemicalConResults(), componentId, variable);
		}
		return null;

	}
			
			//////////////////////////////////////
			///  TIME STEPS string creator 00:00
			//////////////////////////////////////
	
	public ArrayList<String> getTimeSteps(int scenarioIndex){
		return TimeStepCreator.getTimeSteps(model, scenarioIndex);
	}
	
	
			//////////////////////////////////////
			///  SCALE CREATION
			//////////////////////////////////////
	
	//this method is for getting the scale values (5 level scale)
	public ArrayList<Float> createScale(int scenarioIndex,String simulationType,String variable){
		
		if (simulationType.equals("hydraulic")){
			return ReportCreatorHydraulic.createScale(model.getScenarios().get(scenarioIndex).getHydraulicResults(),variable);
		}else if (simulationType.equals("age")){
			return ReportCreatorAge.createScale(model.getScenarios().get(scenarioIndex).getAgeResults(),variable);
		}else if (simulationType.equals("sourceTrace")){
			return ReportCreatorSourceTrace.createScale(model.getScenarios().get(scenarioIndex).getSourceTraceResults(),variable);
		}else if (simulationType.equals("chemical")){
			return ReportCreatorChemical.createScale(model.getScenarios().get(scenarioIndex).getChemicalConResults(),variable);
		}
		return null;
	}
	
				/////////////////////////////////////////////////
				///  RESULTS BY SCALE LEVEL
				///////////////////////////////////////////////
	
	
	//this is for creating ArrayLists that represents the results of each component in each time step in terms of the level in the scale.
	//the results are stored in a object structure that is more convenient to use when we want to show results on the map
	public ArrayList<ComponentLevel> createNodeScaleLevelResults(int scenarioIndex,String simulationType,String variable,int timeStep){
		
		if (simulationType.equals("hydraulic")){
			return ReportCreatorHydraulic.createNodeScaleLevelResults(model.getScenarios().get(scenarioIndex).getHydraulicResults(),variable,timeStep,compIndentifier);
		}else if (simulationType.equals("age")){
			return ReportCreatorAge.createNodeScaleLevelResults(model.getScenarios().get(scenarioIndex).getAgeResults(),variable,timeStep,compIndentifier);
		}else if (simulationType.equals("sourceTrace")){
			return ReportCreatorSourceTrace.createNodeScaleLevelResults(model.getScenarios().get(scenarioIndex).getSourceTraceResults(),variable,timeStep,compIndentifier);
		}else if (simulationType.equals("chemical")){
			return ReportCreatorChemical.createNodeScaleLevelResults(model.getScenarios().get(scenarioIndex).getChemicalConResults(),variable,timeStep,compIndentifier);
		}
		return null;
	}
	
	
	//this is for creating ArrayLists that represents the results of each component in each time step in terms of the level in the scale
	public ArrayList<ComponentLevel> createLinkScaleLevelResults(int scenarioIndex,String simulationType,String variable,int timeStep){
		
		if (simulationType.equals("hydraulic")){
			return ReportCreatorHydraulic.createLinkScaleLevelResults(model.getScenarios().get(scenarioIndex).getHydraulicResults(),variable,timeStep,compIndentifier);
		}
		return null;
	}
	
	
			/////////////////////////////////////////////////
			///  ALERTS
			///////////////////////////////////////////////
	
	public int getAlertCount(int scenarioIndex, String simulationType){
		
		if (simulationType.equals("hydraulic")){
			return AlertCreatorHydraulic.getAlertCount(model.getScenarios().get(scenarioIndex).getHydraulicResults());
		}else if (simulationType.equals("age")){
			return AlertCreatorAge.getAlertCount(model.getScenarios().get(scenarioIndex).getAgeResults());
		}else if (simulationType.equals("sourceTrace")){
			return AlertCreatorSourceTrace.getAlertCount(model.getScenarios().get(scenarioIndex).getSourceTraceResults());
		}else if (simulationType.equals("chemical")){
			return AlertCreatorChemical.getAlertCount(model.getScenarios().get(scenarioIndex).getChemicalConResults());
		}
		return 99999;
		
	}
	
	
	public ArrayList<AlertInfo> getAlerts(int scenarioIndex, String simulationType){
		
		if (simulationType.equals("hydraulic")){
			return AlertCreatorHydraulic.getAlerts(model.getScenarios().get(scenarioIndex).getHydraulicResults());
		}else if (simulationType.equals("age")){
			return AlertCreatorAge.getAlerts(model.getScenarios().get(scenarioIndex).getAgeResults());
		}else if (simulationType.equals("sourceTrace")){
			return AlertCreatorSourceTrace.getAlerts(model.getScenarios().get(scenarioIndex).getSourceTraceResults());
		}else if (simulationType.equals("chemical")){
			return AlertCreatorChemical.getAlerts(model.getScenarios().get(scenarioIndex).getChemicalConResults());
		}
		return null;
		
	}
	
	
	
			//////////////////////////////////////
			///  COMPONENT ID LISTS CREATOR
			//////////////////////////////////////
	
	
	public ArrayList<String> createJunctionsIdList(){
		return ReportCreatorGeneral.createJunctionsIdList(model);		
	}
	
	public ArrayList<String> createPipesIdList(){
		return ReportCreatorGeneral.createPipesIdList(model);		
	}
	
	public ArrayList<String> createReservoirsIdList(){
		return ReportCreatorGeneral.createReservoirsIdList(model);		
	}
	
	public ArrayList<String> createValvesIdList(){
		return ReportCreatorGeneral.createValvesIdList(model);		
	}
	
	public ArrayList<String> createScenarioNamesList(){
		return ReportCreatorGeneral.createScenarioNamesList(model);		
	}
	
					///////////////////////////////////////////////////
					///		Get info of a component using the ID 
					///////////////////////////////////////////////////
	
	//get index of a component without knowing the type of component
	public int getComponentIndex(String ID){
		return compIndentifier.getComponentIndex(ID);
	}
	
	
	//get the Type of a component by its ID
	public String getComponentType(String ID){
		return compIndentifier.getComponentType(ID);
		
	}


}
