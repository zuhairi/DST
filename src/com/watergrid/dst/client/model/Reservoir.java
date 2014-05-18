package com.watergrid.dst.client.model;

public class Reservoir {
	
	int index;
	String id;
	Coordinate coordinates;
	float head;
	
	
	public Reservoir(){
		
	}
	
	public Reservoir(int index,String id,Coordinate coordinates,float head){
		this.index=index;
		this.id=id;
		this.coordinates=coordinates;
		this.head=head;
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
	public float getHead(){
		return head;
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
	public void  setHead(float head){
		this.head=head;
	}
	

}
