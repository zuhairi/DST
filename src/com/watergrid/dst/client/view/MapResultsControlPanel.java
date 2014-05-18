package com.watergrid.dst.client.view;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.watergrid.dst.client.presenter.MapResultsControlPanelPresenter;

public class MapResultsControlPanel extends Composite{
	
	
	MapResultsControlPanelPresenter presenter;
	
	//child views
	Label lblScenarioSimulationTitle;
	MapResultsControlPanelHydraulic controlPanelHydraulic;
	MapResultsControlPanelAge controlPanelAge;
	MapResultsControlPanelSource controlPanelSource;
	MapResultsControlPanelChemical controlPanelChemical;
	
	
	public MapResultsControlPanel(){
		
		initializeComponents();
	}
	
	//this method sets the presenter who will be called by events
	public void registerPresenter(MapResultsControlPanelPresenter presenter){
		this.presenter=presenter;
	}
	
	
	/////////////////
	// VIEW COMPONENTS
	////////////////
	
	private void initializeComponents(){
		
		
		StackPanel stackPanel = new StackPanel();
		stackPanel.setSize("100%", "100%");
		initWidget(stackPanel);
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(2);
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setWidth("100%");
		//verticalPanel.setHeight("100%");
		stackPanel.add(verticalPanel, "MAP RESULTS", false);
		
		//title label
		
		lblScenarioSimulationTitle = new Label("");
		lblScenarioSimulationTitle.setWidth("100%");
		lblScenarioSimulationTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(lblScenarioSimulationTitle);
		
		//Child views
		controlPanelHydraulic = new MapResultsControlPanelHydraulic(this);
		verticalPanel.add(controlPanelHydraulic);
		
		controlPanelAge = new MapResultsControlPanelAge(this);
		verticalPanel.add(controlPanelAge);
		
		controlPanelSource = new MapResultsControlPanelSource(this);
		verticalPanel.add(controlPanelSource);
		
		controlPanelChemical = new MapResultsControlPanelChemical(this);
		verticalPanel.add(controlPanelChemical);
		
		
		//initially all are not visible
		
		this.setVisible(false);
		
		lblScenarioSimulationTitle.setVisible(false);
		controlPanelHydraulic.setVisible(false);
		controlPanelAge.setVisible(false);
		controlPanelSource.setVisible(false);
		controlPanelChemical.setVisible(false);
		
	}
	
	////////////////////////////////////////
	// Method to activate child views (called by this view or by child views)
	////////////////////////////////////////
	
	public void activateHydraulicMode(){
		
		this.setVisible(true);
		lblScenarioSimulationTitle.setVisible(true);
		
		controlPanelHydraulic.setVisible(true);
		controlPanelAge.setVisible(false);
		controlPanelSource.setVisible(false);
		controlPanelChemical.setVisible(false);
		
		controlPanelHydraulic.loadView();
		
	}
	
	public void activateAgeMode(){
		
		this.setVisible(true);
		
		lblScenarioSimulationTitle.setVisible(true);
		
		controlPanelHydraulic.setVisible(false);
		controlPanelAge.setVisible(true);
		controlPanelSource.setVisible(false);
		controlPanelChemical.setVisible(false);
		
		controlPanelAge.loadView();
		
	}
	
	public void activateSourceTraceMode(){
		
		this.setVisible(true);
		
		lblScenarioSimulationTitle.setVisible(true);
		
		controlPanelHydraulic.setVisible(false);
		controlPanelAge.setVisible(false);
		controlPanelSource.setVisible(true);
		controlPanelChemical.setVisible(false);
		
		controlPanelSource.loadView();
		
	}
	
	public void activateChemicalMode(){
		
		this.setVisible(true);

		lblScenarioSimulationTitle.setVisible(true);
		
		controlPanelHydraulic.setVisible(false);
		controlPanelAge.setVisible(false);
		controlPanelSource.setVisible(false);
		controlPanelChemical.setVisible(true);
		
		controlPanelChemical.loadView();
	}
	
	
	public void hideControlPanel(){
		this.setVisible(false);
	}
	
	/////////////////////////////////////////
	// METHODS TO BE CALLED BY PRESENTER
	/////////////////////////////////////////
	
	public void activateView(String scenarioName,String simulationType){
		
		this.setVisible(true);
		
		lblScenarioSimulationTitle.setText(scenarioName+": "+simulationType);
		
		if(simulationType.equals("hydraulic")){
			activateHydraulicMode();
			
		}else if (simulationType.equals("age")){
			activateAgeMode();
			
		}else if (simulationType.equals("sourceTrace")){
			activateSourceTraceMode();
			
		}else if (simulationType.equals("chemical")){
			activateChemicalMode();
		}
		
		
	}
	
	/////////////////////////////////////////
	// METHODS TO BE CALLED BY CHILD VIEWS
	/////////////////////////////////////////
	
	
	//this methods are called by child views when user selects a variable to visualize in map
	
	public ArrayList<String> getTimeSteps(){
		return presenter.getTimeSteps();
	}

	public void informSelectedNodeVariable(String selectedNodeVariable){
		presenter.informSelectedNodeVariable(selectedNodeVariable);
	}
	
	public void informSelectedLinkVariable(String selectedLinkVariable){
		presenter.informSelectedLinkVariable(selectedLinkVariable);
	}
	
	public void informSelectedTimeStep(int selectedTimeStep){
		presenter.informSelectedTimeStep(selectedTimeStep);
	}

	
	public ArrayList<Float> getNodeScale(){
		return presenter.getNodeScale();
	}
	
	public ArrayList<Float> getLinkScale(){
		return presenter.getLinkScale();
	}

}
