package com.watergrid.dst.client.dataobjects;

import java.util.ArrayList;

public class AlertInfo {
	
	String componentId;
	
	ArrayList<String> variablesHighValue;			//each element represents in what variable (e.g. pressure, head) the alert exists. this is because one same component can have more than one alert and we want all of them to be represented by this object instance
	ArrayList<String> variablesLowValue;
	
	public AlertInfo(String componentId){
		this.componentId=componentId;
		variablesHighValue = new ArrayList<String>();
		variablesLowValue = new ArrayList<String>();
	}
	
	
	public void addVariableHighValue(String variable){
		variablesHighValue.add(variable);
	}
	
	public void addVariableLowValue(String variable){
		variablesLowValue.add(variable);
	}
	
	public String getComponentId(){
		return componentId;
	}
	
	public ArrayList<String> getVariablesHighValue(){
		return variablesHighValue;
	}
	
	public ArrayList<String> getVariablesLowValue(){
		return variablesLowValue;
	}
	
	public String getVariablesHighValueString(){
		
		if(variablesHighValue.size()>0){
		
			String toString=variablesHighValue.get(0);
			
			for(int i=1;i<variablesHighValue.size();i++){
				toString=toString+" - "+variablesHighValue.get(i);
			}
			return toString;
			
		}else{
			return "";
		}
		
		
	}
	
	public String getVariablesLowValueString(){
		
		if(variablesLowValue.size()>0){
		
			String toString=variablesLowValue.get(0);
			for(int i=1;i<variablesLowValue.size();i++){
				toString=toString+" - "+variablesLowValue.get(i);
			}
			return toString;
			
		}else{
			return "";
		}
		
		
	}
	

}
