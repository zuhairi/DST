package com.watergrid.dst.client.presenter;

import com.google.gwt.user.client.Window;
import com.watergrid.dst.client.view.AddAction;

public class AddActionPresenter {

	
	AddAction view;					//the views it will manipulate
	AppPresenter appPresenter;		//the AppPresenter to interact with the rest of the application
	
	//variables that represent the current selected component (informed by AppPresenter so that when users adds action we know the info of the component the actions is added to)
	String componentType;
	int componentIndex;
	String componentId;
	
	////////////////////
	
	public AddActionPresenter(){
		
	}

	public void setView(AddAction view){ //sets the view reference to modify the view if needed, and registers as presenter of that view so the view can call the presenter if there is any event
		this.view=view;
		this.view.registerPresenter(this);
	}

	
	public void setAppPresenter(AppPresenter appPresenter){
		this.appPresenter=appPresenter;
	}
	
	/////////////////////////////////
	// Methods called by appPresenter
	//////////////////////////////////
	

		// COMPONENT HIGHLIGHTED

	//this method is called by AppPresenter to inform that a component has been selected, so we know what component we are adding and action to.
	public void informSelectedComponent(String type,int index,String id){
		view.setVisible(false);	//hide view because a new component has been selected
		this.componentType=type;
		this.componentIndex=index;
		this.componentId=id;
	}
	
		// SHOW ADD ACTION VIEW
	public void showView(){
		view.setVisible(true);
	}
	
		//CHANGE IN SCENARIOS
	// Method called by AppPresenter to inform that the Scenarios has changed (to reload list box of names and reset it)
	public void informChangeInScenarios(){
		view.reLoadScenarioList(appPresenter.getModelManager().createScenarioNamesList());
	}
	
	///////////////////////////////
	// METHODS CALLED BY VIEW
	///////////////////////////////
	
	
	//the view informs the users has selected a scenario so we have to get the information of the times and load the view with that information
	public void informChangeInSelectedScenario(int scenarioIndex){
		
		String startTime=appPresenter.getModelManager().getModel().getScenarios().get(scenarioIndex).getStartTime();
		int totalDuration=appPresenter.getModelManager().getModel().getScenarios().get(scenarioIndex).getTotalDuration();
		int iterationLength=appPresenter.getModelManager().getModel().getScenarios().get(scenarioIndex).getIterationLength();
		view.loadTimeList(startTime, totalDuration, iterationLength);	
	}
	
	public void addAction(int scenarioIndex,String action,String actionTime,String actionDate){
		//the view informs the ScenarioIndex, the action, and the action time. This presenter holds the rest of the information which is the information about the component.(was informed by appPresenter when a component was select on explorer or map)
		appPresenter.getModelManager().addAction(scenarioIndex, componentType, componentId, componentIndex, action, actionTime, actionDate);
		//inform the appPresenter that actions have changed sending the index of the scenario that have suffered the changes
		appPresenter.informChangeInActions(scenarioIndex);
	}

}
