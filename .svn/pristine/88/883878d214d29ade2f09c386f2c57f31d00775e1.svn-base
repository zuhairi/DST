package com.watergrid.dst.client.presenter;

import java.util.ArrayList;

import com.google.gwt.core.client.Callback;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.Position.Coordinates;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.user.client.Window;
import com.watergrid.dst.client.dataobjects.ComponentLevel;
import com.watergrid.dst.client.view.NetworkMap;

public class NetworkMapPresenter {
	
	NetworkMap view;				//the view it will manipulate
	AppPresenter appPresenter;		//the AppPresenter to interact with the rest of the application
	
	public NetworkMapPresenter(){
		
	}

	public void setView(NetworkMap view){ //sets the view reference to modify the view if needed, and registers as presenter of that view so the view can call the presenter if there is any event
		this.view=view;
		this.view.registerPresenter(this);
	}
	
	public void setAppPresenter(AppPresenter appPresenter){
		this.appPresenter=appPresenter;
	}
	
	///////////////////////////////////////////
	// MAP INITIALIZATION ADN ADDING COMPONENTS
	////////////////////////////////////////////
	
	public void loadNetworkMap(){
		//get the Lat and Lng of the 4 points that make up the boundary (we need them to center the map, set the zoom, and draw the boundary)
		Double Lat_bttmLeft=appPresenter.getModelManager().getModel().getBoundary().getPointBttmLeft().getLat();
		Double Lng_bttmLeft=appPresenter.getModelManager().getModel().getBoundary().getPointBttmLeft().getLng();
		Double Lat_bttmRight=appPresenter.getModelManager().getModel().getBoundary().getPointBttmRight().getLat();
		Double Lng_bttmRight=appPresenter.getModelManager().getModel().getBoundary().getPointBttmRight().getLng();
		Double Lat_topRight=appPresenter.getModelManager().getModel().getBoundary().getPointTopRight().getLat();
		Double Lng_topRight=appPresenter.getModelManager().getModel().getBoundary().getPointTopRight().getLng();
		Double Lat_topLeft=appPresenter.getModelManager().getModel().getBoundary().getPointTopLeft().getLat();
		Double Lng_topLeft=appPresenter.getModelManager().getModel().getBoundary().getPointTopLeft().getLng();
		
		//initialize the map. It uses 2 corners of the boundary to center the map and set the zoom
		view.InitializeMap(Lat_bttmLeft, Lng_bttmLeft, Lat_topRight, Lng_topRight);
		
		//draw the boundary polygon. It uses the 4 points
		view.setBoundaryPolygon(Lat_bttmLeft, Lng_bttmLeft, Lat_bttmRight, Lng_bttmRight, Lat_topRight, Lng_topRight, Lat_topLeft, Lng_topLeft);
		
		//add the junctions
		for(int i=0;i<appPresenter.getModelManager().getModel().getJunctions().size();i++){
			int index;
			String id;
			Double lat;
			Double lng;
			index=appPresenter.getModelManager().getModel().getJunctions().get(i).getIndex();
			id=appPresenter.getModelManager().getModel().getJunctions().get(i).getId();
			lat=appPresenter.getModelManager().getModel().getJunctions().get(i).getCoordinates().getLat();
			lng=appPresenter.getModelManager().getModel().getJunctions().get(i).getCoordinates().getLng();
			view.addJunctionComponent(index, id, lat, lng);
		}
		
		//add the Pipes
		for(int i=0;i<appPresenter.getModelManager().getModel().getPipes().size();i++){
			
			int index;
			String id;
			ArrayList<Double> latsVertex=new ArrayList<Double>();
			ArrayList<Double> lngsVertex=new ArrayList<Double>();;
			
			index=appPresenter.getModelManager().getModel().getPipes().get(i).getIndex();
			id=appPresenter.getModelManager().getModel().getPipes().get(i).getId();
			
			int numberVertices=appPresenter.getModelManager().getModel().getPipes().get(i).getVertices().size();
			for(int j=0;j<numberVertices;j++){	//create Arrays, one with Lat and another with Lng of each vertex of the pipe
				latsVertex.add(appPresenter.getModelManager().getModel().getPipes().get(i).getVertices().get(j).getLat());
				lngsVertex.add(appPresenter.getModelManager().getModel().getPipes().get(i).getVertices().get(j).getLng());
			}
			view.addPipeComponent(index, id, latsVertex, lngsVertex);
		}
		
		//add the Reservoirs
		for(int i=0;i<appPresenter.getModelManager().getModel().getReservoirs().size();i++){
			int index;
			String id;
			Double lat;
			Double lng;
			index=appPresenter.getModelManager().getModel().getReservoirs().get(i).getIndex();
			id=appPresenter.getModelManager().getModel().getReservoirs().get(i).getId();
			lat=appPresenter.getModelManager().getModel().getReservoirs().get(i).getCoordinate().getLat();
			lng=appPresenter.getModelManager().getModel().getReservoirs().get(i).getCoordinate().getLng();
			view.addReservoirComponent(index, id, lat, lng);
		}
		
		//add the Valves
		for(int i=0;i<appPresenter.getModelManager().getModel().getValves().size();i++){
			int index;
			String id;
			Double lat;
			Double lng;
			index=appPresenter.getModelManager().getModel().getValves().get(i).getIndex();
			id=appPresenter.getModelManager().getModel().getValves().get(i).getId();
			lat=appPresenter.getModelManager().getModel().getValves().get(i).getCoordinate().getLat();
			lng=appPresenter.getModelManager().getModel().getValves().get(i).getCoordinate().getLng();
			view.addReservoirComponent(index, id, lat, lng);
		}
		
	}
	
	
						/////////////////////////////
						// HIGHLIGHTING HANDLING
						////////////////////////////
	
	//method called by MapView when a components has been selected. This method informs the AppPresenter so it propagates the event
	public void informComponentSelected(String type,int index,String id){
		appPresenter.informComponentSelected(type, index, id,false);
	}
	//method called by AppPresenter to inform this presenter to highlight component on the view (the source of the event could be the map or other)
	public void highlightComponent(String type,int index,String id){
		view.highlightComponent(type, index, id);
	}
	
	
						/////////////////////////////
						// SHOWING RESULTS ON MAP
						////////////////////////////
	
	public void showResultsOnMap(ArrayList<ComponentLevel> results){
		view.showResultsOnMap(results);
	}
	
						/////////////////////////////
						// MY LOCATION FUNCTIONALITY
						////////////////////////////
	
	//called when user press button to show his location
	public void requestMyLocation(){
		
		//get location
		if (Geolocation.isSupported()) {
			   
			Geolocation geo = Geolocation.getIfSupported();
			geo.getCurrentPosition(new Callback<Position, PositionError>() {
				
				@Override
				public void onSuccess(Position result) {
					
					Coordinates coords=result.getCoordinates();
					double latitude = coords.getLatitude();
					double longitude = coords.getLongitude();
					
					view.showMyLocation(latitude, longitude);
				}
				
				@Override
				public void onFailure(PositionError reason) {
					Window.alert("Sorry, there was an error getting your position!");
				}
			});

		}
		
	}
	
	
	
	public void refreshMap(){
		view.refreshMap();
	}

}
