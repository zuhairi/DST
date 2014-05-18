package com.watergrid.dst.client.view;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.watergrid.dst.client.presenter.ComponentSearcherPresenter;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class ComponentSearcher extends Composite{
	
	ComponentSearcherPresenter presenter;
	
	ListBox cmbComponentType;
	TextBox txtCompId;
	ListBox listResults;
	Button btnHighlight;
	Label lblResultsFound;
	
	ImageResources images = GWT.create(ImageResources.class);
	
	public ComponentSearcher(){
		initializeComponents();
	}
	
	//this method sets the presenter who will be called by events
	public void registerPresenter(ComponentSearcherPresenter presenter){
		this.presenter=presenter;
	}
	
	///////////////////

	
	/////////////////////
	// VIEW COMPONENTS
	/////////////////////
	
	private void initializeComponents(){
		
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.setWidth("100%");
		initWidget(decoratorPanel);
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(4);
		decoratorPanel.setWidget(verticalPanel);
		verticalPanel.setWidth("100%");
		
		Label lblTitle = new Label("SEARCH");
		lblTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(lblTitle);
		
		Label lblComponentType = new Label("Component type:");
		verticalPanel.add(lblComponentType);
		
		cmbComponentType = new ListBox();
		verticalPanel.add(cmbComponentType);
		cmbComponentType.setWidth("100%");
		cmbComponentType.addItem("--Select Type--");
		cmbComponentType.addItem("junction");
		cmbComponentType.addItem("pipe");
		cmbComponentType.addItem("reservoir");
		cmbComponentType.addItem("valve");
		
		Label lblComponentId = new Label("Component ID:");
		verticalPanel.add(lblComponentId);
		
		txtCompId = new TextBox();
		verticalPanel.add(txtCompId);
		txtCompId.setWidth("95%");
		
		Button btnSearch = new Button("");
		btnSearch.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				searchButtonPressed();
			}
		});
		Image imgSearch = new Image(images.searchSmall());
		Composite buttonPanel = new HorizontalPanelButton(imgSearch, "Search");		//this is a custom panel that includes an image and a title
		btnSearch.getElement().appendChild(buttonPanel.getElement());
		verticalPanel.add(btnSearch);
		btnSearch.setWidth("100%");
		
		lblResultsFound = new Label("results found");
		verticalPanel.add(lblResultsFound);
		
		listResults = new ListBox();
		verticalPanel.add(listResults);
		listResults.setWidth("100%");
		listResults.setVisibleItemCount(5);


		
		btnHighlight = new Button("");
		btnHighlight.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				highlightButtonPressed();
			}
		});
		Image imgHighlight = new Image(images.selectSmall());
		Composite buttonPanel2 = new HorizontalPanelButton(imgHighlight, "Select");		//this is a custom panel that includes an image and a title
		btnHighlight.getElement().appendChild(buttonPanel2.getElement());
		verticalPanel.add(btnHighlight);
		btnHighlight.setWidth("100%");
		
		//hide
		listResults.setVisible(false);
		btnHighlight.setVisible(false);
		lblResultsFound.setVisible(false);
		
	}
	
	////////////////////////////////////////////
	// Method related with events of this view
	////////////////////////////////////////////
	
	private void searchButtonPressed(){
		
		int selectedTypeIndex = cmbComponentType.getSelectedIndex();
		String componentType=cmbComponentType.getItemText(selectedTypeIndex);
		String componentID=txtCompId.getText();
		
		if(selectedTypeIndex>0 && !componentID.equals("")){
			presenter.searchComponent(componentType, componentID);
		}else{
			Window.alert("Please select a component type and enter component ID");
		}
		
	}
	
	private void highlightButtonPressed(){
		
		presenter.highlightComponent(listResults.getSelectedIndex());
	}
	
	
	///////////////////////////////////////
	//  Methods called by the presenter
	//////////////////////////////////////
	
	
	//this method load the list box of results if any
	public void showSearchResults(ArrayList<String> results){
		
		if(results.size()==0){
			listResults.clear();
			Window.alert("Sorry, no results found!");
		}else{
			listResults.setVisible(true);
			btnHighlight.setVisible(true);
			lblResultsFound.setVisible(true);
			lblResultsFound.setText("Components found: " + results.size());
			
			listResults.clear();
			for(int i=0;i<results.size();i++){
				listResults.addItem(results.get(i));
			}
			
		}
		
	}
	
}
