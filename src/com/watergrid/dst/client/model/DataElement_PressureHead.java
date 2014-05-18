//represents a element of data of pressure and Head

package com.watergrid.dst.client.model;

public class DataElement_PressureHead {
	
	Float pressure;
	Float head;
	
	
	public DataElement_PressureHead(Float pressure,Float head){
		this.pressure=pressure;
		this.head=head;
	}
	
	public Float getPressure(){
		return pressure;
	}
	
	public Float getHead(){
		return head;
	}

}
