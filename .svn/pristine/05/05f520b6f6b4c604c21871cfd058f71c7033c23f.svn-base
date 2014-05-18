package com.watergrid.dst.client.manager;

import java.util.ArrayList;

import com.google.gwt.i18n.client.NumberFormat;
import com.watergrid.dst.client.model.DMAmodel;

public class TimeStepCreator {
	
	
	//this method creates an array with the time of each time step
	public static ArrayList<String> getTimeSteps(DMAmodel model, int scenarioIndex){
		
		
		ArrayList<String> timeSteps = new ArrayList<String>();

		String startTime=model.getScenarios().get(scenarioIndex).getStartTime();
		int totalDuration=model.getScenarios().get(scenarioIndex).getTotalDuration(); 
		int iterationLength=model.getScenarios().get(scenarioIndex).getIterationLength();

		String[] startTimeStringArray = startTime.split(":");
		int startTimeHH = Integer.valueOf(startTimeStringArray[0]);
		int startTimeMM = Integer.valueOf(startTimeStringArray[1]);
		
		int startTimeInMinutes=(startTimeHH*60)+startTimeMM;
		int numberOfIterations=(totalDuration*60)/iterationLength;
		
		for(int i=0;i<numberOfIterations;i++){
			
			int iterationTimeMinutes=startTimeInMinutes+(i*iterationLength);
			
			int iterationTimeDAY = iterationTimeMinutes/1440;
			int iterationTimeHH = (iterationTimeMinutes-(iterationTimeDAY*1440))/60;
			int iterationTimeMM = iterationTimeMinutes-(iterationTimeHH*60)-(iterationTimeDAY*1440);
			
			String HH = NumberFormat.getFormat("00").format(iterationTimeHH);
			String MM = NumberFormat.getFormat("00").format(iterationTimeMM);
			String cmbItemString =HH+":"+MM;
			
			if(iterationTimeDAY>0){
				cmbItemString=cmbItemString+" ( + "+ iterationTimeDAY +" day)";
			}
			timeSteps.add(cmbItemString);
		}
		return timeSteps;
		
	}
	

}
