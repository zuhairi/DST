package com.watergrid.dst.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class NotificationPanel {
	
	
	VerticalPanel verticalPanelGlobal;
	VerticalPanel verticalPanelTop;
	VerticalPanel verticalPanelBttm;
	
	DecoratedPopupPanel simplePopup;
	
	ImageResources images = GWT.create(ImageResources.class);
	
	public NotificationPanel() {
		
		
		//global vertical panel
		verticalPanelGlobal = new VerticalPanel();
		verticalPanelGlobal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		
		//top vertical panel (where we add images and labels)
		verticalPanelTop = new VerticalPanel();
		verticalPanelTop.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelTop.setSpacing(5);
		
		//bttm vertical panel, where button goes
		verticalPanelBttm = new VerticalPanel();
		verticalPanelBttm.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		Button btnOK = new Button("OK");
		btnOK.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				simplePopup.hide();
			}
		});
		verticalPanelBttm.add(btnOK);
		
		//add top and bttm to gloabl
		verticalPanelGlobal.add(verticalPanelTop);
		verticalPanelGlobal.add(verticalPanelBttm);
		
		
		//create popup and set global panel as widget
		simplePopup = new DecoratedPopupPanel(true);
		simplePopup.setWidget(verticalPanelGlobal);
		simplePopup.setAnimationEnabled(true);
		simplePopup.setGlassEnabled(true);
		//simplePopup.setModal(true);
		
	}
	
	
	public void showNotification(){
		simplePopup.show();
		simplePopup.center();
		
	}
	
	public void addImageURL(String image){
		Image img = new Image(image);
		verticalPanelTop.add(img);
	}
	
	public void addImageIMG(Image img){
		verticalPanelTop.add(img);
	}
	
	public void addImageSuccess(){
		Image img = new Image(images.successBig());
		verticalPanelTop.add(img);
	}
	public void addImageStop(){
		Image img = new Image(images.stopBig());
		verticalPanelTop.add(img);
	}
	public void addImageError(){
		Image img = new Image(images.errorBig());
		verticalPanelTop.add(img);
	}
	
	public void addMessage(String message){
		Label lblMessage = new Label(message);
		verticalPanelTop.add(lblMessage);
	}

}
