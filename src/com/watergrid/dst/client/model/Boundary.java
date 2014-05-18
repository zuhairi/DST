package com.watergrid.dst.client.model;

public class Boundary {
	
	Coordinate pointTopLeft;
	Coordinate pointTopRight;
	Coordinate pointBttmLeft;
	Coordinate pointBttmRight;
	
	
	public Boundary(){
		
	}
	
	
	//setters
	public void setPointTopLeft(Coordinate point){
		pointTopLeft=point;
	}
	public void setPointTopRight(Coordinate point){
		pointTopRight=point;
	}
	public void setPointBttmLeft(Coordinate point){
		pointBttmLeft=point;
	}
	public void setPointBttmRight(Coordinate point){
		pointBttmRight=point;
	}
	//getters
	public Coordinate getPointTopLeft(){
		return pointTopLeft;
	}
	public Coordinate getPointTopRight(){
		return pointTopRight;
	}
	public Coordinate getPointBttmLeft(){
		return pointBttmLeft;
	}
	public Coordinate getPointBttmRight(){
		return pointBttmRight;
	}
}
