//this class represents the ScenarioManager view. It is also made up of other view classes. 
//So this one acts as the container of the child views and as a BRIDGE between the child views and the presenter
//this class is the one in charge of holding the child views, and activating the different child views depending on the operation mode. Additionally it acts as a bridge between request from presenter to child views (presenter is only coupled to this parent view), and from child views to presenter (child views are only coupled to this parent view)


package com.watergrid.dst.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.watergrid.dst.client.model.Scenario;
import com.watergrid.dst.client.model.ScenarioAction;
import com.watergrid.dst.client.presenter.ScenarioManagerPresenter;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class ScenarioManager extends Composite{
	
	ScenarioManagerPresenter presenter;							//reference to the presenter to call it in case of events
	ScenarioManagerNewScenario newScenarioPanel;				//child view
	ScenarioManagerScenarioExplorer scenarioExplorerPanel;		//child view
	ScenarioManagerEditScenario editScenarioPanel;				//child view
	ScenarioManagerActionExplorer actionExplorerPanel;			//child view
	
	private HorizontalPanel horizontalPanel;
	
	public ScenarioManager(){
		initializeComponents();
	}
	
	//this method sets the presenter who will be called by events
	public void registerPresenter(ScenarioManagerPresenter presenter){
		this.presenter=presenter;
	}
	
	
	///////////////////////
	// VIEW COMPONENTS
	//////////////////////
	
	private void initializeComponents(){
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(5);
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		//verticalPanel.setSize("100%", "100%");
		verticalPanel.setWidth("100%");
		initWidget(verticalPanel);
		
		horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setWidth("100%");
		
		//Child views
		
		newScenarioPanel = new ScenarioManagerNewScenario(this);
		verticalPanel.add(newScenarioPanel);
		newScenarioPanel.setVisible(false);
		
		scenarioExplorerPanel = new ScenarioManagerScenarioExplorer(this);
		verticalPanel.add(scenarioExplorerPanel);
		scenarioExplorerPanel.setVisible(true);
		
		editScenarioPanel = new ScenarioManagerEditScenario(this);
		verticalPanel.add(editScenarioPanel);
		editScenarioPanel.setVisible(false);
		
		actionExplorerPanel = new ScenarioManagerActionExplorer(this);
		verticalPanel.add(actionExplorerPanel);
		actionExplorerPanel.setVisible(false);
	}

	
	////////////////////////////////////////
	// Method to activate child views (called by this view or by child views)
	////////////////////////////////////////
	
	public void activateNormalMode(){
		redrawAllExplorers();
		newScenarioPanel.setVisible(false);
		editScenarioPanel.setVisible(false);
		scenarioExplorerPanel.setVisible(true);
		actionExplorerPanel.setVisible(false);
	}
	public void activateNewScenarioMode(){
		newScenarioPanel.setVisible(true);
		editScenarioPanel.setVisible(false);
		scenarioExplorerPanel.setVisible(false);
		actionExplorerPanel.setVisible(false);
	}
	
	public void activateEditScenarioMode(){
		newScenarioPanel.setVisible(false);
		editScenarioPanel.setVisible(true);
		scenarioExplorerPanel.setVisible(false);
		actionExplorerPanel.setVisible(false);
	}
	
	public void activateActionExplorerMode(){
		newScenarioPanel.setVisible(false);
		editScenarioPanel.setVisible(false);
		scenarioExplorerPanel.setVisible(false);
		actionExplorerPanel.setVisible(true);
	}
	
	
	/////////////////////////////////////////
	// METHODS TO BE CALLED BY PRESENTER
	/////////////////////////////////////////
	//remember that the presenter is only coupled to this parentView. So some of this methods act just as bridge and forward to child views
	
	//for the source nodes (reservoirs) combo box
	public void addSourceNodeId(String sourceNodeId){
		newScenarioPanel.addSourceNodeId(sourceNodeId);
		editScenarioPanel.addSourceNodeId(sourceNodeId);
	}
	
	//updates scenario explorer using the list of scenarios
	public void updateScenarioExplorer(List<Scenario> scenarioModel){
		scenarioExplorerPanel.updateScenarioExplorer(scenarioModel);
	}
	
	
	//update action explorer, new actions has been added or changed
	public void updateActionExplorer(List<ScenarioAction> actionModel){
		actionExplorerPanel.updateActionExplorer(actionModel);
	}
	
	//when selected stack changes we have to redraw.
	public void redrawAllExplorers(){
		actionExplorerPanel.redrawActionExplorer();
		scenarioExplorerPanel.redrawScenarioExplorer();
	}
	
	/////////////////////////////////////////
	// METHODS TO BE CALLED BY CHILD VIEWS
	/////////////////////////////////////////
	
	//remember that the child views are not coupled to the presenter, so some of this methods act as a BRIDGE that forward the call to the presenter
	
			//Creation of NEW Scenarios

	public void createScenario(String name,String startTime,int totalDuration,int iterationLength,String sourceNodeId,float bulkCoeff,float wallCoeff,float chemicalCons ){
		presenter.createScenario(name, startTime, totalDuration, iterationLength, sourceNodeId, bulkCoeff, wallCoeff, chemicalCons);
		activateNormalMode();
	}
	
			//delete scenario
	
	public void deleteScenario(int scenarioIndex){
		presenter.deleteScenario(scenarioIndex);
	}
	//ALTERNATIVE: used by receiving the reference to the object itself obtained from the DataProvider of the DataGrid. This increase coupling because parent view and presenter need to import the model package containing the Scenario class
	//public void deleteScenario(Scenario scenario){
	//	presenter.deleteScenarios(scenario);				
	//}
	
	
			//edit scenarios
	
	public void showEditScenarioView(int selectedIndex){
		activateEditScenarioMode();
		//call the presenter to request all the information of the selected scenario (it returns a list of strings each represent each value WARNING: keep an eye on the order)
		ArrayList<String> details = presenter.getScenarioDetailsAsStrings(selectedIndex);
		//call a method in the panel to fill in all the info of the selected scenario and tell him what is the index so he can later call a save changes using the index
		editScenarioPanel.loadScenarioData(selectedIndex,details.get(0), details.get(1), details.get(2), details.get(3), details.get(4), details.get(5), details.get(6), details.get(7));
	}
	
	public void editScenario(int indexScenarioBeingEdited,String name,String startTime,int totalDuration,int iterationLength,String sourceNodeId,float bulkCoeff,float wallCoeff,float chemicalCons){
		presenter.editScenario(indexScenarioBeingEdited, name, startTime, totalDuration, iterationLength, sourceNodeId, bulkCoeff, wallCoeff, chemicalCons);
		activateNormalMode();
	}
	
	
			//show actions of a scenario
	
	//this method is called by the ScenarioExplorer child view to notify that the user wants to see the actions of a given experiment. It sends the index of the scenario to inform then the presenter to keep track of it for later actions that involve that scenario
	public void showActionsExplorer(int scenarioIndex,String scenarioName){
		
		//inform the presenter the index of the scenario
		presenter.informIndexSelectedScenario(scenarioIndex);
		//call the presenter to get the list, send the list to the action explorer to show on dataGrid
		actionExplorerPanel.updateActionExplorer(presenter.getActions());
		//inform the action explorer the name of the scenario to show it on the title
		actionExplorerPanel.informScenarioName(scenarioName);
		//activate the action explorer view
		activateActionExplorerMode();
	}
	
			//delete action of a scenario
	
	//this method is called by by the Action explorer child view to delete a selected action. It sends the index of the action,and the index of the scenario is held by the presenter (it has been informed by the Scenario explorer previously) 
	public void deleteAction(int actionIndex){
		presenter.deleteAction(actionIndex);
	}
	
			//run simulation of scenario
	
	//this method is called by child view to run a specific type of simulation on the selected scenario in the scenario explorer
	//calls the view presenter to run simulation. this is just a bridge class
	public void runSimulation(int selectedScenarioIndex,String simulationType){
		presenter.runSimulation(selectedScenarioIndex, simulationType);
	}
	
			//re-run all simulations
	
	public void reRunAllSimulations(){
		presenter.reRunAllSimulations();
	}
	
			//view the component of an action on the map (Highlight)
	public void highlightComponent(String type,int index,String id){
		presenter.highlightComponent(type, index, id);
	}
	
	
	
}
