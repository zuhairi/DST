package com.watergrid.dst.client.presenter;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.watergrid.dst.client.dataobjects.ComponentLevel;
import com.watergrid.dst.client.view.MapResultsControlPanel;

public class MapResultsControlPanelPresenter {

	
	MapResultsControlPanel view;			//the views it will manipulate
	AppPresenter appPresenter;				//the AppPresenter to interact with the rest of the application
	
	
	int scenarioIndex;						//keep track of the selected scenario and simulation type for further actions. They are informed when this view is activated by the results manager view
	String simulationType;
	
	String selectedNodeVariable="none";
	String selectedLinkVariable="none";
	int selectedTimeStep=0;
	
	ArrayList<ComponentLevel> linkScaleLevelResults=null;		//this arrays represent the results (by scale level) of the selected variable by the user of the selected time step
	ArrayList<ComponentLevel> nodeScaleLevelResults=null;
	
	////////////////////
	
	public MapResultsControlPanelPresenter(){
		
	}

	public void setView(MapResultsControlPanel view){ //sets the view reference to modify the view if needed, and registers as presenter of that view so the view can call the presenter if there is any event
		this.view=view;
		this.view.registerPresenter(this);
	}

	
	public void setAppPresenter(AppPresenter appPresenter){
		this.appPresenter=appPresenter;
	}
	
	//////////////////
	
	

					////////////////////////////////////////////
					// METHODS CALLED BY APP PRESENTER
					////////////////////////////////////////////
	
	public void activateMapControlPanel(int scenarioIndex,String scenarioName,String simulationType){
		
		//save this variables for further actions
		this.scenarioIndex=scenarioIndex;
		this.simulationType=simulationType;
		
		//show the corresponding control panel
		view.activateView(scenarioName, simulationType);
		
		//clear map from previous results if any by setting level to zero on stored results and sending to map view via appPresenter
		//if they are null nothings has to be done because it means there are no results being shown
		if(nodeScaleLevelResults!=null){
			
				for(int j=0;j<nodeScaleLevelResults.size();j++){
					nodeScaleLevelResults.get(j).setLevel(0);
				}
			appPresenter.showResultsOnMap(nodeScaleLevelResults);
		}
		
		if(linkScaleLevelResults!=null){
			
				for(int j=0;j<linkScaleLevelResults.size();j++){
					linkScaleLevelResults.get(j).setLevel(0);
				}
			appPresenter.showResultsOnMap(linkScaleLevelResults);
		}
		
		//clear previous results stored in this presenter
		nodeScaleLevelResults=null;
		linkScaleLevelResults=null;
		selectedLinkVariable="none";
		selectedNodeVariable="none";
		selectedTimeStep=0;
	}
	
	
	
					////////////////////////////////////////////
					// METHODS CALLED BY VIEW
					////////////////////////////////////////////
	
	
	//the view calls this method to get the time steps to show in the comboBox
	public ArrayList<String> getTimeSteps(){
		return appPresenter.getModelManager().getTimeSteps(scenarioIndex);
	}
	
	
	//this methods are called by the view when user selects a variable to visualize in map.
	//use the model manager to update the ArrayLists we have here that represent the level of each component in a selected timeStep
	//send results to map view through appPresenter of the current selected time step
	//if the selected variable is other than "none" get the nodeScaleLevel results from the model manager and send them to the map
	//if the selected variable is "none" there are 2 options. 1-If the existing results are equal to null, we just don't have to do anything because it means the map is not showing any results. 2- if the results have some content, so then we set their levels to zero and send to map view to set default color of components
	
	public void informSelectedNodeVariable(String selectedNodeVariable){
		
		this.selectedNodeVariable=selectedNodeVariable;
		
		if (!selectedNodeVariable.equals("none")){
			
			nodeScaleLevelResults=appPresenter.getModelManager().createNodeScaleLevelResults(scenarioIndex, simulationType, selectedNodeVariable,selectedTimeStep);
			appPresenter.showResultsOnMap(nodeScaleLevelResults);
			
		}else{
			
			if(nodeScaleLevelResults!=null){
				
				for(int j=0;j<nodeScaleLevelResults.size();j++){
					nodeScaleLevelResults.get(j).setLevel(0);
				}
				appPresenter.showResultsOnMap(nodeScaleLevelResults);
			}
		}
		
	}
	
	
	//same as previous one
	public void informSelectedLinkVariable(String selectedLinkVariable){
		
		this.selectedLinkVariable=selectedLinkVariable;
		
		if (!selectedLinkVariable.equals("none")){
			
			linkScaleLevelResults=appPresenter.getModelManager().createLinkScaleLevelResults(scenarioIndex, simulationType, selectedLinkVariable,selectedTimeStep);
			appPresenter.showResultsOnMap(linkScaleLevelResults);	
			
		}else{
			
			if(linkScaleLevelResults!=null){

				for(int j=0;j<linkScaleLevelResults.size();j++){
					linkScaleLevelResults.get(j).setLevel(0);
				}
				appPresenter.showResultsOnMap(linkScaleLevelResults);	
			}
		}
	}
	
	//change the selected time step variable and send the results to the map view via the appPresenter
	public void informSelectedTimeStep(int selectedTimeStep){
		
		this.selectedTimeStep=selectedTimeStep;
		
		if(nodeScaleLevelResults!=null){
			if(!selectedNodeVariable.equals("none")){
				nodeScaleLevelResults=appPresenter.getModelManager().createNodeScaleLevelResults(scenarioIndex, simulationType, selectedNodeVariable,selectedTimeStep);
				appPresenter.showResultsOnMap(nodeScaleLevelResults);
			}
		}
		
		if(linkScaleLevelResults!=null){
			if(!selectedLinkVariable.equals("none")){
				linkScaleLevelResults=appPresenter.getModelManager().createLinkScaleLevelResults(scenarioIndex, simulationType, selectedLinkVariable,selectedTimeStep);
				appPresenter.showResultsOnMap(linkScaleLevelResults);
			}
		}
	}
	
	
	
	//called by view to get scale. we get the scale via the model manager
	public ArrayList<Float> getNodeScale(){
		return appPresenter.getModelManager().createScale(scenarioIndex, simulationType, selectedNodeVariable);
	}
	
	public ArrayList<Float> getLinkScale(){
		return appPresenter.getModelManager().createScale(scenarioIndex, simulationType, selectedLinkVariable);
	}
		
	
	
}
