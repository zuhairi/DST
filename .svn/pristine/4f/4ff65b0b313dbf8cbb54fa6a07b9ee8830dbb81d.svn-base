package com.watergrid.dst.client.presenter;

import java.util.ArrayList;

import com.watergrid.dst.client.dataobjects.AlertInfo;
import com.watergrid.dst.client.dataobjects.LinkHydraulicResult;
import com.watergrid.dst.client.dataobjects.NodeAgeResult;
import com.watergrid.dst.client.dataobjects.NodeChemicalResult;
import com.watergrid.dst.client.dataobjects.NodeHydraulicResult;
import com.watergrid.dst.client.dataobjects.NodeSourceTraceResult;
import com.watergrid.dst.client.view.ResultsManager;

public class ResultsManagerPresenter {

	ResultsManager view;
	AppPresenter appPresenter;
	
	int selectedScenarioIndex;			//keep track of the selected scenario and simulation type for future actions and data queries asked by view
	String selectedScenarioName;
	String selectedSimulationType;
	
	public ResultsManagerPresenter(){
		
	}
	
	public void setView(ResultsManager view){ //sets the view reference to modify the view if needed, and registers as presenter of that view so the view can call the presenter if there is any event
		this.view=view;
		this.view.registerPresenter(this);
	}
	
	public void setAppPresenter(AppPresenter appPresenter){
		this.appPresenter=appPresenter;
	}
	
	
	//////////////////////////////////////////////////////////////////////////
	// METHODS CALLED BY APP PRESENTER
	//////////////////////////////////////////////////////////////////////////
	
	//change in results can be because of: results have arrived, or change in scenarios (e.g. change name, new scenario added, or deleted)
	//all this actions make us update the results view because they not only include results but info about scenarios like name
	
	public void informChangeResultsOrScenarios(){
		//we ask the model manager for the resultsSumarry list and send it to the view
		view.updateResultsExplorer(appPresenter.getModelManager().createResultsSummary());
	}
	
	
	//when selected stack changes we have to redraw.
	public void redrawAllExplorers(){
		view.redrawAllExplorers();
	}
	
	
	//////////////////////////////////////////////////////////////////////////
	// METHODS CALLED BY VIEW
	//////////////////////////////////////////////////////////////////////////
	
	//the view informs this when user selects a specific results to interact with
	public void informSelectedResults(int selectedScenarioIndex,String selectedScenarioName ,String selectedSimulationType){
		
		this.selectedScenarioIndex=selectedScenarioIndex;
		this.selectedScenarioName=selectedScenarioName;
		this.selectedSimulationType=selectedSimulationType;
	}
	
				//////////////////////
				//  GET SUMMARY RESULTS
				//////////////////////
	
	
	//get the HYDRO summary using model manager and sending the scenario index
	public ArrayList<Float> getHydraulicSummaryData(){
		
		return appPresenter.getModelManager().createHydraulicResultSummary(selectedScenarioIndex);
	}
	//get the AGE summary using model manager and sending the scenario index
	public ArrayList<Float> getAgeSummaryData(){
		return appPresenter.getModelManager().createAgeResultSummary(selectedScenarioIndex);
	}
	//get the SOURCE summary using model manager and sending the scenario index
	public ArrayList<Float> getSourceTraceSummaryData(){
		return appPresenter.getModelManager().createSourceTraceResultSummary(selectedScenarioIndex);
	}
	//get the CHEMICAL summary using model manager and sending the scenario index
	public ArrayList<Float> getChemicalSummaryData(){
		return appPresenter.getModelManager().createChemicalResultSummary(selectedScenarioIndex);
	}
	
	
				/////////////////////////////////
				//  GET ALERTS
				/////////////////////////////////
	
	public int getAlertCount(){
		return appPresenter.getModelManager().getAlertCount(selectedScenarioIndex, selectedSimulationType);
	}
	
	
	public ArrayList<AlertInfo> getAlerts(){
		return appPresenter.getModelManager().getAlerts(selectedScenarioIndex, selectedSimulationType);
	}
	
				/////////////////////////////////
				//  GET DETAILED COMPONENT RESULTS (depending on type of simulation)
				/////////////////////////////////
	
	public ArrayList<NodeHydraulicResult> getNodeHydraulicResults(){
		return appPresenter.getModelManager().createNodeHydraulicResults(selectedScenarioIndex);
	}
	
	public ArrayList<LinkHydraulicResult> getLinkHydraulicResults(){
		return appPresenter.getModelManager().createLinkHydraulicResults(selectedScenarioIndex);
	}
	
	
	public ArrayList<NodeAgeResult> getNodeAgeResults(){
		return appPresenter.getModelManager().createNodeAgeResults(selectedScenarioIndex);
	}
	
	public ArrayList<NodeSourceTraceResult> getNodeSourceTraceResults(){
		return appPresenter.getModelManager().createNodeSourceTraceResults(selectedScenarioIndex);
	}
	
	public ArrayList<NodeChemicalResult> getNodeChemicalConResults(){
		return appPresenter.getModelManager().createNodeChemicalConResults(selectedScenarioIndex);
	}
	
				/////////////////////////////////
				//  GET COMPLETE DATA OF COMPONENT
				/////////////////////////////////
	
	//complete data step by step of a component
	
	public ArrayList<Float> getComponentCompleteData(String componentId,String variable){
		
		return appPresenter.getModelManager().getComponentCompleteData(selectedScenarioIndex, selectedSimulationType, componentId, variable);
	}
	
	
				/////////////////////////////
				//   Highlight a component from the results
				//////////////////////////
	
	public void highlightComponent(String componentID){
		appPresenter.informComponentSelectedByID(componentID, true);
	}
	
				/////////////////////////////
				//  Show results on Map
				//////////////////////////
	
	//when user wants to see results on map. this method is called by view, and it informs appPresenter so it activates the map control panel and activates the map
	//this presenter keeps track of the selected scenario index and simulation type
	public void showResultsOnMap(){
		
		appPresenter.activateResultsOnMap(selectedScenarioIndex, selectedScenarioName,selectedSimulationType);
		
	}
	
}
