//this data object is used for showing results on map
//it represents the results of a single component in a time step instance
//it includes the component type, the component index, and the level (the lever in the scale of) of a given variable (e.g. pressure)

package com.watergrid.dst.client.dataobjects;

public class ComponentLevel {
	
	String componentType;
	int componentIndex;
	int level;
	
	
	public ComponentLevel(String componentType, int componentIndex, int level) {
		this.componentType = componentType;
		this.componentIndex = componentIndex;
		this.level = level;
	}


	
	public String getComponentType() {
		return componentType;
	}


	public int getComponentIndex() {
		return componentIndex;
	}


	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level){
		this.level=level;
	}
	
	
	
	

}
