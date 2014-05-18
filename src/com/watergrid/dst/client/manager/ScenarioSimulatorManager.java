package com.watergrid.dst.client.manager;

import java.util.ArrayList;

import com.google.gwt.user.client.Timer;
import com.watergrid.dst.client.model.DMAmodel;
import com.watergrid.dst.client.model.Scenario;

public class ScenarioSimulatorManager {

	ModelManager modelManager;			//reference to the model manager so it can callBack when simulations are finished
	
	
	public ScenarioSimulatorManager(ModelManager modelManager){
		this.modelManager=modelManager;
	}
	
				/////////////////////
				//   SIMULATION
				/////////////////////
	
	//we receive the scenario object to get all the information and add the results, and the index to inform later which scenario has been simulated
	public void runSimulation(String DMAname,Scenario scenario, int scenarioIndex, String simulationType,boolean isReRun){
		
		// create the appropriate instance of the simulator (template method pattern). Sends reference of this manager for the callBacks, the index to inform in callback, and the scenario object to get all the information from it it needs
		// for each simulation we create a new instance of the simulator object.
		
		Simulator simulator = null;
		
		if(simulationType.equals("hydraulic")){
			simulator=new SimulatorHydraulic(this,DMAname,scenario,scenarioIndex,isReRun);
		}else if(simulationType.equals("age")){
			simulator=new SimulatorAge(this,DMAname,scenario,scenarioIndex,isReRun);
		}else if (simulationType.equals("sourceTrace")){
			simulator=new SimulatorSourceTrace(this,DMAname,scenario,scenarioIndex,isReRun);
		}else if(simulationType.equals("chemical")){
			simulator=new SimulatorChemical(this,DMAname,scenario,scenarioIndex,isReRun);
		}
		simulator.simulate();
			
	}
	
	//simulation callBack (inform Model Manager)
	public void informSimulationFinished(int scenarioIndex, String simulationType,boolean isOK, String errorLog,boolean isReRun){
		
		if (!isReRun){
			modelManager.informSimulationFinished(scenarioIndex, simulationType, isOK, errorLog,isReRun);
		}else{
			this.informSimulationFinishedReRun(scenarioIndex, simulationType, isOK, errorLog);
		}
	}
	
	
				/////////////////////
				//   RE-RUN
				/////////////////////
	
	//the following 3 methods re-runs all simulations and when all the results have been received creates a report informing if any alerts are present
	//iterates over all the simulations that have already been run, and runs them again using the methods ABOVE. When all the results have been received we inform the model manager
	
	int numberOfReRunsSent;
	int numberOfReRunsReceived;
	boolean allReRunsSent;
	
	//re run all previously run simulations
	public void reRunAll(DMAmodel model,String DMAname){
		
		//iterate over all scenarios, and if hasRun is true then run it again
		//isReRun=true
		
		numberOfReRunsSent=0;							//number of reruns sent to simulation
		numberOfReRunsReceived=0;						//number of reruns received
		allReRunsSent=false;							//this tells if all the re-runs have been sent to simulation
		
		for (int i=0;i<model.getScenarios().size();i++){
			
			Scenario scenario = model.getScenarios().get(i);
			
			if(scenario.getHydroHasRun()==true){
				numberOfReRunsSent++;
				this.runSimulation(DMAname, scenario, i, "hydraulic",true);
			}
			if(scenario.getAgeHasRun()==true){
				numberOfReRunsSent++;
				this.runSimulation(DMAname, scenario, i, "age",true);
			}
			if(scenario.getSourceHasRun()==true){
				numberOfReRunsSent++;
				this.runSimulation(DMAname, scenario, i, "sourceTrace",true);
			}
			if(scenario.getChemicalHasRun()==true){
				numberOfReRunsSent++;
				this.runSimulation(DMAname, scenario, i, "chemical",true);
			}
		}
		allReRunsSent=true;
	}
	
	//this method is called when we are informed that a simulation has finished and it was a ReRun
	private void informSimulationFinishedReRun(int scenarioIndex,String simulationType,boolean isOK, String errorLog){
		numberOfReRunsReceived++;
		checkReRunsFinished();
	}
	//every time we receive a rerun result, we check if all have been finished to inform appPresenter (because we only inform appPresenter once when all re runs have finished)
	private void checkReRunsFinished(){
		if(allReRunsSent && numberOfReRunsReceived==numberOfReRunsSent){
			modelManager.informSimulationFinishedReRun();
		}
	}
	
	

	
	
}
