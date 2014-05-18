package com.watergrid.dst.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.maps.gwt.client.ControlPosition;
import com.google.maps.gwt.client.GoogleMap;
import com.google.maps.gwt.client.LatLng;
import com.google.maps.gwt.client.MapOptions;
import com.google.maps.gwt.client.MapTypeId;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;

public class TestGUI extends Composite {

	GoogleMap map;
	
	StackLayoutPanel stackCenter;
	
	public TestGUI(){
		

		///////
		LayoutPanel globalPanel = new LayoutPanel();
		initWidget(globalPanel);
		
		//Main Areas
		
		StackLayoutPanel stackLeft = new StackLayoutPanel(Unit.EM);
		globalPanel.add(stackLeft); 
		globalPanel.setWidgetLeftWidth(stackLeft, 0, Unit.PCT, 20, Unit.PCT);  // Left panel
		globalPanel.setWidgetTopHeight(stackLeft, 0, Unit.PCT, 100, Unit.PCT);
		
		stackCenter = new StackLayoutPanel(Unit.EM);
		stackCenter.addSelectionHandler(new SelectionHandler<Integer>() {
			public void onSelection(SelectionEvent<Integer> event) {
				stackCenter.onResize();
				map.triggerResize();
			}
		});
		
		globalPanel.add(stackCenter); 
		globalPanel.setWidgetLeftWidth(stackCenter, 20, Unit.PCT, 80, Unit.PCT); 
		globalPanel.setWidgetTopHeight(stackCenter, 0, Unit.PCT, 100, Unit.PCT);
		
		StackLayoutPanel stackRight = new StackLayoutPanel(Unit.EM);
		globalPanel.add(stackRight); 
		globalPanel.setWidgetLeftWidth(stackRight, 80, Unit.PCT, 100, Unit.PCT); 
		globalPanel.setWidgetTopHeight(stackRight, 0, Unit.PCT, 100, Unit.PCT);
		
		
		//Sample children inside stacks or tabs
		
		//for left stack
		
		Label lbl1 = new Label("Left Stack, stack 1");
		stackLeft.add(lbl1, new HTML("Stack1"), 2.0);
		
		Label lbl2 = new Label("Left Stack, stack 2");
		stackLeft.add(lbl2, new HTML("Stack2"), 2.0);
		
		
		//for right stack
		
		Label lbl3 = new Label("Right Stack, stack 1");
		stackRight.add(lbl3, new HTML("Stack1"), 2.0);
		
		Label lbl4 = new Label("Right Stack, stack 2");
		stackRight.add(lbl4, new HTML("Stack2"), 2.0);
		
		
		//for center stack
		
		Label lbl5 = new Label("Center Tab, Tab 1");
		stackCenter.add(lbl5, new HTML("Stack1"), 2.0);
		
		Label lbl6 = new Label("Center Tab, Tab 2");
		stackCenter.add(lbl6, new HTML("Stack2"), 2.0);
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSize("100%", "100%");
		stackCenter.add(verticalPanel, new HTML("Map"), 2.0);

		
		

		
		//add map

		MapOptions mapOptions;

		final LatLng centerLatLng = LatLng.create(53.473182, -2.241718);

		mapOptions = MapOptions.create();
		mapOptions.setZoom(14.0);
		mapOptions.setCenter(centerLatLng);
		mapOptions.setMapTypeId(MapTypeId.ROADMAP);
		map= GoogleMap.create(verticalPanel.getElement(), mapOptions);
		
		map.triggerResize();
		
	    //create button with listener
	    Button btn = new Button("Refresh", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	map.triggerResize();
	        }
	      });
	    RootPanel.get().add(btn);
	    map.getControls().get(new Double(ControlPosition.LEFT_CENTER.getValue()).intValue()).push(btn.getElement());
		
		
		
	}
	
}
