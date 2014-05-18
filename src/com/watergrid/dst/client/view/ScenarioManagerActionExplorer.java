package com.watergrid.dst.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.watergrid.dst.client.model.ScenarioAction;
import com.google.gwt.user.client.ui.Label;

public class ScenarioManagerActionExplorer extends Composite{

	ScenarioManager parentView;								//this view is contained in a ScenarioManager view. We need reference to it to inform of events, and then the parent view will inform the presenter
	
	VerticalPanel verticalPanelGlobal;						//parts of view (we need to hide and show)
	HorizontalPanel optionButtonsPanel;
	Label lblScenarioname;
	
	DataGrid<ScenarioAction> dataGrid;
	SimplePager pager;
	
	List<ScenarioAction> list;								//list that represents the model
	ListDataProvider<ScenarioAction> dataProvider;
	SingleSelectionModel<ScenarioAction> selectionModel;			//selection model so we can read the selected object
	
	ImageResources images = GWT.create(ImageResources.class);
	
	
	public ScenarioManagerActionExplorer(ScenarioManager parentView){
		
		this.parentView=parentView;
		initializeComponents();
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
		
		Image imgAction = new Image(images.jobMid());
		verticalPanelGlobal.add(imgAction);
		
		lblScenarioname = new Label("ScenarioName");
		verticalPanelGlobal.add(lblScenarioname);
		
		//data grid
		
		createDataGrid();
		
		//data grid option buttons
		
		optionButtonsPanel = new HorizontalPanel();
		verticalPanelGlobal.add(optionButtonsPanel);
		optionButtonsPanel.setWidth("100%");
		
		// Return button
		Button btnReturn = new Button("");
		btnReturn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentView.activateNormalMode();
			}
		});
		Image img = new Image(images.backSmall());
		VerticalPanelButton buttonPanel2 = new VerticalPanelButton(img, "Return");		//this is a custom panel that includes an image and a title
		btnReturn.getElement().appendChild(buttonPanel2.getElement());
		optionButtonsPanel.add(btnReturn);
		btnReturn.setWidth("100%");
		
		//delete button
		Button btnDelete = new Button("");
		btnDelete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deleteActionButtonPressed();
			}
		});
		Image img2 = new Image(images.trashSmall());
		VerticalPanelButton buttonPanel3 = new VerticalPanelButton(img2, "Delete Action");		//this is a custom panel that includes an image and a title
		btnDelete.getElement().appendChild(buttonPanel3.getElement());
		optionButtonsPanel.add(btnDelete);
		btnDelete.setWidth("100%");
		
		// view on map button
		Button btnViewComp = new Button("");
		btnViewComp.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				viewOnMapButtonPressed();
			}
		});
		Image img3 = new Image(images.mapSmall());
		VerticalPanelButton buttonPanel4 = new VerticalPanelButton(img3, "View on map");		//this is a custom panel that includes an image and a title
		btnViewComp.getElement().appendChild(buttonPanel4.getElement());
		optionButtonsPanel.add(btnViewComp);
		btnViewComp.setWidth("100%");
	}
	
	private void createDataGrid(){
		

		
		dataGrid = new DataGrid<ScenarioAction>();
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
	    
	    // Create Columns
	    
		TextColumn<ScenarioAction> typeColumn = new TextColumn<ScenarioAction>() {
			@Override
			public String getValue(ScenarioAction scenarioAction) {
				return scenarioAction.getComponentType();
			}
		};

		TextColumn<ScenarioAction> idColumn = new TextColumn<ScenarioAction>() {
			@Override
			public String getValue(ScenarioAction scenarioAction) {
				return scenarioAction.getComponentId();
			}
		};
		
		TextColumn<ScenarioAction> actionColumn = new TextColumn<ScenarioAction>() {
			@Override
			public String getValue(ScenarioAction scenarioAction) {
				return scenarioAction.getComponentAction();
			}
		};
		
		TextColumn<ScenarioAction> actionTimeColumn = new TextColumn<ScenarioAction>() {
			@Override
			public String getValue(ScenarioAction scenarioAction) {
				return scenarioAction.getActionTime();
			}
		};
		
		TextColumn<ScenarioAction> actionDateColumn = new TextColumn<ScenarioAction>() {
			@Override
			public String getValue(ScenarioAction scenarioAction) {
				return scenarioAction.getActionDate();
			}
		};

	    // Add the columns.
	    dataGrid.addColumn(typeColumn, "Type");
	    dataGrid.addColumn(idColumn, "ID");
	    dataGrid.addColumn(actionColumn, "Action");
	    dataGrid.addColumn(actionTimeColumn, "Time");
	    dataGrid.addColumn(actionDateColumn, "Date");

	    // Create a data provider.
	    dataProvider = new ListDataProvider<ScenarioAction>();

	    // Connect the list to the data provider.
	    dataProvider.addDataDisplay(dataGrid);
	    //list=dataProvider.getList();
	    
		// selection Model
		selectionModel = new SingleSelectionModel<ScenarioAction>();
		dataGrid.setSelectionModel(selectionModel);
	}
	
	////////////////////////////////////
	// METHODS RELATED WITH EVENTS OF THIS VIEW
	///////////////////////////////////
	
	
	private void deleteActionButtonPressed(){
		
		ScenarioAction selected = selectionModel.getSelectedObject();
		if (selected != null) {
			int selectedIndex=dataProvider.getList().indexOf(selected);
			parentView.deleteAction(selectedIndex);
		}else{
			Window.alert("Please select an action!");
		}
	}
	
	
	private void viewOnMapButtonPressed(){
		ScenarioAction selected = selectionModel.getSelectedObject();
		if (selected != null) {
			String componentId =selected.getComponentId();
			String componentType=selected.getComponentType();
			int componentIndex=selected.getComponentIndex();
			parentView.highlightComponent(componentType, componentIndex, componentId);

		}else{
			Window.alert("Please select an component!");
		}
	}
	
	
	//////////////////////////////////
	// Methods for updating the list that represents the model of the dataGrid (called by parent view when the user desires to see the actions of a selected scenario)
	//////////////////////////////////
	
	
	public void updateActionExplorer(List<ScenarioAction> actionModel){

		selectionModel.clear();
		
		//first set the dataProvider with an empty list. I am doing this because sometimes the datagrid was not refreshing properly and it blocked, and I found out that if I do this first it solve the problem. No idea why. This happened specially if the dataGrid was going to be updated with a List of smaller size than the previous one.
		dataProvider.setList(new ArrayList<ScenarioAction>());
		dataProvider.refresh();
		dataProvider.flush();
		dataGrid.redraw();
		
		dataProvider.setList(actionModel);
		dataProvider.refresh();
		dataProvider.flush();
		dataGrid.redraw();
		selectionModel.clear();
	}
	
	public void redrawActionExplorer(){
		dataGrid.redraw();
	}
	
	public void informScenarioName(String name){
		
		lblScenarioname.setText("Scenario: "+name);
	}
}
