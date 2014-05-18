//this is a simple panel to add a title and an image to a button

package com.watergrid.dst.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VerticalPanelButton extends Composite{

	public VerticalPanelButton(Image icon,String title){
		
		VerticalPanel panel = new VerticalPanel();
		panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		panel.setSize("100%","100%");
		panel.add(icon);
		Label label = new Label(title);
		panel.add(label);
		
		initWidget(panel);
	}
	
}