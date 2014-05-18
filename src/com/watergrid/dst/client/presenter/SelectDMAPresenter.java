package com.watergrid.dst.client.presenter;

import com.watergrid.dst.client.view.SelectDMA;

public class SelectDMAPresenter {
	
	SelectDMA view;					//the view it will manipulate
	AppPresenter appPresenter;		//the AppPresenter to interact with the rest of the application
	
	////////////////////
	
	public SelectDMAPresenter(){
		
	}

	public void setView(SelectDMA view){ //sets the view reference to modify the view if needed, and registers as presenter of that view so the view can call the presenter if there is any event
		this.view=view;
		this.view.registerPresenter(this);
	}
	
	public void setAppPresenter(AppPresenter appPresenter){
		this.appPresenter=appPresenter;
	}
	
	//////////////////
	
	
	
	
	//this method requests the model manager to create a model using the DMAname given
	//this method is called by the View
	public void loadDMA(String DMAname){
		view.setLoading(true);
		view.setMessage("Retreiving DATA");
		appPresenter.getModelManager().createModel(DMAname);
	}
	//method called by AppPresenter when loading DMA is done. The model manager has a reference of this presenter which was passed by the AppPresenter
	public void informLoadingModelFinished(boolean isCreationSuccessfull,String errorLog){
		
		if(isCreationSuccessfull){
			appPresenter.loadNormalView();
		}
		else{
			view.setLoading(false);
			view.setMessage("Error creating the model. Please try Again!");
			view.setAreaMessage(errorLog);
		}
		
	}

}
