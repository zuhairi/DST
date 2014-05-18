package com.watergrid.dst.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.watergrid.dst.client.presenter.AddActionPresenter;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.i18n.client.NumberFormat;

public class AddAction extends Composite{
	
	AddActionPresenter presenter;
	
	ListBox cmbScenario;
	ListBox cmbAction;
	ListBox cmbTime;
	
	ImageResources images = GWT.create(ImageResources.class);
	
	public AddAction(){
	
		initializeComponents();
	}
	
	//this method sets the presenter who will be called by events
	public void registerPresenter(AddActionPresenter presenter){
		this.presenter=presenter;
	}
	
	
	/////////////////
	// VIEW COMPONENTS
	////////////////
	
	private void initializeComponents(){
		
		DecoratorPanel decoratorPanelDetails = new DecoratorPanel();
		decoratorPanelDetails = new DecoratorPanel();
		decoratorPanelDetails.setWidth("100%");
		initWidget(decoratorPanelDetails);
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(3);
		decoratorPanelDetails.setWidget(verticalPanel);
		verticalPanel.setWidth("100%");
		
		Label lblTitle = new Label("NEW ACTION");
		lblTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(lblTitle);
		
		cmbScenario = new ListBox();
		cmbScenario.addChangeHandler(new ChangeHandler() {		//this method is to update the list box of possible action time when the selected scenario has changed. 
			public void onChange(ChangeEvent event) {
				if(cmbScenario.getSelectedIndex()==0){
					cmbTime.clear();
					cmbTime.addItem("--Select Time--");
				}else{
					presenter.informChangeInSelectedScenario(cmbScenario.getSelectedIndex()-1);		//subtract 1 because of the first option
				}
			}
		});
		
		verticalPanel.add(cmbScenario);
		cmbScenario.setWidth("100%");
		cmbScenario.addItem("--Select Scenario--");
		
		cmbAction = new ListBox();
		verticalPanel.add(cmbAction);
		cmbAction.setWidth("100%");
		cmbAction.addItem("--Select Action--");
		cmbAction.addItem("open");
		cmbAction.addItem("closed");
		
		cmbTime = new ListBox();
		verticalPanel.add(cmbTime);
		cmbTime.setWidth("100%");
		cmbTime.addItem("--Select Time--");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setWidth("100%");
		
		
		Button btnCancel = new Button("", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	hideResetView();
	        }
	      });
		horizontalPanel.add(btnCancel);
		btnCancel.setWidth("100%");
		Image img = new Image(images.cancelSmall());
		Composite buttonPanel = new HorizontalPanelButton(img, "Cancel");		//this is a custom panel that includes an image and a title
		btnCancel.getElement().appendChild(buttonPanel.getElement());
		
		
		Button btnAdd = new Button("", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	
	        	if(cmbScenario.getSelectedIndex()>0 && cmbAction.getSelectedIndex()>0 && cmbTime.getSelectedIndex()>0){
	        		addActionEvent();
	        	}
	        }
	      });
		horizontalPanel.add(btnAdd);
		btnAdd.setWidth("100%");
		Image img2 = new Image(images.addSmall());
		Composite buttonPanel2 = new HorizontalPanelButton(img2, "Add");		//this is a custom panel that includes an image and a title
		btnAdd.getElement().appendChild(buttonPanel2.getElement());
		
		this.setVisible(false);		//starts hiding
	}
	

	
	
	/////////////////////////////////
	// METHODS RELATED TO EVENTS OF THIS VIEW
	/////////////////////////////////
	
	private void addActionEvent(){
		
		int scenarioIndex=cmbScenario.getSelectedIndex()-1;
		String action=cmbAction.getItemText(cmbAction.getSelectedIndex());
		String actionTime=cmbTime.getItemText(cmbTime.getSelectedIndex());
		String actionDate="FIX THIS";
		
		
		if(actionTime.indexOf(" (")>-1){
			actionTime=(actionTime.split(" ("))[0];				//remove the day information if it exists e.g. 10:30 ( + 1 day)
		}

		presenter.addAction(scenarioIndex,action,actionTime,actionDate);
		
		
		hideResetView();
		
	}
	
	private void hideResetView(){

		cmbScenario.setSelectedIndex(0);
		cmbAction.setSelectedIndex(0);
		cmbTime.setSelectedIndex(0);
		this.setVisible(false);
	}
	
	
	/////////////////////////////////
	// METHODS CALLED BY PRESENTER
	/////////////////////////////////
	
	public void reLoadScenarioList(ArrayList<String> scenarioNames){
		cmbScenario.clear();
		cmbScenario.addItem("--Select Scenario--");
		
		for(int i=0;i<scenarioNames.size();i++){
			cmbScenario.addItem(scenarioNames.get(i));
		}
		//also clear the cmb of possible times
		cmbTime.clear();
		cmbTime.addItem("--Select Time--");
	}
	
	public void loadTimeList(String startTime, int totalDuration, int iterationLength){
		
		//this method generates the values of the list box with all the possible times for an action depending on the start time, the total duration and the length of each iteration
		
		cmbTime.clear();
		cmbTime.addItem("--Select Time--");
		
		String[] startTimeStringArray = startTime.split(":");
		int startTimeHH = Integer.valueOf(startTimeStringArray[0]);
		int startTimeMM = Integer.valueOf(startTimeStringArray[1]);
		
		int startTimeInMinutes=(startTimeHH*60)+startTimeMM;
		int numberOfIterations=(totalDuration*60)/iterationLength;
		
		for(int i=0;i<numberOfIterations;i++){
			
			int iterationTimeMinutes=startTimeInMinutes+(i*iterationLength);
			
			int iterationTimeDAY = iterationTimeMinutes/1440;
			int iterationTimeHH = (iterationTimeMinutes-(iterationTimeDAY*1440))/60;
			int iterationTimeMM = iterationTimeMinutes-(iterationTimeHH*60)-(iterationTimeDAY*1440);
			
			String HH = NumberFormat.getFormat("00").format(iterationTimeHH);
			String MM = NumberFormat.getFormat("00").format(iterationTimeMM);
			String cmbItemString =HH+":"+MM;
			
			if(iterationTimeDAY>0){
				cmbItemString=cmbItemString+" ( + "+ iterationTimeDAY +" day)";
			}
			cmbTime.addItem(cmbItemString);
		}
		
	}
	
	
	

}
