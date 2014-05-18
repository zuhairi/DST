package com.watergrid.dst.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.i18n.client.NumberFormat;

public class MapResultsControlPanelAge extends Composite{
	
	MapResultsControlPanel parentView;
	
	VerticalPanel verticalPanelGlobal;
	
	ListBox cmbNode;
	ListBox cmbTimeStep;
	ListBox cmbAnimationSpeed;
	
	//variables of the scales
	Grid nodeScaleGrid;
	Label lblNodeScaleTitle;
	Label lblNodelevel5;
	Label lblNodelevel4;
	Label lblNodelevel3;
	Label lblNodelevel2;
	Label lblNodelevel1;
	
	Timer timer;
	int animationDelay=0;
	
	ImageResources images = GWT.create(ImageResources.class);
	
	
	public MapResultsControlPanelAge(MapResultsControlPanel parentView){
		
		this.parentView=parentView;
		initializeComponents();
		
	}
	
	//////////////////////
	// VIEW COMPONENTS
	//////////////////////
	
	private void initializeComponents(){
		
		verticalPanelGlobal = new VerticalPanel();
		verticalPanelGlobal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelGlobal.setSpacing(2);
		verticalPanelGlobal.setWidth("100%");
		//verticalPanelGlobal.setHeight("100%");
		initWidget(verticalPanelGlobal);
		
		
		//nodes title
		Label lblNodesTitle = new Label("Nodes variable:");
		lblNodesTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		verticalPanelGlobal.add(lblNodesTitle);
		
		//nodes cmb
		cmbNode = new ListBox();
		cmbNode.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				nodeVariableChanged();
			}
		});
		cmbNode.setWidth("100%");
		verticalPanelGlobal.add(cmbNode);
		
		//node scale GRID
		nodeScaleGrid = new Grid(6, 2);
		verticalPanelGlobal.add(nodeScaleGrid);
		lblNodeScaleTitle = new Label("variable (units)");
		nodeScaleGrid.setWidget(0, 1, lblNodeScaleTitle);
		Image imgRed = new Image("images/markers/red.png");
		nodeScaleGrid.setWidget(1, 0, imgRed);
		lblNodelevel5 = new Label("nodeLevel5");
		nodeScaleGrid.setWidget(1, 1, lblNodelevel5);
		Image imgYellow = new Image("images/markers/yellow.png");
		nodeScaleGrid.setWidget(2, 0, imgYellow);
		lblNodelevel4 = new Label("nodeLevel4");
		nodeScaleGrid.setWidget(2, 1, lblNodelevel4);
		Image imgGreen = new Image("images/markers/green.png");
		nodeScaleGrid.setWidget(3, 0, imgGreen);
		lblNodelevel3 = new Label("nodeLevel3");
		nodeScaleGrid.setWidget(3, 1, lblNodelevel3);
		Image imgCyan = new Image("images/markers/cyan.png");
		nodeScaleGrid.setWidget(4, 0, imgCyan);
		lblNodelevel2 = new Label("nodeLevel2");
		nodeScaleGrid.setWidget(4, 1, lblNodelevel2);
		Image imgBlue = new Image("images/markers/blue.png");
		nodeScaleGrid.setWidget(5, 0, imgBlue);
		lblNodelevel1 = new Label("nodeLevel1");
		nodeScaleGrid.setWidget(5, 1, lblNodelevel1);
		
		// time step title
		Label lblTimeTitle = new Label("Time Step:");
		lblTimeTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		verticalPanelGlobal.add(lblTimeTitle);
		
		//time step cmb
		cmbTimeStep = new ListBox();
		cmbTimeStep.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				timeStepChanged();
			}
		});
		cmbTimeStep.setWidth("100%");
		verticalPanelGlobal.add(cmbTimeStep);
		
		//animation cmb
		cmbAnimationSpeed = new ListBox();
		cmbAnimationSpeed.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				if(cmbAnimationSpeed.getSelectedIndex()==1){
					animationDelay=500;
				}else if(cmbAnimationSpeed.getSelectedIndex()==2){
					animationDelay=100;
				}else if(cmbAnimationSpeed.getSelectedIndex()==3){
					animationDelay=0;
				}
			}
		});
		cmbAnimationSpeed.setWidth("100%");
		verticalPanelGlobal.add(cmbAnimationSpeed);
		
		//Animate button
		Button btnAnimate= new Button("");
		btnAnimate.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(cmbAnimationSpeed.getSelectedIndex()==0){
					Window.alert("please select animation speed!");
				}else{
					animate();
				}
			}
		});
		Image img2 = new Image(images.playSmall());
		HorizontalPanelButton buttonPanel2 = new HorizontalPanelButton(img2,"Animate");
		btnAnimate.getElement().appendChild(buttonPanel2.getElement());
		verticalPanelGlobal.add(btnAnimate);
		btnAnimate.setWidth("100%");
		
		
		//clear map button
		Button btnClearMap = new Button("");
		btnClearMap.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				clearMap();
			}
		});
		Image img3 = new Image(images.clearSmall());
		HorizontalPanelButton buttonPanel3 = new HorizontalPanelButton(img3,"Clear Map");
		btnClearMap.getElement().appendChild(buttonPanel3.getElement());
		verticalPanelGlobal.add(btnClearMap);
		btnClearMap.setWidth("100%");
		
		//load comboxes
		cmbNode.addItem("none");
		cmbNode.addItem("age");
		
		cmbAnimationSpeed.addItem("--animation speed--");
		cmbAnimationSpeed.addItem("slow");
		cmbAnimationSpeed.addItem("normal");
		cmbAnimationSpeed.addItem("fast");

	}
	
	/////////////////////////
	// Methods related to events of this view
	/////////////////////////
	
	
	private void nodeVariableChanged(){
		
		String selectedVariable=cmbNode.getItemText(cmbNode.getSelectedIndex());
		parentView.informSelectedNodeVariable(selectedVariable);						//inform
		
		if (!selectedVariable.equals("none")){
			nodeScaleGrid.setVisible(true);												//show scale
			loadNodeScale(parentView.getNodeScale(),selectedVariable);					//get scale values and load them
		}else{
			nodeScaleGrid.setVisible(false);											//hide scale
		}
	}
	
	
	private void timeStepChanged(){
		parentView.informSelectedTimeStep(cmbTimeStep.getSelectedIndex());
	}
	
	private void clearMap(){
		cmbNode.setSelectedIndex(0);
		cmbTimeStep.setSelectedIndex(0);
		parentView.informSelectedNodeVariable("none");	//this is equivalent of clearing the map
		parentView.hideControlPanel();
	}
	
	
	private void animate(){
		
		timer = new Timer() {
		      @Override
		      public void run() {
		        increaseTimeStep();
		      }
		 };
		
		 timer.schedule(animationDelay);
		
	}
	
	private void increaseTimeStep(){
		
		if(cmbTimeStep.getSelectedIndex()==(cmbTimeStep.getItemCount()-1)){
			
			timer.cancel();
			cmbTimeStep.setSelectedIndex(0);
			timeStepChanged();
			
		}else{
			cmbTimeStep.setSelectedIndex(cmbTimeStep.getSelectedIndex()+1);
			timeStepChanged();
			timer.schedule(animationDelay);
		}
		
	}
	
	
	/////////////////////////
	// load scales
	/////////////////////////
	
	private void loadNodeScale(ArrayList<Float> scale,String variable){
		lblNodelevel1.setText(NumberFormat.getFormat("0.00").format(scale.get(0)));
		lblNodelevel2.setText(NumberFormat.getFormat("0.00").format(scale.get(1)));
		lblNodelevel3.setText(NumberFormat.getFormat("0.00").format(scale.get(2)));
		lblNodelevel4.setText(NumberFormat.getFormat("0.00").format(scale.get(3)));
		lblNodelevel5.setText(NumberFormat.getFormat("0.00").format(scale.get(4)));
		
		if(variable.equals("age")){
			lblNodeScaleTitle.setText("age (hours)");
		}
	}
	
	
	///////////////////////////
	// Methods called by parent view
	//////////////////////////
	
	//this method is called by parent view when the view is activated. here we request the timeSteps strings
	public void loadView(){
		
		ArrayList<String> timeSteps= parentView.getTimeSteps();
		cmbTimeStep.clear();
		
		for (int i=0;i<timeSteps.size();i++){
			cmbTimeStep.addItem(timeSteps.get(i));
		}
		cmbNode.setSelectedIndex(0);
		cmbTimeStep.setSelectedIndex(0);
		
		nodeScaleGrid.setVisible(false);
	}
	
}
