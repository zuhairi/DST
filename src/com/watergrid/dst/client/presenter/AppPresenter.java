//this class initializes the view Classes and their presenters. It puts everything together.
//it contains a reference to the model manager which acts as a facade of the complete model and allows our interaction with it. 
//additionally it contains methods that represents events that affect different views. so when there is an event on a view, the view informs its presenter and the the presenter informs this AppPresenter which then calls all the presenters that need to act upon that event (the one who informed the event could also be called to act upon the event)
//it contains the methods to be called by events that happen in the model manager, e.g. model created, results ready, etc

package com.watergrid.dst.client.presenter;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.watergrid.dst.client.dataobjects.ComponentLevel;
import com.watergrid.dst.client.manager.ModelManager;
import com.watergrid.dst.client.view.AddAction;
import com.watergrid.dst.client.view.CenterPanel;
import com.watergrid.dst.client.view.ComponentExplorer;
import com.watergrid.dst.client.view.ComponentDetails;
import com.watergrid.dst.client.view.ComponentSearcher;
import com.watergrid.dst.client.view.MapResultsControlPanel;
import com.watergrid.dst.client.view.ResultsManager;
import com.watergrid.dst.client.view.ScenarioManager;
import com.watergrid.dst.client.view.LeftPanel;
import com.watergrid.dst.client.view.NetworkMap;
import com.watergrid.dst.client.view.SelectDMA;
import com.watergrid.dst.client.view.TopLogo;

public class AppPresenter {
	
	
	//views
	TopLogo topLogo;
	ComponentExplorer componentExplorer;
	ComponentDetails componentDetails;
	AddAction addAction;
	CenterPanel centerPanel;
	LeftPanel	leftPanel;
	SelectDMA selectDMA;
	NetworkMap networkMap;
	ScenarioManager scenarioManager;
	ComponentSearcher componentSearcher;
	ResultsManager resultsManager;
	MapResultsControlPanel mapControlPanel;
	
	//VIEWpresenters
	SelectDMAPresenter selectDMAPresenter;
	ComponentExplorerPresenter componentExplorerPresenter;
	ComponentDetailsPresenter componentDetailsPresenter;
	AddActionPresenter addActionPresenter;
	NetworkMapPresenter networkMapPresenter;
	ScenarioManagerPresenter scenarioManagerPresenter;
	ComponentSearcherPresenter componentSearcherPresenter;
	ResultsManagerPresenter resultsManagerPresenter;
	MapResultsControlPanelPresenter mapControlPanelPresenter;
	
	NotificationsPresenter notificationPresenter;
	
	//presenters
	ModelManager modelManager;
	
	
	public AppPresenter(){
		initializeViewComponents();
		loadStartView();
	}

	private void initializeViewComponents(){
		//initialize all views
		topLogo = new TopLogo();
		componentExplorer = new ComponentExplorer();
		componentDetails = new ComponentDetails();
		addAction = new AddAction();
		selectDMA = new SelectDMA();
		networkMap = new NetworkMap();
		centerPanel = new CenterPanel(this);		//the stack panel needs reference to this appPresenter to inform change in stack to redraw all the DataGrids in the different views
		leftPanel = new LeftPanel();
		scenarioManager=new ScenarioManager();
		componentSearcher = new ComponentSearcher();
		resultsManager = new ResultsManager();
		mapControlPanel = new MapResultsControlPanel();
		
		//initialize VIEW presenters
		selectDMAPresenter = new SelectDMAPresenter();
		selectDMAPresenter.setView(selectDMA);
		selectDMAPresenter.setAppPresenter(this);
		
		componentExplorerPresenter = new ComponentExplorerPresenter();
		componentExplorerPresenter.setView(componentExplorer);
		componentExplorerPresenter.setAppPresenter(this);
		
		componentDetailsPresenter = new ComponentDetailsPresenter();
		componentDetailsPresenter.setView(componentDetails);
		componentDetailsPresenter.setAppPresenter(this);
		
		addActionPresenter = new AddActionPresenter();
		addActionPresenter.setView(addAction);
		addActionPresenter.setAppPresenter(this);
		
		componentSearcherPresenter = new ComponentSearcherPresenter();
		componentSearcherPresenter.setView(componentSearcher);
		componentSearcherPresenter.setAppPresenter(this);
		
		networkMapPresenter = new NetworkMapPresenter();
		networkMapPresenter.setView(networkMap);
		networkMapPresenter.setAppPresenter(this);
		
		scenarioManagerPresenter=new ScenarioManagerPresenter();
		scenarioManagerPresenter.setView(scenarioManager);
		scenarioManagerPresenter.setAppPresenter(this);
		
		resultsManagerPresenter = new ResultsManagerPresenter();
		resultsManagerPresenter.setView(resultsManager);
		resultsManagerPresenter.setAppPresenter(this);
		
		mapControlPanelPresenter = new MapResultsControlPanelPresenter();
		mapControlPanelPresenter.setView(mapControlPanel);
		mapControlPanelPresenter.setAppPresenter(this);
		
		notificationPresenter = new NotificationsPresenter();
		notificationPresenter.setAppPresenter(this);
		
		//initialize ModelManager
		modelManager = new ModelManager(this);
		
	}
	
	public void loadStartView(){
		//add the ones needed for startup only
		RootPanel.get("topLeft").add(topLogo);
		RootPanel.get("center").add(selectDMA);
		
	}
	
	public void loadNormalView(){

		//this method loads the normal view after a successful creation of model. This method is called by the selectDMApresenter when it is informed that the creation has been done succesfully
		
		//left components view
		RootPanel.get("left").clear();
		RootPanel.get("left").add(leftPanel);
		leftPanel.addWidgetExplorerStack(componentExplorer);
		leftPanel.addWidgetExplorerStack(componentDetails);
		leftPanel.addWidgetExplorerStack(addAction);
		leftPanel.addWidgetSearchStack(componentSearcher);
		//center view
		RootPanel.get("center").clear();
		RootPanel.get("center").add(centerPanel);
		centerPanel.addStack(networkMap, "<html><body><img src='images/mapicon-16.png'>  NETWORK MAP</body></html>");
		centerPanel.addStack(scenarioManager, "<html><body><img src='images/workericon-16.png'>  SCENARIO MANAGER</body></html>");
		centerPanel.addStack(resultsManager, "<html><body><img src='images/charticon3-16.png'>  RESULTS</body></html>");
		//right view
		RootPanel.get("right").clear();
		RootPanel.get("right").add(mapControlPanel);
		//load VIEWS with the model data calling their their PRESENTERS
		componentExplorerPresenter.loadComponentExplorer();
		networkMapPresenter.loadNetworkMap();
		scenarioManagerPresenter.loadScenarioManager();	

	}
	
	public ModelManager getModelManager(){
		//return model manager so that VIEWpresenters can use it to access the model and read information or set info of scenarios
		return modelManager;
	}
				
	
				//////////////////////////////////////////////////////////////////
				// METHOS FOR EVENTS THAT INVOLVE MORE THAN ONE VIEW RESPONSE
				//////////////////////////////////////////////////////////////////
	
	
	/////////////////////////////
	// HIGHLIGHTING HANDLING
	////////////////////////////
	
	//this method is called by presenters when a component has been selected. It then informs all the presenters to update their views
	public void informComponentSelected(String type,int index,String id,boolean activateMap){
		
		
		if(activateMap){
			centerPanel.activateStack(0);
			networkMap.refreshMap();
		}
		
		networkMapPresenter.highlightComponent(type, index, id);
		componentExplorerPresenter.highlightComponent(type, index, id);
		componentDetailsPresenter.showComponentDetails(type, index, id);
		addActionPresenter.informSelectedComponent(type, index, id);
		
		leftPanel.showExplorerStack();		//whenever a component is highlighted, activate the explorer to see details
		

	}
	//this method is the same as the previous one, but it only receives the ID of the component
	//we use the methods of the model manager that with the id it tell us the index and type
	public void informComponentSelectedByID(String componentID,boolean activateMap){
		
		String type =modelManager.getComponentType(componentID);
		int index=modelManager.getComponentIndex(componentID);
		
		this.informComponentSelected(type, index, componentID, activateMap);
	}
	
	/////////////////////////////
	// CHANGE IN SCENARIOS
	////////////////////////////
	
	//this method is called by a presenter (scenario manager in this case) to inform that the scenarios have changed. It can be a new addition, delete, or edit of existing
	//it then informs the views that need to react against this change
	//it can also be called by ModelManager to inform a change, e.g. results are ready
	//we also inform the resultsManager because we might have deleted an scenario or changed its name so we have to update the view
	public void informChangeInScenarios(){
		addActionPresenter.informChangeInScenarios();
		scenarioManagerPresenter.inforChangeInScenarios();
		resultsManagerPresenter.informChangeResultsOrScenarios();
	}
	
	
	/////////////////////////////
	// SHOW ADD ACTION VIEW
	////////////////////////////
	
	//this method is called by ComponentDetail presenter, and we call the AddAction presenter so it shows the view
	public void showAddActionView(){
		addActionPresenter.showView();
	}
	
	
	/////////////////////////////
	// CHANGE IN ACTIONS
	/////////////////////////////
	
	//this method is called when an action has been added or changed so that the views can react (e.g. we add a new action on the AddAction view so the action explorer has to be updated)
	//we notify by sending the index of the scenario that suffered change in its actions
	public void informChangeInActions(int indexScenarioThatChanged){
		scenarioManagerPresenter.informChangeInActions(indexScenarioThatChanged);
	}
	
	
	/////////////////////////////
	// CHANGE IN RESULTS
	/////////////////////////////
	
	//new results are available. This is called by this AppPresenter when modelManager informs results are ready
	
	public void informChangeInResults(){
		
		//inform the presenter that have to update their views
		scenarioManagerPresenter.inforChangeInScenarios();			//(hasRun variable has changed)
		resultsManagerPresenter.informChangeResultsOrScenarios();
	}
	
	
	/////////////////////////////
	// SHOW RESULTS ON MAP
	/////////////////////////////
	
	//this method is called by ResultsManager presenter when the user desires to view the results on the map. Sending the selected scenario index and the simulation type
	public void activateResultsOnMap(int scenarioIndex,String scenarioName,String simulationType){
		
		//activate the mapView
		centerPanel.activateStack(0);
		
		//refresh map (triggerResize) in case it has gone gray
		networkMapPresenter.refreshMap();
		
		//inform map control panel to activate its view
		mapControlPanelPresenter.activateMapControlPanel(scenarioIndex, scenarioName,simulationType);
	}
	
	
	//this method is called by the ControlPanel presenter sending the array of with the results of a given variable of a given time step.
	//we then send the array to the map presenter so it displays the data on the map
	public void showResultsOnMap(ArrayList<ComponentLevel> results){
		networkMapPresenter.showResultsOnMap(results);
	}
	
	
	//////////////////////////////////////////////////////////////////////////
	
	
	
	///////////////////////////////////
	// METHODS CALLED BY MODEL MANAGER (CallBacks)
	//////////////////////////////////
	
	public void informLoadingModelFinished(boolean isCreationSuccessfull,String errorLog){
		selectDMAPresenter.informLoadingModelFinished(isCreationSuccessfull, errorLog);
	}
	
	//single manual simulation
	public void informSimulationFinished(int scenarioIndex,String scenarioName, String simulationType, boolean isOK,String errorLog){
		
		//notify user
		notificationPresenter.informSimulationFinished(scenarioIndex, scenarioName, simulationType, isOK, errorLog);

		//inform this appPresenter so it informs the corresponding presenters to update their views
		this.informChangeInResults();
		
	}
	
	
	//when all re-runs have finished
	public void informSimulationFinishedReRun(ArrayList<String> report){
		
		//notify user
		notificationPresenter.informSimulationFinishedReRun(report);

		//inform this appPresenter so it informs the corresponding presenters to update their views
		this.informChangeInResults();
	}
	
	///////////////////
	//  CALLED BY CENTER STACK - Change in stack
	//////////////////
	
	//the dataGrid needs to be redrawn (refresh) every time it is switch to visible, otherwise it will not show the data in it.
	//so this method is called by the center stackPanel when the stack has change, and we inform the views to they redraw their dataGrids
	//this happens because if the dataGrid is not visible at the moment, and we update its DataProvider, then when we make it visible it doesn't show the data
	public void informChangeInSelectedStack(){
		
		scenarioManagerPresenter.redrawAllExplorers();
		resultsManagerPresenter.redrawAllExplorers();
		
	}

	
}
