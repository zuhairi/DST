package com.watergrid.dst.client.model;

public class Valve {
	
	int index;
	String id;
	Coordinate coordinates;
	
	
	public Valve(){
		
	}
	
	public Valve(int index,String id,Coordinate coordinates){
		this.index=index;
		this.id=id;
		this.coordinates=coordinates;
	}
	
	//getters
	public int getIndex(){
		return index;
	}
	public String getId(){
		return id;
	}
	public Coordinate getCoordinate(){
		return coordinates;
	}
	
	//setters
	public void setIndex(int index){
		this.index=index;
	}
	public void setId(String id){
		this.id=id;
	}
	public void setCoordinate(Coordinate coordinates){
		this.coordinates=coordinates;
	}
	

}
