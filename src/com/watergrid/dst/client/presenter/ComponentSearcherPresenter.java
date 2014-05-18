package com.watergrid.dst.client.presenter;

import java.util.ArrayList;

import com.watergrid.dst.client.view.ComponentSearcher;

public class ComponentSearcherPresenter {

	ComponentSearcher view;
	AppPresenter appPresenter;
	
	//variables for the searching process and results storage
	
	String componentType;
	ArrayList<String> resultsID;			//IDs of Components
	ArrayList<Integer> resultsIndex;			//INDEX of components (their original index in the component list, not in the results list). this is used to inform AppPresenter in case user wants to highlight component

	
	
	
	public ComponentSearcherPresenter(){
		
	}
	
	public void setView(ComponentSearcher view){ //sets the view reference to modify the view if needed, and registers as presenter of that view so the view can call the presenter if there is any event
		this.view=view;
		this.view.registerPresenter(this);
	}

	
	public void setAppPresenter(AppPresenter appPresenter){
		this.appPresenter=appPresenter;
	}
	
	
	/////////////////////////
	// Methods called by the view
	////////////////////////
	
	//for searching
	
	public void searchComponent(String componentType, String componentID){
		
		this.componentType=componentType;
		
		resultsID= new ArrayList<String>();
		resultsIndex= new ArrayList<Integer>();
		
		//first get a list of all the existing component IDs depending on the type of component
		
		ArrayList<String> existingIDs = new ArrayList<String>();
		
		if(componentType.equals("junction")){
			existingIDs=appPresenter.getModelManager().createJunctionsIdList();
		}else if (componentType.equals("pipe")){
			existingIDs=appPresenter.getModelManager().createPipesIdList();
		}else if (componentType.equals("reservoir")){
			existingIDs=appPresenter.getModelManager().createReservoirsIdList();
		}else if (componentType.equals("valve")){
			existingIDs=appPresenter.getModelManager().createValvesIdList();
		}
		
		
		//iterate over all the array of existing IDs, and if there is a match add it to the list of results
		for (int i=0;i<existingIDs.size();i++){
			if(existingIDs.get(i).indexOf(componentID)!=-1){
				resultsID.add(existingIDs.get(i));
				resultsIndex.add(i);										//the index in the array of string obtained from the model manager is the same as the real index of the component in the corresponding list of components
			}
		}
		
		//send the list of results (ID names) to the view
		 view.showSearchResults(resultsID);
		
	}
	
	//for highlighting a component
	
	public void highlightComponent(int selectedIndexOfResults){
		
		//the selected index in the view makes also reference to the index in the arrays we have here in this class of the results
		String componentID=resultsID.get(selectedIndexOfResults);
		Integer componentIndex=resultsIndex.get(selectedIndexOfResults);
		
		//notify appPresenter
		
		appPresenter.informComponentSelected(componentType, componentIndex, componentID,false);
		
	}
	
}
