package com.watergrid.dst.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.watergrid.dst.client.dataobjects.ResultsSummary;
import com.watergrid.dst.client.presenter.ResultsManagerPresenter;

public class ResultsManager extends Composite{
	
	
	ResultsManagerPresenter presenter;							//reference to the presenter to call it in case of events

	Label lblScenarioSimulationTitle;
	
	//child views
	
	ResultsManagerResultsExplorer resultsExplorer;	
	
	ResultsManagerHydraulicSummary hydraulicSummary;
	ResultsManagerHydraulicDetails hydraulicDetails;
	
	ResultsManagerAgeSummary ageSummary;
	ResultsManagerAgeDetails ageDetails;
	
	ResultsManagerSourceSummary sourceSummary;
	ResultsManagerSourceDetails sourceDetails;
	
	ResultsManagerChemicalSummary chemicalSummary;
	ResultsManagerChemicalDetails chemicalDetails;
	
	ResultsManagerAlerts alerts;
	
	String activeSimulationType="";							//keep track of the current active simulation type
	
	public ResultsManager(){
		initializeComponents();
	}
	
	//this method sets the presenter who will be called by events
	public void registerPresenter(ResultsManagerPresenter presenter){
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
		verticalPanel.setWidth("100%");
		//verticalPanel.setHeight("100%");
		initWidget(verticalPanel);
		
		//title label
		
		lblScenarioSimulationTitle = new Label("");
		lblScenarioSimulationTitle.setWidth("100%");
		lblScenarioSimulationTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		verticalPanel.add(lblScenarioSimulationTitle);
		
		//Child views
		
		resultsExplorer = new ResultsManagerResultsExplorer(this);
		verticalPanel.add(resultsExplorer);
		
		hydraulicSummary=new ResultsManagerHydraulicSummary(this);
		verticalPanel.add(hydraulicSummary);
		hydraulicDetails=new ResultsManagerHydraulicDetails(this);
		verticalPanel.add(hydraulicDetails);
		
		ageSummary=new ResultsManagerAgeSummary(this);
		verticalPanel.add(ageSummary);
		ageDetails=new ResultsManagerAgeDetails(this);
		verticalPanel.add(ageDetails);
		
		sourceSummary=new ResultsManagerSourceSummary(this);
		verticalPanel.add(sourceSummary);
		sourceDetails=new ResultsManagerSourceDetails(this);
		verticalPanel.add(sourceDetails);
		
		chemicalSummary=new ResultsManagerChemicalSummary(this);
		verticalPanel.add(chemicalSummary);
		chemicalDetails=new ResultsManagerChemicalDetails(this);
		verticalPanel.add(chemicalDetails);
		
		alerts = new ResultsManagerAlerts(this);
		verticalPanel.add(alerts);
		
		activateExplorerMode();

	}
	
	////////////////////////////////////////
	// Method to activate child views (called by this view or by child views)
	////////////////////////////////////////
	
	public void activateExplorerMode(){
		
		resultsExplorer.redrawResultsExplorer();
		lblScenarioSimulationTitle.setText("");
		
		resultsExplorer.setVisible(true);
		hydraulicSummary.setVisible(false);
		hydraulicDetails.setVisible(false);
		ageSummary.setVisible(false);
		ageDetails.setVisible(false);
		sourceSummary.setVisible(false);
		sourceDetails.setVisible(false);
		chemicalSummary.setVisible(false);
		chemicalDetails.setVisible(false);
		alerts.setVisible(false);
	}
	
	public void activateHydraulicSummaryMode(){
		resultsExplorer.setVisible(false);
		hydraulicSummary.setVisible(true);
		hydraulicDetails.setVisible(false);
		ageSummary.setVisible(false);
		ageDetails.setVisible(false);
		sourceSummary.setVisible(false);
		sourceDetails.setVisible(false);
		chemicalSummary.setVisible(false);
		chemicalDetails.setVisible(false);
		alerts.setVisible(false);
	}
	public void activateHydraulicDetailsMode(){
		resultsExplorer.setVisible(false);
		hydraulicSummary.setVisible(false);
		hydraulicDetails.setVisible(true);
		ageSummary.setVisible(false);
		ageDetails.setVisible(false);
		sourceSummary.setVisible(false);
		sourceDetails.setVisible(false);
		chemicalSummary.setVisible(false);
		chemicalDetails.setVisible(false);
		alerts.setVisible(false);
	}
	
	public void activateAgeSummaryMode(){
		resultsExplorer.setVisible(false);
		hydraulicSummary.setVisible(false);
		hydraulicDetails.setVisible(false);
		ageSummary.setVisible(true);
		ageDetails.setVisible(false);
		sourceSummary.setVisible(false);
		sourceDetails.setVisible(false);
		chemicalSummary.setVisible(false);
		chemicalDetails.setVisible(false);
		alerts.setVisible(false);
	}
	public void activateAgeDetailsMode(){
		resultsExplorer.setVisible(false);
		hydraulicSummary.setVisible(false);
		hydraulicDetails.setVisible(false);
		ageSummary.setVisible(false);
		ageDetails.setVisible(true);
		sourceSummary.setVisible(false);
		sourceDetails.setVisible(false);
		chemicalSummary.setVisible(false);
		chemicalDetails.setVisible(false);
		alerts.setVisible(false);
	}
	
	public void activateSourceTraceSummaryMode(){
		resultsExplorer.setVisible(false);
		hydraulicSummary.setVisible(false);
		hydraulicDetails.setVisible(false);
		ageSummary.setVisible(false);
		ageDetails.setVisible(false);
		sourceSummary.setVisible(true);
		sourceDetails.setVisible(false);
		chemicalSummary.setVisible(false);
		chemicalDetails.setVisible(false);
		alerts.setVisible(false);
	}
	public void activateSourceTraceDetailsMode(){
		resultsExplorer.setVisible(false);
		hydraulicSummary.setVisible(false);
		hydraulicDetails.setVisible(false);
		ageSummary.setVisible(false);
		ageDetails.setVisible(false);
		sourceSummary.setVisible(false);
		sourceDetails.setVisible(true);
		chemicalSummary.setVisible(false);
		chemicalDetails.setVisible(false);
		alerts.setVisible(false);
	}
	
	public void activateChemicalSummaryMode(){
		resultsExplorer.setVisible(false);
		hydraulicSummary.setVisible(false);
		hydraulicDetails.setVisible(false);
		ageSummary.setVisible(false);
		ageDetails.setVisible(false);
		sourceSummary.setVisible(false);
		sourceDetails.setVisible(false);
		chemicalSummary.setVisible(true);
		chemicalDetails.setVisible(false);
		alerts.setVisible(false);
	}
	public void activateChemicalDetailsMode(){
		resultsExplorer.setVisible(false);
		hydraulicSummary.setVisible(false);
		hydraulicDetails.setVisible(false);
		ageSummary.setVisible(false);
		ageDetails.setVisible(false);
		sourceSummary.setVisible(false);
		sourceDetails.setVisible(false);
		chemicalSummary.setVisible(false);
		chemicalDetails.setVisible(true);
		alerts.setVisible(false);
	}
	public void activateAlertsMode(){
		resultsExplorer.setVisible(false);
		hydraulicSummary.setVisible(false);
		hydraulicDetails.setVisible(false);
		ageSummary.setVisible(false);
		ageDetails.setVisible(false);
		sourceSummary.setVisible(false);
		sourceDetails.setVisible(false);
		chemicalSummary.setVisible(false);
		chemicalDetails.setVisible(false);
		alerts.setVisible(true);
	}
	
	/////////////////////////////////////////
	// METHODS TO BE CALLED BY PRESENTER
	/////////////////////////////////////////
	
	public void updateResultsExplorer(List<ResultsSummary> summary){
		resultsExplorer.updateResultsExplorer(summary);
	}
	
	//when selected stack changes we have to redraw.
	public void redrawAllExplorers(){
		resultsExplorer.redrawResultsExplorer();
	}
	
			/////////////////////////////////////////
			// METHODS TO BE CALLED BY CHILD VIEWS
			/////////////////////////////////////////
	
	//this method is called by explorer when user wants to see results of a selected scenario results from the dataGrid
	//we inform our presenter to keep record of the selected scenario and simulation type, then we activate the corresponding view, and load the view with the summary data using the presenter
	public void showResultsSummary(int scenarioIndex,String scenarioName,String simulationType){
		
		lblScenarioSimulationTitle.setText(scenarioName+": "+ simulationType);			//set data into title
		presenter.informSelectedResults(scenarioIndex, scenarioName,simulationType);	//inform presenter
		activeSimulationType=simulationType;											//to keep track of it
		
		if (simulationType.equals("hydraulic")){

			activateHydraulicSummaryMode();												//activate hydro view
			hydraulicSummary.loadSummaryData(presenter.getHydraulicSummaryData());		//load data on view. get data using presenter
			hydraulicSummary.loadAlertsData(presenter.getAlertCount());					//load alerts count
			
		} else if(simulationType.equals("age")){
			
			activateAgeSummaryMode();												
			ageSummary.loadSummaryData(presenter.getAgeSummaryData());
			ageSummary.loadAlertsData(presenter.getAlertCount());
			
		}else if(simulationType.equals("sourceTrace")){
			
			activateSourceTraceSummaryMode();												
			sourceSummary.loadSummaryData(presenter.getSourceTraceSummaryData());	
			sourceSummary.loadAlertsData(presenter.getAlertCount());
			
		}else if(simulationType.equals("chemical")){
			
			activateChemicalSummaryMode();												
			chemicalSummary.loadSummaryData(presenter.getChemicalSummaryData());		
			chemicalSummary.loadAlertsData(presenter.getAlertCount());
		}
		
	}
	
	
	//called by child view (HYDRAULIC summary) when user desires to view the details of the different components
	public void showHydraulicDetails(){
		
		activateHydraulicDetailsMode();
		//load dataGrids calling the presenter to get the data 
		hydraulicDetails.updateNodesData(presenter.getNodeHydraulicResults());
		hydraulicDetails.updateLinksData(presenter.getLinkHydraulicResults());
		
	}
	
	
	//called by child view (AGE summary) when user desires to view the details of the different components
	public void showAgeDetails(){
		
		activateAgeDetailsMode();
		//load dataGrids calling the presenter to get the data 
		ageDetails.updateNodesData(presenter.getNodeAgeResults());
	}
	
	//called by child view (SOURCE summary) when user desires to view the details of the different components
	public void showSourceDetails(){
		
		activateSourceTraceDetailsMode();
		//load dataGrids calling the presenter to get the data 
		sourceDetails.updateNodesData(presenter.getNodeSourceTraceResults());
	}
	
	//called by child view (CHEMICAL summary) when user desires to view the details of the different components
	public void showChemicalDetails(){
		
		activateChemicalDetailsMode();
		//load dataGrids calling the presenter to get the data 
		chemicalDetails.updateNodesData(presenter.getNodeChemicalConResults());
	}
	
	
	//called by child views to view results on map
	public void showResultsOnMap(){
		presenter.showResultsOnMap();
	}
	
	
	//called by child views to highlight a component on the map
	public void highlightComponent(String ID){
		presenter.highlightComponent(ID);
	}
	
	
	//show alerts (called by summary child views)
	public void showAlerts(){
		activateAlertsMode();
		//get alerts from presenter and send them to the child view to load dataGrid (presenter has record of scenario index and simulation type)
		alerts.loadAlertsData(presenter.getAlerts());
	}
	
	//this method is called from the Alerts child view when user wants to go back to summary. We have to check which summary view was the one that was active before
	public void backFromAlerts(){
		
		if (activeSimulationType.equals("hydraulic")){
			activateHydraulicSummaryMode();
		} else if(activeSimulationType.equals("age")){
			activateAgeSummaryMode();
		}else if(activeSimulationType.equals("sourceTrace")){
			activateSourceTraceSummaryMode();
		}else if(activeSimulationType.equals("chemical")){
			activateChemicalSummaryMode();
		}
		
	}
	
	//called by child view to get the complete data step by step of a component
	public ArrayList<Float> getComponentCompleteData(String componentId,String variable){
		return presenter.getComponentCompleteData(componentId, variable);
	}
	
}
