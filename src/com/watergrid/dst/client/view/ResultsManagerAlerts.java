package com.watergrid.dst.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.watergrid.dst.client.dataobjects.AlertInfo;
import com.watergrid.dst.client.dataobjects.NodeAgeResult;
import com.watergrid.dst.client.dataobjects.NodeChemicalResult;

public class ResultsManagerAlerts extends Composite{
	
	ResultsManager parentView;
	
	VerticalPanel verticalPanelGlobal;
	
	//
	Image alertImage;
	Label lblAlertTitle;
	Image noAlertImage;
	Label lblNoAlertTitle;
	Button btnViewNodeMap;
	
	//for data Grid
	DataGrid<AlertInfo> alertDataGrid;
	SimplePager alertPager;
	ListDataProvider<AlertInfo> alertDataProvider;
	SingleSelectionModel<AlertInfo> alertSelectionModel;
	List<AlertInfo> alertList;
	
	ImageResources images = GWT.create(ImageResources.class);
	
	public ResultsManagerAlerts(ResultsManager parentView){
		
		this.parentView=parentView;
		initializeComponents();
	}
	
	private void initializeComponents() {

		verticalPanelGlobal = new VerticalPanel();
		verticalPanelGlobal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelGlobal.setSpacing(3);
		verticalPanelGlobal.setWidth("100%");
		//verticalPanelGlobal.setHeight("100%");
		initWidget(verticalPanelGlobal);
		
		
		alertImage = new Image(images.alertBig());
		verticalPanelGlobal.add(alertImage);
		
		lblAlertTitle = new Label("WARNING! the network presents the following alerts:");
		lblAlertTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelGlobal.add(lblAlertTitle);
		
		noAlertImage = new Image(images.successExtraBig());
		verticalPanelGlobal.add(noAlertImage);
		
		lblNoAlertTitle = new Label("The network does not present any type of alerts!");
		lblNoAlertTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelGlobal.add(lblNoAlertTitle);
		
		//data Grid
		
		createDataGrid();
		
		//Buttons

		HorizontalPanel optionButtonsPanel = new HorizontalPanel();
		verticalPanelGlobal.add(optionButtonsPanel);
		verticalPanelGlobal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		// optionButtonsPanel.setWidth("100%");

		// Back button
		Button btnBack = new Button("");
		btnBack.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentView.backFromAlerts();
			}
		});
		Image img2 = new Image(images.backSmall());
		VerticalPanelButton buttonPanel2 = new VerticalPanelButton(img2,"Back to summary"); // this is a custom panel that includes an image and a title
		btnBack.getElement().appendChild(buttonPanel2.getElement());
		optionButtonsPanel.add(btnBack);
		// btnBack.setWidth("100%");

		
		// View Component on MAP
		btnViewNodeMap = new Button("");
		btnViewNodeMap.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				viewOnMapButtonPresed();
			}
		});
		Image img3 = new Image(images.mapSmall());
		VerticalPanelButton buttonPanel3 = new VerticalPanelButton(img3,"View Component on map");
		btnViewNodeMap.getElement().appendChild(buttonPanel3.getElement());
		optionButtonsPanel.add(btnViewNodeMap);
		// btnViewNodeMap.setWidth("100%");
		
	}
	
	//////////////////////
	// Alerts DataGrid
	//////////////////////
	
	private void createDataGrid(){
		
		alertDataGrid = new DataGrid<AlertInfo>();
		alertDataGrid.setWidth("100%");
		alertDataGrid.setHeight("19em");
		verticalPanelGlobal.add(alertDataGrid);
		// options
		alertDataGrid.setAutoHeaderRefreshDisabled(true);
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		alertPager = new SimplePager(TextLocation.CENTER, pagerResources, false,0, true);
		//alertPager.setDisplay(alertDataGrid);
		//verticalPanelGlobal.add(alertPager);
		alertDataGrid.setPageSize(10000);
		
		// Create ID column.
		TextColumn<AlertInfo> idColumn = new TextColumn<AlertInfo>() {
			@Override
			public String getValue(AlertInfo alert) {
				return alert.getComponentId();
			}
		};
		// Create Variable HIGH column.
		TextColumn<AlertInfo> varHighColumn = new TextColumn<AlertInfo>() {
			@Override
			public String getValue(AlertInfo alert) {
				return alert.getVariablesHighValueString();
			}
		};
		// Create Variable LOW column.
		TextColumn<AlertInfo> varLowColumn = new TextColumn<AlertInfo>() {
			@Override
			public String getValue(AlertInfo alert) {
				return alert.getVariablesLowValueString();
			}
		};
		
		// Add the columns.
		alertDataGrid.addColumn(idColumn, "component ID");
		alertDataGrid.addColumn(varHighColumn, "HIGH value Alert");
		alertDataGrid.addColumn(varLowColumn, "LOW value Alert");
		
		// Create a data provider.
		alertDataProvider = new ListDataProvider<AlertInfo>();

		// Connect the list to the data provider.
		alertDataProvider.addDataDisplay(alertDataGrid);
		alertList=alertDataProvider.getList();

		// selection Model
		alertSelectionModel = new SingleSelectionModel<AlertInfo>();
		alertDataGrid.setSelectionModel(alertSelectionModel);
		
	}
	
	/////////////////////////////
	//  Methods related to events of this view
	///////////////////////////
	
	private void viewOnMapButtonPresed(){
	
		//get the selected ID and call the parent view
		AlertInfo selectedAlert = alertSelectionModel.getSelectedObject();
		if(selectedAlert != null){
			parentView.highlightComponent(selectedAlert.getComponentId());
		}else{
			Window.alert("Please select a component!");
		}
		
	}
	
	
	/////////////////////////////////
	// METHODS to load the dataGrids
	/////////////////////////////////
	
	//for updating the DataGrid
	public void loadAlertsData(List<AlertInfo> data){
		
		alertSelectionModel.clear();

		alertDataProvider.refresh();
		alertDataProvider.flush();
		alertDataGrid.redraw();
		
		alertList.clear();						//done this way,because with the other way we did it (like in scenario explorer) the sorter didn't work
		for (int i =0;i<data.size();i++){
			alertList.add(data.get(i));
		}
		
		alertDataProvider.refresh();
		alertDataProvider.flush();
		alertDataGrid.redraw();
		alertSelectionModel.clear();
		alertDataGrid.onResize();
		
		
		if(data.size()>0){
			//alerts
			activateAlertsMode();
			
		}else{
			//NO alerts
			activateNoAlertsMode();
		}
		
	}

	public void redrawDataGrids(){
		alertDataGrid.redraw();
	}
	
	private void activateNoAlertsMode(){
		alertDataGrid.setVisible(false);
		lblAlertTitle.setVisible(false);
		alertImage.setVisible(false);
		lblNoAlertTitle.setVisible(true);
		noAlertImage.setVisible(true);
		btnViewNodeMap.setVisible(false);
	}
	
	private void activateAlertsMode(){
		alertDataGrid.setVisible(true);
		lblAlertTitle.setVisible(true);
		alertImage.setVisible(true);
		lblNoAlertTitle.setVisible(false);
		noAlertImage.setVisible(false);
		btnViewNodeMap.setVisible(true);
	}

}
