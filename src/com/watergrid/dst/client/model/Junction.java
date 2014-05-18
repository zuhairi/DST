package com.watergrid.dst.client.model;

public class Junction {
	
	int index;
	String id;
	float elevation;
	float demand;
	Coordinate coordinates;
	
	
	public Junction(){
		
	}
	public Junction(int index,String id,float elevation,float demand,Coordinate coordinates){
		this.index=index;
		this.id=id;
		this.elevation=elevation;
		this.demand=demand;
		this.coordinates=coordinates;
	}
	
	//setters
	public void setIndex(int index){
		this.index=index;
	}
	public void setId(String id){
		this.id=id;
	}
	public void setElevation(float elevation){
		this.elevation=elevation;
	}
	public void setDemand(float demand){
		this.demand=demand;
	}
	public void setCoordinates(Coordinate coordinates){
		this.coordinates=coordinates;
	}
	
	//getters
	public int getIndex(){
		return index;
	}
	public String getId(){
		return id;
	}
	public float getElevation(){
		return elevation;
	}
	public float getDemand(){
		return demand;
	}
	public Coordinate getCoordinates(){
		return coordinates;
	}

}
