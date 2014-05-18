package com.watergrid.dst.client.presenter;

import java.util.ArrayList;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.watergrid.dst.client.model.Scenario;
import com.watergrid.dst.client.model.ScenarioAction;
import com.watergrid.dst.client.view.ScenarioManager;

public class ScenarioManagerPresenter {
	
	ScenarioManager view;
	AppPresenter appPresenter;
	
	int selectedScenarioIndex=-1;		//this is to keep track of the selected scenario in the explorer so that we know what actions to show, update, or delete
	
	boolean isReRunActive=false;		//for activating automatic rerun.
	
	public ScenarioManagerPresenter(){
		
	}
	
	public void setView(ScenarioManager view){ //sets the view reference to modify the view if needed, and registers as presenter of that view so the view can call the presenter if there is any event
		this.view=view;
		this.view.registerPresenter(this);
	}
	
	public void setAppPresenter(AppPresenter appPresenter){
		this.appPresenter=appPresenter;
	}
	
	//////////////////////////////////////////////////////////////////////////
	// METHODS CALLED BY APP PRESENTER
	//////////////////////////////////////////////////////////////////////////
	
	//this method is called by appPresenter when the creation of the model has been completed successfully. 
	public void loadScenarioManager(){
		
		//populate cmb of SourceNodes of the newScenario panel and the edit panel
		ArrayList<String> reservoirIdList;
		reservoirIdList=appPresenter.getModelManager().createReservoirsIdList();
		for(int i=0;i<reservoirIdList.size();i++){
			view.addSourceNodeId(reservoirIdList.get(i));
		}
		
		//for testing only, so it loads the sample scenarios 
		//appPresenter.getModelManager().createSampleScenario();
		//view.updateScenarioExplorer(appPresenter.getModelManager().getModel().getScenarios());
	}
	
	
	//this method is called by the AppPresenter to inform that some actions has caused a change in the scenarios. The origin of this event could be the ScenarioManager view. (this works the same as with component highlight. The presenters inform the AppPresenter and then the AppPresenter inform all the presenters that need to alter their views in reaction to that event)
	//in this case this view's reaction is to update the dataGrid
	public void inforChangeInScenarios(){
		view.updateScenarioExplorer(appPresenter.getModelManager().getModel().getScenarios());
		redrawAllExplorers();
	}
	
	//this method is called by the AppPresenter to inform that the Actions has changed (e.g. a new action has been added). The idea is to react against that event, where we have to update the dataGrid of actions
	public void informChangeInActions(int indexScenarioThatChanged){
		
		//update the view only if the scenario that suffered the change is the same as the active one on the view, otherwise is useless
		if(indexScenarioThatChanged==selectedScenarioIndex){
			view.updateActionExplorer(appPresenter.getModelManager().getModel().getScenarios().get(selectedScenarioIndex).getActions());
		}
		redrawAllExplorers();
	}
	
	//when selected stack changes we have to redraw.
	public void redrawAllExplorers(){
		view.redrawAllExplorers();
	}
	
	
	//////////////////////////////////////////////////////////////////////////
	// METHODS CALLED BY VIEW
	//////////////////////////////////////////////////////////////////////////
	
			//CREATION OF SCENARIOS
	
	//this methods use the model manager to create the scenario and add it to the model
	//and then uses again the model manager to ask for the list of scenarios and sends it back to the view for the dataGrid update

	public void createScenario(String name,String startTime,int totalDuration,int iterationLength,String sourceNodeId,float bulkCoeff,float wallCoeff,float chemicalCons ){
		appPresenter.getModelManager().createScenario(name, startTime, totalDuration, iterationLength, sourceNodeId, bulkCoeff, wallCoeff, chemicalCons);
		appPresenter.informChangeInScenarios();
	}
	
			//DELETE SCENARIO
	
	public void deleteScenario(int scenarioIndex){
		appPresenter.getModelManager().deleteScenario(scenarioIndex);
		appPresenter.informChangeInScenarios();
	}
	//ALTERNATIVE: used by receiving the reference to the object itself obtained from the DataProvider of the DataGrid. This increase coupling because parent view and presenter need to import the model package containing the Scenario class
	//public void deleteScenario(Scenario scenario){
	//	appPresenter.getModelManager().deleteScenario(scenario); 
	//	view.updateScenarioExplorer(appPresenter.getModelManager().getModel().getScenarios());
	//}
	
	
			//EDIT SCENARIO
	
	//this method is called by the view to ask the presenter to get the details of the selected scenario so we can show them in the field for user visualization and edit
	public ArrayList<String> getScenarioDetailsAsStrings(int scenarioIndex){
		//return details in an array list of objects. WARNING: order is important
		ArrayList<String> details = new ArrayList<String>();
		String name=appPresenter.getModelManager().getModel().getScenarios().get(scenarioIndex).getName();
		String startTime=appPresenter.getModelManager().getModel().getScenarios().get(scenarioIndex).getStartTime();
		String totalDuration=String.valueOf(appPresenter.getModelManager().getModel().getScenarios().get(scenarioIndex).getTotalDuration());
		String iterationLength=String.valueOf(appPresenter.getModelManager().getModel().getScenarios().get(scenarioIndex).getIterationLength());
		String sourceNodeId=String.valueOf(appPresenter.getModelManager().getModel().getScenarios().get(scenarioIndex).getSourceNodeId());
		String bulkCoeff=String.valueOf(appPresenter.getModelManager().getModel().getScenarios().get(scenarioIndex).getBulkCoefficient());
		String wallCoeff=String.valueOf(appPresenter.getModelManager().getModel().getScenarios().get(scenarioIndex).getWallCoefficient());
		String chemicalCons=String.valueOf(appPresenter.getModelManager().getModel().getScenarios().get(scenarioIndex).getChemicalCons());
		details.add(name);
		details.add(startTime);
		details.add(totalDuration);
		details.add(iterationLength);
		details.add(sourceNodeId);
		details.add(bulkCoeff);
		details.add(wallCoeff);
		details.add(chemicalCons);
		return details;
	}
	
	public void editScenario(int indexScenarioBeingEdited,String name,String startTime,int totalDuration,int iterationLength,String sourceNodeId,float bulkCoeff,float wallCoeff,float chemicalCons){
		//edits the scenario using the model manager and the call the view to update the view sending the new list of scenarios
		appPresenter.getModelManager().editScenario(indexScenarioBeingEdited, name, startTime, totalDuration, iterationLength, sourceNodeId, bulkCoeff, wallCoeff, chemicalCons);
		appPresenter.informChangeInScenarios();
	}
	
			//ACTIONS DETAILS OF GIVEN SCENARIO
	
	//this method is called by the scenariExplorer to keep us updated of the selected scenario. This is used to know what actions to show and when we want to delete an action we need to know from which scenario
	public void informIndexSelectedScenario(int selectedScenarioIndex){
		this.selectedScenarioIndex=selectedScenarioIndex;
	}
	
	//the view calls this method to ask the presenter to get the list of actions of the current selected scenario to show them in the data grid.
	//this class knows the current selected scanerio because it was previously informed by the view when the user pressed "view actions" button
	public ArrayList<ScenarioAction> getActions(){
		return appPresenter.getModelManager().getModel().getScenarios().get(selectedScenarioIndex).getActions();
	}
	
			//DELETE AN ACTION
	
	// the view call this method to delete a given action. The view sends the index of the action, and the index of the scenario is saved here in this class (it has been informed previously by View (ScenarioExplorer))
	public void deleteAction(int actionIndex){
		
		//delete action 
		appPresenter.getModelManager().deleteAction(selectedScenarioIndex, actionIndex);
		//inform appPresenter that actions has changed so that views react. E.g. the Action explorer has to be updated. send the selected scenario index.
		appPresenter.informChangeInActions(selectedScenarioIndex);
		
	}
	
	
			// RUN SIMULATION
	
	//the view calls this method to run a simulation. 
	public void runSimulation(int selectedScenarioIndex,String simulationType){
		
		//execute simuulation
		appPresenter.getModelManager().runSimulation(selectedScenarioIndex, simulationType);
		
		//activate automatic re-run from first simulation on. 
		if(!isReRunActive){
			activateAutomaticReRun();
			isReRunActive=true;
		}
		
	}
			//RE-RUN all
	
	public void reRunAllSimulations(){
		appPresenter.getModelManager().reRunAll();
	}
	

		//   AUTOMATIC RE-RUNNER

	Timer timer;
	int delay=900000;		//in Milliseconds. every 15 minutes
	
	private void activateAutomaticReRun(){
	
			timer = new Timer() {
			  @Override
			  public void run() {
				  timerUp();
			  }
			};
			timer.schedule(delay);
	}
	
	private void timerUp(){
	    	reRunAllSimulations();
			timer.schedule(delay);
	}
	
	
			//REQUEST the app to highlight a component 
	
	//the view calls this method when the user wants to see a component of an action in the map
	public void highlightComponent(String type,int index,String id){
		appPresenter.informComponentSelected(type, index, id,true);
	}

}
