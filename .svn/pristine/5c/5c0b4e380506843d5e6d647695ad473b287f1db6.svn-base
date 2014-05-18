package com.watergrid.dst.client.presenter;

import com.watergrid.dst.client.view.ComponentDetails;

public class ComponentDetailsPresenter {
	
	ComponentDetails view;			//the views it will manipulate
	AppPresenter appPresenter;		//the AppPresenter to interact with the rest of the application
	
	////////////////////
	
	public ComponentDetailsPresenter(){
		
	}

	public void setView(ComponentDetails view){ //sets the view reference to modify the view if needed, and registers as presenter of that view so the view can call the presenter if there is any event
		this.view=view;
		this.view.registerPresenter(this);
	}

	
	public void setAppPresenter(AppPresenter appPresenter){
		this.appPresenter=appPresenter;
	}
	
	//////////////////
	
	
	/////////////////////////////
	// HIGHLIGHTING HANDLING
	////////////////////////////
	
	public void showComponentDetails(String type,int index,String id){
				
		view.setVisible(true);
		
		if(type.equals("junction")){
			Double lat = appPresenter.getModelManager().getModel().getJunctions().get(index).getCoordinates().getLat();
			Double lng = appPresenter.getModelManager().getModel().getJunctions().get(index).getCoordinates().getLng();
			float elevation = appPresenter.getModelManager().getModel().getJunctions().get(index).getElevation();
			view.showJunctionDetails(index, id, lat, lng, elevation);
		}
		else if(type.equals("pipe")){
			Double length = appPresenter.getModelManager().getModel().getPipes().get(index).getLength();
			Double diameter = appPresenter.getModelManager().getModel().getPipes().get(index).getDiameter();
			String status = appPresenter.getModelManager().getModel().getPipes().get(index).getStatus();
			String startNodeId =appPresenter.getModelManager().getModel().getPipes().get(index).getStartNodeId();
			String endNodeId=appPresenter.getModelManager().getModel().getPipes().get(index).getEndNodeId();
			view.showPipeDetails(index, id, length, diameter, status,startNodeId,endNodeId);
		}
		else if(type.equals("reservoir")){
			Double lat = appPresenter.getModelManager().getModel().getReservoirs().get(index).getCoordinate().getLat();
			Double lng = appPresenter.getModelManager().getModel().getReservoirs().get(index).getCoordinate().getLng();
			float head = appPresenter.getModelManager().getModel().getReservoirs().get(index).getHead();
			view.showReservoirDetails(index, id, lat, lng, head);
		}else if(type.equals("valve")){
			Double lat = appPresenter.getModelManager().getModel().getValves().get(index).getCoordinate().getLat();
			Double lng = appPresenter.getModelManager().getModel().getValves().get(index).getCoordinate().getLng();
			view.showValveDetails(index, id, lat, lng);
		}
		
	}
	
	////////////////
	// ADD ACTION
	///////////////
	
	//this method is called by the view. The main idea is to inform the AppPresenter, so that it informs the AddAction presenter so show the panel
	
	public void showAddActionView(){
		appPresenter.showAddActionView();
	}

}
