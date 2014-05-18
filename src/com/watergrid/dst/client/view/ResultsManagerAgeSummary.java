package com.watergrid.dst.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Grid;

public class ResultsManagerAgeSummary extends Composite{

	ResultsManager parentView;
	
	VerticalPanel verticalPanelGlobal;
	
	Grid grid;
	
	//labels to add data
	Label lblMinAge;		//Age
	Label lblMaxAge;
	
	Label lblAlerts;
	Image alertStatus;

	ImageResources images = GWT.create(ImageResources.class);
	
	
	public ResultsManagerAgeSummary(ResultsManager parentView){
		
		this.parentView=parentView;
		initializeComponents();
		
	}
	
	//////////////////////
	// VIEW COMPONENTS
	//////////////////////
	
	private void initializeComponents(){
	
		verticalPanelGlobal = new VerticalPanel();
		verticalPanelGlobal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelGlobal.setSpacing(5);
		verticalPanelGlobal.setWidth("100%");
		//verticalPanelGlobal.setHeight("100%");
		initWidget(verticalPanelGlobal);
		
		Image imgLogo = new Image(images.timerBig());
		verticalPanelGlobal.add(imgLogo);
		
		Label lblTitle = new Label("Age Results Summary");
		verticalPanelGlobal.add(lblTitle);
		
		//TABLE with summary data
		
		//(Row,Column)
		
		DecoratorPanel gridPanel = new DecoratorPanel();
		verticalPanelGlobal.add(gridPanel);

		grid = new Grid(4, 6);
		grid.setCellSpacing(4);
		gridPanel.add(grid);
		
		Label lblMintitle = new Label("MIN ");
		grid.setWidget(0, 2, lblMintitle);
		
		Label lblMaxtitle = new Label("MAX ");
		grid.setWidget(0, 4, lblMaxtitle);
		
		Label lblPressureTitle = new Label("Age (hours): ");
		grid.setWidget(1, 0, lblPressureTitle);
		
		lblMinAge = new Label("0.00");
		grid.setWidget(1, 2, lblMinAge);
		lblMaxAge = new Label("0.00");
		grid.setWidget(1, 4, lblMaxAge);
		
		
		Label lblAlertsTitle = new Label("Alerts: ");
		grid.setWidget(3, 0, lblAlertsTitle);
		
		lblAlerts = new Label("0");
		grid.setWidget(3, 2, lblAlerts);

		
		//buttons

		HorizontalPanel optionButtonsPanel = new HorizontalPanel();
		verticalPanelGlobal.add(optionButtonsPanel);
		verticalPanelGlobal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		optionButtonsPanel.setWidth("100%");
		
		// Back button
		Button btnBack = new Button("");
		btnBack.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentView.activateExplorerMode();
			}
		});
		Image img2 = new Image(images.backSmall());
		VerticalPanelButton buttonPanel2 = new VerticalPanelButton(img2, "Back");		//this is a custom panel that includes an image and a title
		btnBack.getElement().appendChild(buttonPanel2.getElement());
		optionButtonsPanel.add(btnBack);
		btnBack.setWidth("100%");
		
		
		// View on Map
		Button btnViewMap = new Button("");
		btnViewMap.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentView.showResultsOnMap();
			}
		});
		Image img3 = new Image(images.mapSmall());
		VerticalPanelButton buttonPanel3 = new VerticalPanelButton(img3, "View results on map");		//this is a custom panel that includes an image and a title
		btnViewMap.getElement().appendChild(buttonPanel3.getElement());
		optionButtonsPanel.add(btnViewMap);
		btnViewMap.setWidth("100%");
		
		// View Component Details
		Button btnComponentDetails = new Button("");
		btnComponentDetails.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentView.showAgeDetails();
			}
		});
		Image img4 = new Image(images.componentSmall());
		VerticalPanelButton buttonPanel4 = new VerticalPanelButton(img4, "Detailed components' results");		//this is a custom panel that includes an image and a title
		btnComponentDetails.getElement().appendChild(buttonPanel4.getElement());
		optionButtonsPanel.add(btnComponentDetails);
		btnComponentDetails.setWidth("100%");
		
		// View Alerts
		Button btnViewAlerts = new Button("");
		btnViewAlerts.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentView.showAlerts();
			}
		});
		Image img5 = new Image(images.alertSmall());
		VerticalPanelButton buttonPanel5 = new VerticalPanelButton(img5, "View components' alerts");		//this is a custom panel that includes an image and a title
		btnViewAlerts.getElement().appendChild(buttonPanel5.getElement());
		optionButtonsPanel.add(btnViewAlerts);
		btnViewAlerts.setWidth("100%");
		
	}
	
	/////////////////////////
	// Methods related to events of this view
	/////////////////////////
	
	
	///////////////////////////
	// Methods called by parent view
	//////////////////////////
	
	public void loadSummaryData(ArrayList<Float> summaryData){
		
		lblMinAge.setText(String.valueOf(summaryData.get(0)));		
		lblMaxAge.setText(String.valueOf(summaryData.get(1)));
		
	}
	
	public void loadAlertsData(int alertCount){
		
		lblAlerts.setText(String.valueOf(alertCount));
		
		if (alertCount==0){
			alertStatus= new Image(images.successMid());
			grid.setWidget(3, 4, alertStatus);
		}else{
			alertStatus= new Image(images.stopMid());
			grid.setWidget(3, 4, alertStatus);
		}
	}
	
}
