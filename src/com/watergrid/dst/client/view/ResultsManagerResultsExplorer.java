package com.watergrid.dst.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.watergrid.dst.client.dataobjects.ResultsSummary;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class ResultsManagerResultsExplorer extends Composite{
	
	ResultsManager parentView;
	
	VerticalPanel verticalPanelGlobal;
	
	DataGrid<ResultsSummary> dataGrid;
	SimplePager pager;
	List<ResultsSummary> list;								//list that represents the model
	ListDataProvider<ResultsSummary> dataProvider;
	SingleSelectionModel<ResultsSummary> selectionModel;	//selection model so we can read the selected object
	
	ImageResources images = GWT.create(ImageResources.class);
	
	public ResultsManagerResultsExplorer(ResultsManager parentView){
		
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
		
		Image imgLogo = new Image(images.resultsBig());
		verticalPanelGlobal.add(imgLogo);
		
		//DATAGRID
		createDataGrid();
		
		//buttons
		
		//use this panel in case we want to add more buttons
		HorizontalPanel optionButtonsPanel = new HorizontalPanel();
		verticalPanelGlobal.add(optionButtonsPanel);
		verticalPanelGlobal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		optionButtonsPanel.setWidth("100%");
		//
		
		// View button button
		Button btnViewResults = new Button("");
		btnViewResults.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				viewResultsButtonPressed();
			}
		});
		Image img = new Image(images.resultsMid());
		HorizontalPanelButton buttonPanel2 = new HorizontalPanelButton(img, "View Results");		//this is a custom panel that includes an image and a title
		btnViewResults.getElement().appendChild(buttonPanel2.getElement());
		verticalPanelGlobal.add(btnViewResults);
		btnViewResults.setWidth("30%");
		
	}
	
	
	private void createDataGrid(){
		
		dataGrid = new DataGrid<ResultsSummary>();
	    dataGrid.setWidth("100%");
	    //dataGrid.setHeight("250px");
	    dataGrid.setHeight("18em");
	    verticalPanelGlobal.add(dataGrid);
	    
	    //options
	    dataGrid.setAutoHeaderRefreshDisabled(true);
	    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
	    pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
	    pager.setDisplay(dataGrid);
	    verticalPanelGlobal.add(pager);
	    
	    // Create Name column.
	    TextColumn<ResultsSummary> scenarioColumn = new TextColumn<ResultsSummary>() {
	      @Override
	      public String getValue(ResultsSummary summary) {
	        return summary.getScenarioName();
	      }
	    };

	    // Create StartTime column.
	    TextColumn<ResultsSummary> simTypeColumn = new TextColumn<ResultsSummary>() {
	      @Override
	      public String getValue(ResultsSummary summary) {
	        return summary.getSimulationType();
	      }
	    };
	    // Create Duration column.
	    TextColumn<ResultsSummary> timeColumn = new TextColumn<ResultsSummary>() {
	      @Override
	      public String getValue(ResultsSummary summary) {
	        return summary.getResultsTime();
	      }
	    };
	    
	    
	    // Add the columns.
	    dataGrid.addColumn(scenarioColumn, "Scenario");
	    dataGrid.addColumn(simTypeColumn, "Simulation Type");
	    dataGrid.addColumn(timeColumn, "Results received at");

	    // Create a data provider.
	    dataProvider = new ListDataProvider<ResultsSummary>();

	    // Connect the list to the data provider.
	    dataProvider.addDataDisplay(dataGrid);
	    //list=dataProvider.getList();
	    
		// selection Model
		selectionModel = new SingleSelectionModel<ResultsSummary>();
		dataGrid.setSelectionModel(selectionModel);		
	}
	
	
	///////////////////////
	//  Methods related with events of this view
	///////////////////////
	
	
	//inform the parent view that user desires to view results, sending scenario index (not the index in the dataGrid of results, the real scenario index) and the simulation type
	private void viewResultsButtonPressed(){
		
		ResultsSummary selected = selectionModel.getSelectedObject();
		if (selected != null) {
			parentView.showResultsSummary(selected.getScenarioIndex(), selected.getScenarioName(),selected.getSimulationType());
		}else{
			Window.alert("Please select a simulation results!");
		}
		
	}
	
	//for updating the DataGrid
	public void updateResultsExplorer(List<ResultsSummary> summaryModel){
		
		selectionModel.clear();
		
		//first set the dataProvider with an empty list. I am doing this because sometimes the dataGrid was not refreshing properly and it blocked, and I found out that if I do this first it solve the problem. No idea why. This happened specially if the dataGrid was going to be updated with a List of smaller size than the previous one.
		dataProvider.setList(new ArrayList<ResultsSummary>());
		dataProvider.refresh();
		dataProvider.flush();
		dataGrid.redraw();
		
		dataProvider.setList(summaryModel);
		dataProvider.refresh();
		dataProvider.flush();
		dataGrid.redraw();
		selectionModel.clear();
		
		dataGrid.onResize();
	}
	
	public void redrawResultsExplorer(){
		dataGrid.redraw();
	}
	
	

}
