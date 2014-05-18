package com.watergrid.dst.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.watergrid.dst.client.model.Scenario;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Label;



public class ScenarioManagerScenarioExplorer extends Composite{

	ScenarioManager parentView;							//this view is contained in a ScenarioManager view. We need reference to it to inform of events, and then the parent view will inform the presenter

	
	VerticalPanel verticalPanelGlobal;					//parts of view (we need to hide and show)
	Button btnNewScenario;
	DecoratorPanel selectSimulationPanel;
	HorizontalPanel optionButtonsPanel;
	DataGrid<Scenario> dataGrid;
	SimplePager pager;
	
	List<Scenario> list;								//list that represents the model
	ListDataProvider<Scenario> dataProvider;
	SingleSelectionModel<Scenario> selectionModel;		//selection model so we can read the selected object
	
	ImageResources images = GWT.create(ImageResources.class);
	
	public ScenarioManagerScenarioExplorer(ScenarioManager parentView){
		
		this.parentView=parentView;
		initializeComponents();
		showNormalView();
	}
	
	//////////////////////
	// VIEW COMPONENTS
	//////////////////////
	
	private void initializeComponents(){
		
		verticalPanelGlobal = new VerticalPanel();
		verticalPanelGlobal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelGlobal.setSpacing(3);
		verticalPanelGlobal.setWidth("100%");
		initWidget(verticalPanelGlobal);
		
		Image imgLogo = new Image(images.workerBig());
		verticalPanelGlobal.add(imgLogo);
		
		//DATAGRID
		
		createDataGrid();
		
		//DATA GRID OPTION BUTTONS
		
		optionButtonsPanel = new HorizontalPanel();
		verticalPanelGlobal.add(optionButtonsPanel);
		optionButtonsPanel.setWidth("100%");
		
		
		//new  scenario button
		
		btnNewScenario = new Button("");
		btnNewScenario.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentView.activateNewScenarioMode();
			}
		});
		Image imgnew = new Image(images.newSmall());
		VerticalPanelButton buttonPanelnew = new VerticalPanelButton(imgnew, "New Scenario");		//this is a custom panel that includes an image and a title
		btnNewScenario.getElement().appendChild(buttonPanelnew.getElement());
		btnNewScenario.setWidth("100%");
		optionButtonsPanel.add(btnNewScenario);
		
		// Delete button
		Button btnDelete = new Button("");
		btnDelete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deleteButtonPressed();
			}
		});
		optionButtonsPanel.add(btnDelete);
		btnDelete.setWidth("100%");
		Image img = new Image(images.trashSmall());
		Composite buttonPanel = new VerticalPanelButton(img, "Delete");		//this is a custom panel that includes an image and a title
		btnDelete.getElement().appendChild(buttonPanel.getElement());
		
		// Edit button
		Button btnEdit = new Button("");
		btnEdit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				editButtonPressed();
			}
		});
		optionButtonsPanel.add(btnEdit);
		btnEdit.setWidth("100%");
		Image img2 = new Image(images.editSmall());
		Composite buttonPanel2 = new VerticalPanelButton(img2, "View/Edit details");		//this is a custom panel that includes an image and a title
		btnEdit.getElement().appendChild(buttonPanel2.getElement());
		
		// view actions button
		Button btnViewActions = new Button("");
		btnViewActions.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				viewActionsButtonPressed();
			}
		});
		optionButtonsPanel.add(btnViewActions);
		btnViewActions.setWidth("100%");
		Image img3 = new Image(images.jobSmall());
		Composite buttonPanel3 = new VerticalPanelButton(img3, "View Actions");		//this is a custom panel that includes an image and a title
		btnViewActions.getElement().appendChild(buttonPanel3.getElement());
		
		// run simulation button
		Button btnRunSimulations = new Button("");
		btnRunSimulations.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				simulateButtonPressed();
			}
		});
		optionButtonsPanel.add(btnRunSimulations);
		btnRunSimulations.setWidth("100%");
		Image img4 = new Image(images.runSmall());
		Composite buttonPanel4 = new VerticalPanelButton(img4, "Run simulation");		//this is a custom panel that includes an image and a title
		btnRunSimulations.getElement().appendChild(buttonPanel4.getElement());
		
		
		// re-run simulation button
		Button btnReRun = new Button("");
		btnReRun.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentView.reRunAllSimulations();
			}
		});
		optionButtonsPanel.add(btnReRun);
		btnReRun.setWidth("100%");
		Image img6 = new Image(images.refreshSmall());
		Composite buttonPanel6 = new VerticalPanelButton(img6, "Re-Run ALL");		//this is a custom panel that includes an image and a title
		btnReRun.getElement().appendChild(buttonPanel6.getElement());
		
		
		// SIMULATION OPTIONS
		
		selectSimulationPanel = new DecoratorPanel();
		verticalPanelGlobal.add(selectSimulationPanel);
		
		VerticalPanel verticalSelectSimulationPanel = new VerticalPanel();
		verticalSelectSimulationPanel.setSpacing(4);
		verticalSelectSimulationPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		selectSimulationPanel.setWidget(verticalSelectSimulationPanel);
		
		Image imgRun = new Image(images.runMid());
		verticalSelectSimulationPanel.add(imgRun);
		
		Label lblSelectTypeOf = new Label("Select type of simulation:");
		lblSelectTypeOf.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalSelectSimulationPanel.add(lblSelectTypeOf);
		
		Button btnHydraulic = new Button("Hydraulic analysis");
		btnHydraulic.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				runHydroButtonPressed();
			}
		});
		verticalSelectSimulationPanel.add(btnHydraulic);
		btnHydraulic.setWidth("100%");
		
		Button btnAgeAnalysis = new Button("Age analysis");
		btnAgeAnalysis.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				runAgeButtonPressed();
			}
		});
		verticalSelectSimulationPanel.add(btnAgeAnalysis);
		btnAgeAnalysis.setWidth("100%");
		
		Button btnSourceTraceAnalysis = new Button("Source trace analysis");
		btnSourceTraceAnalysis.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				runSourceTraceButtonPressed();
			}
		});
		verticalSelectSimulationPanel.add(btnSourceTraceAnalysis);
		btnSourceTraceAnalysis.setWidth("100%");
		
		Button btnChemicalConcentrationAnalysis = new Button("Chemical concentration analysis");
		btnChemicalConcentrationAnalysis.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				runChemicalButtonPressed();
			}
		});
		verticalSelectSimulationPanel.add(btnChemicalConcentrationAnalysis);
		btnChemicalConcentrationAnalysis.setWidth("100%");
		
		Label lblSpace = new Label(".");
		verticalSelectSimulationPanel.add(lblSpace);
		
		Button btnCancel = new Button("");
		btnCancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				showNormalView();
			}
		});
		Image img5 = new Image(images.cancelSmall());
		HorizontalPanelButton buttonPanel5 = new HorizontalPanelButton(img5, "Cancel");		//this is a custom panel that includes an image and a title
		btnCancel.getElement().appendChild(buttonPanel5.getElement());
		verticalSelectSimulationPanel.add(btnCancel);
		//btnCancel.setWidth("100%");
	    
	}
	
	private void createDataGrid(){
		
		dataGrid = new DataGrid<Scenario>();
	    dataGrid.setWidth("100%");
	    //dataGrid.setHeight("250px");
	    dataGrid.setHeight("20em");
	    verticalPanelGlobal.add(dataGrid);
	    
	    //options
	    dataGrid.setAutoHeaderRefreshDisabled(true);
	    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
	    pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
	    pager.setDisplay(dataGrid);
	    verticalPanelGlobal.add(pager);
	    
	    // Create Name column.
	    TextColumn<Scenario> nameColumn = new TextColumn<Scenario>() {
	      @Override
	      public String getValue(Scenario scenario) {
	        return scenario.getName();
	      }
	    };

	    // Create StartTime column.
	    TextColumn<Scenario> startTimeColumn = new TextColumn<Scenario>() {
	      @Override
	      public String getValue(Scenario scenario) {
	        return scenario.getStartTime();
	      }
	    };
	    // Create Duration column.
	    TextColumn<Scenario> durationColumn = new TextColumn<Scenario>() {
	      @Override
	      public String getValue(Scenario scenario) {
	        return String.valueOf(scenario.getTotalDuration())+ "hr";
	      }
	    };
	    // Create Iteration column.
	    TextColumn<Scenario> iterationColumn = new TextColumn<Scenario>() {
	      @Override
	      public String getValue(Scenario scenario) {
	        return String.valueOf(scenario.getIterationLength())+ "min";
	      }
	    };
	    // Create hasRun columns.
	    Column<Scenario, ImageResource> hydroHasRunColumn = new Column<Scenario, ImageResource> (new ImageResourceCell())  {
	      @Override
	      public ImageResource getValue(Scenario scenario) {
	        
	    	  if(scenario.getHydroHasRun()){
	    		  return images.tinyTick();
	    	  }else{
	    		  return null;
	    	  }
	      }
	    };
	    // Create hasRun columns.
	    Column<Scenario, ImageResource> ageHasRunColumn = new Column<Scenario, ImageResource> (new ImageResourceCell())  {
	      @Override
	      public ImageResource getValue(Scenario scenario) {
	        
	    	  if(scenario.getAgeHasRun()){
	    		  return images.tinyTick();
	    	  }else{
	    		  return null;
	    	  }
	      }
	    };
	    // Create hasRun columns.
	    Column<Scenario, ImageResource> sourceHasRunColumn = new Column<Scenario, ImageResource> (new ImageResourceCell())  {
	      @Override
	      public ImageResource getValue(Scenario scenario) {
	        
	    	  if(scenario.getSourceHasRun()){
	    		  return images.tinyTick();
	    	  }else{
	    		  return null;
	    	  }
	      }
	    };
	    // Create hasRun columns.
	    Column<Scenario, ImageResource> chemicalHasRunColumn = new Column<Scenario, ImageResource> (new ImageResourceCell())  {
	      @Override
	      public ImageResource getValue(Scenario scenario) {
	        
	    	  if(scenario.getChemicalHasRun()){
	    		  return images.tinyTick();
	    	  }else{
	    		  return null;
	    	  }
	      }
	    };
	    

	    
	    
	    
	    // Add the columns.
	    dataGrid.addColumn(nameColumn, "Name");
	    dataGrid.addColumn(startTimeColumn, "Start time");
	    dataGrid.addColumn(durationColumn, "Duration");
	    dataGrid.addColumn(iterationColumn, "Iteration");
	    dataGrid.addColumn(hydroHasRunColumn, "Hydraulic");
	    dataGrid.addColumn(ageHasRunColumn, "Age");
	    dataGrid.addColumn(sourceHasRunColumn, "Source");
	    dataGrid.addColumn(chemicalHasRunColumn, "Chemical");

	    
	    // Create a data provider.
	    dataProvider = new ListDataProvider<Scenario>();

	    // Connect the list to the data provider.
	    dataProvider.addDataDisplay(dataGrid);
	    //list=dataProvider.getList();
	    
		// selection Model
		selectionModel = new SingleSelectionModel<Scenario>();
		dataGrid.setSelectionModel(selectionModel);
	}
	
	////////////////////////////////////
	// METHODS RELATED WITH EVENTS OF THIS VIEW
	///////////////////////////////////
	
	private void deleteButtonPressed(){
		
		Scenario selected = selectionModel.getSelectedObject();
		if (selected != null) {
			int selectedIndex=dataProvider.getList().indexOf(selected);
			parentView.deleteScenario(selectedIndex);
			//parentView.deleteScenario(selected);			//ALTERNATIVE: could also be deleted by sending the reference to the Scenario object itself. But this implies that the parent view and the presenter have to import the model package and increase coupling
		}else{
			Window.alert("Please select a Scenario!");
		}
	}
	
	private void editButtonPressed(){
		Scenario selected = selectionModel.getSelectedObject();
		if (selected != null) {
			int selectedIndex=dataProvider.getList().indexOf(selected);
			parentView.showEditScenarioView(selectedIndex);
		}else{
			Window.alert("Please select a Scenario!");
		}
	}
	
	private void simulateButtonPressed(){
		Scenario selected = selectionModel.getSelectedObject();
		if (selected != null) {
			showSelectSimulationView();
		}else{
			Window.alert("Please select a Scenario!");
		}
	}
	
	private void viewActionsButtonPressed(){
		
		Scenario selected = selectionModel.getSelectedObject();
		if (selected != null) {
			int selectedIndex=dataProvider.getList().indexOf(selected);
			parentView.showActionsExplorer(selectedIndex,selected.getName());
		}else{
			Window.alert("Please select a Scenario!");
		}
	}
	
	
	private void runHydroButtonPressed(){
		Scenario selected = selectionModel.getSelectedObject();
		if (selected != null) {
			int selectedIndex=dataProvider.getList().indexOf(selected);
			parentView.runSimulation(selectedIndex, "hydraulic");
		}else{
			Window.alert("Please select a Scenario!");
		}
		showNormalView();
	}
	
	private void runAgeButtonPressed(){
		Scenario selected = selectionModel.getSelectedObject();
		if (selected != null) {
			int selectedIndex=dataProvider.getList().indexOf(selected);
			parentView.runSimulation(selectedIndex, "age");
		}else{
			Window.alert("Please select a Scenario!");
		}
		showNormalView();
	}
	
	private void runSourceTraceButtonPressed(){
		Scenario selected = selectionModel.getSelectedObject();
		if (selected != null) {
			int selectedIndex=dataProvider.getList().indexOf(selected);
			parentView.runSimulation(selectedIndex, "sourceTrace");
		}else{
			Window.alert("Please select a Scenario!");
		}
		showNormalView();
	}
	
	private void runChemicalButtonPressed(){
		Scenario selected = selectionModel.getSelectedObject();
		if (selected != null) {
			int selectedIndex=dataProvider.getList().indexOf(selected);
			parentView.runSimulation(selectedIndex, "chemical");
		}else{
			Window.alert("Please select a Scenario!");
		}
		showNormalView();
	}
	
	private void showNormalView(){
		btnNewScenario.setVisible(true);
		dataGrid.setVisible(true);
		pager.setVisible(true);
		optionButtonsPanel.setVisible(true);
		selectSimulationPanel.setVisible(false);
	}
	private void showSelectSimulationView(){
		btnNewScenario.setVisible(false);
		dataGrid.setVisible(false);
		pager.setVisible(false);
		optionButtonsPanel.setVisible(false);
		selectSimulationPanel.setVisible(true);
	}
	
	//////////////////////////////////
	// Methods for updating the list that represents the model of the dataGrid (called by parent view which is called by presenter after any modification in scenarios)
	//////////////////////////////////
	
	//Each time the scenario list of the model has been changed because an scenario has been added, removed, or modified, this method has to be called which sets the list of the DataGrid equal to the list of scenarioss of the model (the presenter gets it using the model manager)
	// ideally, the ListDataProvider can be set to point to a list once, and after that if the list changes the datagrid does it aswell. But I havent been able to set the list of the ListDataProvider to be the list of scenarios of the model
	// the method dataProvider.setList(scenarioModel) only copies the list, and any change to the list being passed as parameter does not affect the ListDataProvider
	// in order to make the DataListProvider able to keep an eye in a list, we have to use list=dataProvider.getList(), and then any change to list will be reflected. Here is where I have the problem because I havent been able to make list equal to the list of scenarios
	
	public void updateScenarioExplorer(List<Scenario> scenarioModel){
		
		selectionModel.clear();
		
		//first set the dataProvider with an empty list. I am doing this because sometimes the datagrid was not refreshing properly and it blocked, and I found out that if I do this first it solve the problem. No idea why. This happened specially if the dataGrid was going to be updated with a List of smaller size than the previous one.
		dataProvider.setList(new ArrayList<Scenario>());
		dataProvider.refresh();
		dataProvider.flush();
		dataGrid.redraw();
		
		dataProvider.setList(scenarioModel);
		dataProvider.refresh();
		dataProvider.flush();
		dataGrid.redraw();
		selectionModel.clear();
	}

	
	public void redrawScenarioExplorer(){
		dataGrid.redraw();
	}

}

//Window.alert(String.valueOf(selectedIndex));
