package com.watergrid.dst.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class ScenarioManagerNewScenario extends Composite{

	ScenarioManager parentView;			//this view is contained in a ScenarioManager view. We need reference to it to inform of events, and then the parent view will inform the presenter
	
	//components we need to reed info from
	TextBox txtName;
	ListBox cmbTotalDuration;
	ListBox cmbIteration;
	ListBox cmbStartHour;
	ListBox cmbStartMinutes;
	
	Label lblSourceNodeId;
	ListBox cmbSourceNode;
	Label lblBulkCoefficient;
	TextBox txtBulk;
	Label lblWallCoefficient;
	TextBox txtWall;
	Label lblChemicalCons;
	TextBox txtChemical;
	
	//variables that represent the input of the user
	String selectedType;
	String name;
	int totalDuration;
	int iterationLength;
	String startHour;
	String startMinutes;
	String startTime;
	String sourceNodeId;
	float bulkCoeff;
	float wallCoeff;
	float chemicalCons;
	
	Label lblMessage;
	private Label lblNewLabel;
	
	ImageResources images = GWT.create(ImageResources.class);
	
	public ScenarioManagerNewScenario(ScenarioManager parentView){
		
		this.parentView=parentView;
		initializeComponents();
		loadComboBoxes();
		
		//default
		cmbTotalDuration.setSelectedIndex(3);
		cmbIteration.setSelectedIndex(2);
		txtBulk.setText("-1.0");
		txtWall.setText("-1.0");
		txtChemical.setText("1.0");	
	}
	
	
	/////////////////////
	//  VIEW COMPONENTS
	////////////////////
	
	private void initializeComponents(){
		
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel = new DecoratorPanel();
		//decoratorPanel.setWidth("100%");
		initWidget(decoratorPanel);
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setSpacing(3);
		decoratorPanel.setWidget(verticalPanel);
		
		Image imgnew = new Image(images.newMid());
		verticalPanel.add(imgnew);
		
		lblNewLabel = new Label("Enter scenario details and parameters");
		verticalPanel.add(lblNewLabel);
		//verticalPanel.setWidth("100%");
		
		Grid gridCommon = new Grid(9, 2);
		verticalPanel.add(gridCommon);
		
		
		Label lblScenarioName = new Label("Scenario name:");
		gridCommon.setWidget(1, 0, lblScenarioName);
		
		txtName = new TextBox();
		gridCommon.setWidget(1, 1, txtName);
		
		Label lblStartTime = new Label("Start time (hh:mm):");
		gridCommon.setWidget(2, 0, lblStartTime);
		
		HorizontalPanel horizontalPanel_time = new HorizontalPanel();
		horizontalPanel_time.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		gridCommon.setWidget(2, 1, horizontalPanel_time);
		horizontalPanel_time.setWidth("100%");
		
		cmbStartHour = new ListBox();
		horizontalPanel_time.add(cmbStartHour);
		cmbStartHour.setWidth("100%");
		
		horizontalPanel_time.add(new Label("  :  "));
		
		cmbStartMinutes = new ListBox();
		horizontalPanel_time.add(cmbStartMinutes);
		cmbStartMinutes.setWidth("100%");
		
		Label lblDurationhours = new Label("Duration (hours):");
		gridCommon.setWidget(3, 0, lblDurationhours);
		
		cmbTotalDuration= new ListBox();
		gridCommon.setWidget(3, 1, cmbTotalDuration);
		cmbTotalDuration.setWidth("100%");
		
		Label lblIterationLengthminutes = new Label("Iteration length (minutes):");
		gridCommon.setWidget(4, 0, lblIterationLengthminutes);
		
		cmbIteration= new ListBox();
		gridCommon.setWidget(4, 1, cmbIteration);
		cmbIteration.setWidth("100%");
		
		lblSourceNodeId = new Label("Source node ID:");
		gridCommon.setWidget(5, 0, lblSourceNodeId);
		
		cmbSourceNode = new ListBox();
		gridCommon.setWidget(5, 1, cmbSourceNode);
		cmbSourceNode.setWidth("100%");
		
		lblBulkCoefficient = new Label("Bulk coefficient:");
		gridCommon.setWidget(6, 0, lblBulkCoefficient);
		
		txtBulk = new TextBox();
		gridCommon.setWidget(6, 1, txtBulk);
		
		lblWallCoefficient = new Label("Wall coefficient:");
		gridCommon.setWidget(7, 0, lblWallCoefficient);
		
		txtWall = new TextBox();
		gridCommon.setWidget(7, 1, txtWall);
		
		lblChemicalCons = new Label("Chemical Cons:");
		gridCommon.setWidget(8, 0, lblChemicalCons);
		
		txtChemical = new TextBox();
		gridCommon.setWidget(8, 1, txtChemical);
		
		lblMessage = new Label("");
		verticalPanel.add(lblMessage);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setWidth("100%");
		
		Button btnCancel = new Button("");
		btnCancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				reset();
				parentView.activateNormalMode();
			}
		});
		Image img = new Image(images.cancelSmall());
		Composite buttonPanel = new HorizontalPanelButton(img, "Cancel");		//this is a custom panel that includes an image and a title
		btnCancel.getElement().appendChild(buttonPanel.getElement());
		horizontalPanel.add(btnCancel);
		btnCancel.setWidth("100%");
		
		Button btnSave = new Button("");
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				create();
			}
		});
		Image img2 = new Image(images.okSmall());
		Composite buttonPanel2 = new HorizontalPanelButton(img2, "Create");		//this is a custom panel that includes an image and a title
		btnSave.getElement().appendChild(buttonPanel2.getElement());
		horizontalPanel.add(btnSave);
		btnSave.setWidth("100%");
		
	}
	
	private void loadComboBoxes(){
		
		cmbTotalDuration.addItem("1");
		cmbTotalDuration.addItem("6");
		cmbTotalDuration.addItem("12");
		cmbTotalDuration.addItem("24");
		cmbTotalDuration.addItem("48");
		
		cmbIteration.addItem("5");
		cmbIteration.addItem("10");
		cmbIteration.addItem("15");
		cmbIteration.addItem("20");
		cmbIteration.addItem("30");

		cmbStartHour.addItem("00");
		cmbStartHour.addItem("01");
		cmbStartHour.addItem("02");
		cmbStartHour.addItem("03");
		cmbStartHour.addItem("04");
		cmbStartHour.addItem("05");
		cmbStartHour.addItem("06");
		cmbStartHour.addItem("07");
		cmbStartHour.addItem("08");
		cmbStartHour.addItem("09");
		cmbStartHour.addItem("10");
		cmbStartHour.addItem("11");
		cmbStartHour.addItem("12");
		cmbStartHour.addItem("13");
		cmbStartHour.addItem("14");
		cmbStartHour.addItem("15");
		cmbStartHour.addItem("16");
		cmbStartHour.addItem("17");
		cmbStartHour.addItem("18");
		cmbStartHour.addItem("19");
		cmbStartHour.addItem("20");
		cmbStartHour.addItem("21");
		cmbStartHour.addItem("22");
		cmbStartHour.addItem("23");
		cmbStartMinutes.addItem("00");
		cmbStartMinutes.addItem("15");
		cmbStartMinutes.addItem("30");
		cmbStartMinutes.addItem("45");
		//the cmb of SourceNodeId is populated by the presenter after the model has been created because the items correspond to the reservoirs
	}
	
	//called by presenter to add reservoir ids to listbox
	public void addSourceNodeId(String sourceNodeId){
		cmbSourceNode.addItem(sourceNodeId);
	}
	
	//////////////////////////
	// VIEW EVENTS
	//////////////////////////
	
	private void reset(){
		//reset all the fields
		cmbStartHour.setSelectedIndex(0);
		cmbStartMinutes.setSelectedIndex(0);
		cmbSourceNode.setSelectedIndex(0);
		txtName.setText("");
		cmbTotalDuration.setSelectedIndex(3);
		cmbIteration.setSelectedIndex(2);
		txtBulk.setText("-1.0");
		txtWall.setText("-1.0");
		txtChemical.setText("1.0");	
		lblMessage.setText("");
	}
	
	
	private void create(){
		
		//call the presenter (via the parentView) to save the scenario. read and validate data first
		
			if(readValidateData()){
				parentView.createScenario(name, startTime, totalDuration, iterationLength, sourceNodeId, bulkCoeff, wallCoeff, chemicalCons);
				reset();
			}
	}
	
	
	////////////////////////////////////////////////
	// METHOD TO READ AND VALIDATE USER INPUTS 
	////////////////////////////////////////////////
	
	//this method returns true if everything is OK
	//first we check if all data has been entered
	//then if the data is correct (they are numbers) using a try catch block when parsing values from strings

	private boolean readValidateData(){
		
		boolean isDataOK;
		
		if(txtName.getText().equals("") || txtBulk.getText().equals("") || txtWall.getText().equals("") || txtChemical.getText().equals("")){
			isDataOK=false;
			lblMessage.setText("Please enter all the information");
		}
		else{
			try{
				name = txtName.getText();
				totalDuration = Integer.valueOf(cmbTotalDuration.getItemText(cmbTotalDuration.getSelectedIndex()));
				iterationLength = Integer.valueOf(cmbIteration.getItemText(cmbIteration.getSelectedIndex()));
				startHour = cmbStartHour.getItemText(cmbStartHour.getSelectedIndex());
				startMinutes = cmbStartMinutes.getItemText(cmbStartMinutes.getSelectedIndex());
				startTime = startHour+":"+startMinutes;
				sourceNodeId = cmbSourceNode.getItemText(cmbSourceNode.getSelectedIndex());
				bulkCoeff = Float.valueOf(txtBulk.getText());
				wallCoeff = Float.valueOf(txtWall.getText());
				chemicalCons = Float.valueOf(txtChemical.getText());
				isDataOK=true;
				
			}catch(Exception e){
				isDataOK=false;
				lblMessage.setText("Error in input value");
			}
		}
		return isDataOK;
	}
	
}
