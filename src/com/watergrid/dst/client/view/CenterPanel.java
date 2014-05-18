package com.watergrid.dst.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.watergrid.dst.client.presenter.AppPresenter;

public class CenterPanel extends Composite{

	VerticalPanel verticalPanelMain;
	StackPanel stackPanel;
	TabLayoutPanel stackLayoutPanel;

	AppPresenter appPresenter;
	
	
	int selectedStack=0;
	
	public CenterPanel(AppPresenter appPresenter){
		
		this.appPresenter=appPresenter;
		initializeComponents();
		
	}
	
	public void initializeComponents(){
		
		verticalPanelMain = new VerticalPanel();
		initWidget(verticalPanelMain);
		setWidth("100%");
		setHeight("100%");
		
		verticalPanelMain.setSize("100%", "100%");
		verticalPanelMain.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelMain.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		stackPanel = new StackPanel();
		verticalPanelMain.add(stackPanel);
		stackPanel.setSize("100%", "100%");
		
		
		//this click handler is needed to redraw all the datagrids when the stack changes, otherwise they dont refresh and show empty
		//this gets all the clicks. So only notify when the click means a change in stack.
		//when the stack is change by code using showStack() it also triggers this event
		stackPanel.addHandler(new ClickHandler()
		{
		    @Override
		    public void onClick(ClickEvent clickEvent)
		    {
		    	if(selectedStack!=stackPanel.getSelectedIndex()){			//detect if there is change in stack
		    		
		    		selectedStack=stackPanel.getSelectedIndex();			//update the selected stack variable
			    	appPresenter.informChangeInSelectedStack();				//inform presenter that stack has changed so it can redraw all DataGrid.
			    	
			    	if(selectedStack==0){
			    		//Window.alert("mapSelected");
			    	}
			    	
		    	}
		    }
		}, ClickEvent.getType());
		
		
	}
	
	public void addStack(Composite composite, String name){
		stackPanel.add(composite, name, true);
		
	}
	
	public void activateStack(int stackIndex){
		stackPanel.showStack(stackIndex);
	}
	
}
