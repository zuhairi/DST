package com.watergrid.dst.client.presenter;

import java.util.ArrayList;

import com.watergrid.dst.client.view.ComponentExplorer;
import com.watergrid.dst.client.view.ComponentDetails;

public class ComponentExplorerPresenter {
	
	ComponentExplorer view;			//the views it will manipulate
	AppPresenter appPresenter;		//the AppPresenter to interact with the rest of the application
	
	////////////////////
	
	public ComponentExplorerPresenter(){
		
	}

	public void setView(ComponentExplorer view){ //sets the view reference to modify the view if needed, and registers as presenter of that view so the view can call the presenter if there is any event
		this.view=view;
		this.view.registerPresenter(this);
	}

	
	public void setAppPresenter(AppPresenter appPresenter){
		this.appPresenter=appPresenter;
	}
	
	//////////////////
	
	
	//method called by AppPresenter when the normal view is loaded after a successful creation of the model
	public void loadComponentExplorer(){
		
		//load each list box. Use the model manager to get the list of ID of each type of component
		
		ArrayList<String> componentIdList;
		componentIdList=appPresenter.getModelManager().createJunctionsIdList();
		for(int i=0;i<componentIdList.size();i++){
			view.addJunction(componentIdList.get(i));
		}
		componentIdList=appPresenter.getModelManager().createPipesIdList();
		for(int i=0;i<componentIdList.size();i++){
			view.addPipe(componentIdList.get(i));
		}
		componentIdList=appPresenter.getModelManager().createReservoirsIdList();
		for(int i=0;i<componentIdList.size();i++){
			view.addReservoir(componentIdList.get(i));
		}
		componentIdList=appPresenter.getModelManager().createValvesIdList();
		for(int i=0;i<componentIdList.size();i++){
			view.addValve(componentIdList.get(i));
		}
		
	}
	
	
	/////////////////////////////
	// HIGHLIGHTING HANDLING
	////////////////////////////
	
	//method called by Explorer view when a components has been selected. This method informs the AppPresenter so it propagates the event
	public void informComponentSelected(String type,int index,String id){
		appPresenter.informComponentSelected(type, index, id,false);
	}
	
	//method called by AppPresenter to inform this presenter to highlight component on the view (the source of the event could be the map or other)
	public void highlightComponent(String type,int index,String id){
		
		view.highlightComponent(type, index, id);		//highlight component
		
	}

}
