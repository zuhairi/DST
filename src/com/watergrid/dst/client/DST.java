package com.watergrid.dst.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.watergrid.dst.client.presenter.AppPresenter;
import com.watergrid.dst.client.view.TestGUI;


public class DST implements EntryPoint {


	
	
	public void onModuleLoad() {
		
		
		new AppPresenter();
		//RootLayoutPanel.get().add(new TestGUI());
		
	}
	

	


	
	
}
