package com.watergrid.dst.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.watergrid.dst.client.presenter.SelectDMAPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.DisclosurePanel;

public class SelectDMA extends Composite{
	
	SelectDMAPresenter presenter;	//reference to the presenter to call it in case of events

	TextBox txtDMA;
	Label lblMessage;
	Button btnLoad;
	Image image;
	TextArea textAreaMessage;
	DisclosurePanel disclosurePanel;
	
	ImageResources images = GWT.create(ImageResources.class);

	public SelectDMA() {
		initializeComponents();
	}
	
	
	//this method sets the presenter who will be called by events
	public void registerPresenter(SelectDMAPresenter presenter){
		this.presenter=presenter;
	}
	
	
	private void initializeComponents(){
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		
		Label lblWelcome = new Label("Welcome");
		lblWelcome.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(lblWelcome);
		
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		verticalPanel.add(decoratorPanel);
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		verticalPanel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel_1.setSpacing(10);
		decoratorPanel.setWidget(verticalPanel_1);
		
		image = new Image(images.tapWater());
		verticalPanel_1.add(image);
		
		
		Label lblDmaName = new Label("DMA name:");
		verticalPanel_1.add(lblDmaName);
		
		txtDMA = new TextBox();
		txtDMA.setText("j796");
		verticalPanel_1.add(txtDMA);
		txtDMA.setWidth("95%");
		
		btnLoad = new Button("LOAD");
		btnLoad.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				disclosurePanel.setVisible(false);
				presenter.loadDMA(txtDMA.getText());
			}
		});
		
		verticalPanel_1.add(btnLoad);
		btnLoad.setWidth("100%");
		
		lblMessage = new Label("");
		lblMessage.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel_1.add(lblMessage);
		
		disclosurePanel = new DisclosurePanel("Details");
		disclosurePanel.setAnimationEnabled(true);
		verticalPanel_1.add(disclosurePanel);
		disclosurePanel.setWidth("100%");
		
		textAreaMessage = new TextArea();
		textAreaMessage.setWidth("80%");
		disclosurePanel.setContent(textAreaMessage);
		disclosurePanel.setVisible(false);
		
	}
	

	public void setMessage(String message){
		txtDMA.setText("");
		lblMessage.setText(message);
		
	}
	public void setAreaMessage(String message){
		textAreaMessage.setText(message);
		disclosurePanel.setVisible(true);
	}
	public void setLoading(boolean isLoading){
		
		if(isLoading){
			btnLoad.setEnabled(false);
			image.setResource(images.loading());
		}
		else{
			btnLoad.setEnabled(true);
			image.setResource(images.tapWater());
		}
	}

	

}
