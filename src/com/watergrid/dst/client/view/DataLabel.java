package com.watergrid.dst.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class DataLabel extends Composite{
	
	
	public DataLabel(String title,String data) {
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		initWidget(horizontalPanel);
		//horizontalPanel.setWidth("100%");
		
		Label lblTitle = new Label(title+": ");
		lblTitle.setStyleName("gwt-Label-DataLabel-Title");
		horizontalPanel.add(lblTitle);
		//lblTitle.setWidth("100%");
		
		Label lblData = new Label(data);
		horizontalPanel.add(lblData);
		//lblData.setWidth("100%");


	}

}
