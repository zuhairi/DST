package com.watergrid.dst.client.presenter;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.watergrid.dst.client.view.ImageResources;
import com.watergrid.dst.client.view.NotificationPanel;

public class NotificationsPresenter {
	
	AppPresenter appPresenter;
	
	
	public NotificationsPresenter(){
		
	}
	
	public void setAppPresenter(AppPresenter appPresenter){
		this.appPresenter=appPresenter;
	}
	
	
	
	/////////
	// called by AppPresenter
	/////////
	
	
	public void informSimulationFinished(int scenarioIndex,String scenarioName, String simulationType, boolean isOK,String errorLog){
		
		String message="";
		
		NotificationPanel notification = new NotificationPanel();
	    
		if (isOK){
			
			int alertCount=appPresenter.getModelManager().getAlertCount(scenarioIndex, simulationType);
			
			if(alertCount==0){
				//simulation OK - NO alerts present
				notification.addImageSuccess();
				message="RESULTS READY !";
				notification.addMessage(message);
				message=simulationType.toUpperCase() + " simulation results of " + scenarioName.toUpperCase() + " scenario are ready !";
				notification.addMessage(message);
			}
			else{
				//simulation OK - alerts present
				notification.addImageStop();
				message="RESULTS READY !";
				notification.addMessage(message);
				message=simulationType.toUpperCase() + " simulation results of " + scenarioName.toUpperCase() + " scenario are ready !";
				notification.addMessage(message);
				notification.addMessage(".");
				message="WARNING!";
				notification.addMessage(message);
				message="The network presents " +alertCount+" alert(s) !";
				notification.addMessage(message);
			}
			
		}else{
			//error in simulation (communication or parsing)
			notification.addImageError();
			message="ERROR !";
			notification.addMessage(message);
			message=simulationType.toUpperCase() + " simulation results of " + scenarioName.toUpperCase() + " scenario presented an error !";
			notification.addMessage(message);
		}
		
		notification.showNotification();
		
		
	}
	
	///////
	
	// re run
	
	public void informSimulationFinishedReRun(ArrayList<String> report){
		
		
		NotificationPanel notification = new NotificationPanel();
		String message="";
		
		
		if(report.size()==0){
			//no alerts
			notification.addImageSuccess();
			message="AUTOMATIC RE-RUN HAS EXECUTED SUCCESSFULLY !";
			notification.addMessage(message);
			message="No alerts present";
			notification.addMessage(message);
		}else{
			
			//Alerts
			notification.addImageStop();
			message="AUTOMATIC RE-RUN HAS EXECUTED SUCCESSFULLY !";
			notification.addMessage(message);
			message="WARNING: The following scenarios present alerts";
			notification.addMessage(message);
			
			for(int i=0;i<report.size();i++){
				message=report.get(i);
				notification.addMessage(message);
			}
	
		}
		
		notification.showNotification();
		
	}
	

}
