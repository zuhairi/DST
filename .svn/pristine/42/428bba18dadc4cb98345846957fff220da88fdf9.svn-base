package com.watergrid.dst.client.model;

public class ScenarioAction {
	
	String componentType;		//refers to the component the action is applied to
	String componentId;			//	
	int componentIndex;			//
	
	String componentAction;
	String actionTime;
	String actionDate;			//string="dd/mm/yyyy"
	boolean isScheduled;		//
	
	
	//constructors
	public ScenarioAction(){
		isScheduled=false;
	}
	
	public ScenarioAction(String componentType, String componentId,int componentIndex, String componentAction, String actionTime,String actionDate) {
		
		this.componentType = componentType;
		this.componentId = componentId;
		this.componentIndex = componentIndex;
		this.componentAction = componentAction;
		this.actionTime = actionTime;
		this.actionDate = actionDate;
		isScheduled=false;
	}

	//getters
	public String getComponentType(){
		return componentType;
	}
	public String getComponentId(){
		return componentId;
	}
	public int getComponentIndex(){
		return componentIndex;
	}
	public  String getComponentAction(){
		return componentAction;
	}
	public String getActionTime(){
		return actionTime;
	}
	public String getActionDate(){
		return actionDate;
	}
	public boolean getIsScheduled(){
		return isScheduled;
	}
	
	public String getComponentCategory(){		//this is for the simulation XML. It changes the name, no idea why.
		
		String componentCategory="";
		
		if (componentType.equals("pipe")){
			componentCategory="link";
		}else if (componentType.equals("junction")){
			componentCategory="junction";
		}else if(componentType.equals("reservoir")){
			componentCategory="reservoir";
		}else if(componentType.equals("valve")){
			componentCategory="valve";
		}
		
		return componentCategory;
		
	}
	
	//setters
	public void setComponentType(String componentType){
		this.componentType=componentType;
	}
	public void setComponentId(String componentId){
		this.componentId=componentId;
	}
	public void setComponentIndex(int componentIndex){
		this.componentIndex=componentIndex;
	}
	public  void setComponentStatus(String componentAction){
		this.componentAction=componentAction;
	}
	public void setActionTime(String actionTime){
		this.actionTime=actionTime;
	}
	public void setActionDate(String actionDate){
		this.actionDate=actionDate;
	}
	public void setIsScheduled(boolean isScheduled){
		this.isScheduled=isScheduled;
	}

}
