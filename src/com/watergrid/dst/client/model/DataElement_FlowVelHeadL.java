//represents a element of data

package com.watergrid.dst.client.model;

public class DataElement_FlowVelHeadL {
	
	Float flow;
	Float velocity;
	Float headLoss;
	
	
	public DataElement_FlowVelHeadL(Float flow,Float velocity,Float headLoss){
		this.flow=flow;
		this.velocity=velocity;
		this.headLoss=headLoss;
	}
	
	public Float getFlow(){
		return flow;
	}
	
	public Float getVelocity(){
		return velocity;
	}
	
	public Float getHeadLoss(){
		return headLoss;
	}

}