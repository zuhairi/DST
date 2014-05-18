package com.watergrid.dst.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LeftPanel extends Composite{
	
	VerticalPanel verticalPanelGlobal;
	
	StackPanel stackPanel;
	VerticalPanel explorerPanel;
	VerticalPanel searchPanel;
	
	
	
	public LeftPanel(){
		
		initializeComponents();
		
	}
	
	private void initializeComponents(){
		
		//a general vertical panel in case we want to add something else in addition to the stack panel
		verticalPanelGlobal=new VerticalPanel();
		verticalPanelGlobal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelGlobal.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		//verticalPanelGlobal.setSpacing(1);
		verticalPanelGlobal.setSize("100%", "100%");
		initWidget(verticalPanelGlobal);

		//stack panel
		stackPanel = new StackPanel();
		verticalPanelGlobal.add(stackPanel);
		stackPanel.setSize("100%", "100%");
		
		//explorer panel (stack No.1)
		
		VerticalPanel explorerPanelParent=new VerticalPanel();		//we use one vertical inside another one because the stackPanel doesn't allow us to define alignment.So we add first a parent one that occupies 100% of the space and then we add another one inside which can be any percentage and define alignment. We we don't do this, if we want the vertical panel to be less than 100%, everything will look to be align to the left because of the stack panel
		explorerPanelParent.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		explorerPanelParent.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		explorerPanelParent.setSize("100%", "100%");
		stackPanel.add(explorerPanelParent, "<html><body><img src='images/browsericon-16.png'>  EXPLORER</body></html>", true);
		
		explorerPanel=new VerticalPanel();
		explorerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		explorerPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		explorerPanel.setSpacing(1);
		explorerPanel.setSize("95%", "100%");
		explorerPanelParent.add(explorerPanel);
		
		
		
		//Search panel (stack No.2)
		
		VerticalPanel searchPanelParent=new VerticalPanel();
		searchPanelParent.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		searchPanelParent.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		searchPanelParent.setSize("100%", "100%");
		stackPanel.add(searchPanelParent, "<html><body><img src='images/searchicon-16.png'>  SEARCH</body></html>", true);
		
		searchPanel=new VerticalPanel();
		searchPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		searchPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		searchPanel.setSpacing(1);
		searchPanel.setSize("95%", "100%");
		searchPanelParent.add(searchPanel);

		
	}
	
	public void addWidgetGlobal(Composite composite){
		verticalPanelGlobal.add(composite);	
	}
	
	public void addWidgetExplorerStack(Composite composite){
		explorerPanel.add(composite);	
	}

	public void addWidgetSearchStack(Composite composite){
		searchPanel.add(composite);	
	}
	
	public void showExplorerStack(){
		stackPanel.showStack(0);
	}
	
	public void showSearchStack(){
		stackPanel.showStack(1);
	}
}
