//this class creates information for the whole dma Model

package com.watergrid.dst.client.manager;

import java.util.ArrayList;

import com.watergrid.dst.client.dataobjects.ResultsSummary;
import com.watergrid.dst.client.model.DMAmodel;
import com.watergrid.dst.client.model.Junction;
import com.watergrid.dst.client.model.Piping;
import com.watergrid.dst.client.model.Reservoir;
import com.watergrid.dst.client.model.Scenario;
import com.watergrid.dst.client.model.Valve;

public class ReportCreatorGeneral {
	
	
					//////////////////////////////////////
					/// RESULTS SUMMARY
					//////////////////////////////////////
	
	
	//this method creates a list of all the available results (each represented by a ResultSummary object)
	public static ArrayList<ResultsSummary> createResultsSummary(DMAmodel model){ 
		
		ArrayList<ResultsSummary> summary = new ArrayList<ResultsSummary>();
		
		
		for(int i=0;i<model.getScenarios().size();i++){
			
			//for every scenario check if it has run for each type of simulation type, and if it has, create a summary object and add it to the list
			
			Scenario scenario = model.getScenarios().get(i);
			
			if (scenario.getHydroHasRun()){
				ResultsSummary summaryItem = new ResultsSummary(scenario.getName(), "hydraulic", i,scenario.getHydraulicResults().getResultsTime());
				summary.add(summaryItem);
			}
			if (scenario.getAgeHasRun()){
				ResultsSummary summaryItem = new ResultsSummary(scenario.getName(), "age", i,scenario.getAgeResults().getResultsTime());
				summary.add(summaryItem);
			}
			if (scenario.getSourceHasRun()){
				ResultsSummary summaryItem = new ResultsSummary(scenario.getName(), "sourceTrace", i,scenario.getSourceTraceResults().getResultsTime());
				summary.add(summaryItem);
			}
			if (scenario.getChemicalHasRun()){
				ResultsSummary summaryItem = new ResultsSummary(scenario.getName(), "chemical", i,scenario.getChemicalConResults().getResultsTime());
				summary.add(summaryItem);
			}
		}
		return summary;
		
	}
	
							
							//////////////////////////////////////
							///  COMPONENT ID LISTS CREATOR
							//////////////////////////////////////
	
	
	public static ArrayList<String> createJunctionsIdList(DMAmodel model){
		
		ArrayList<Junction> junctionList = model.getJunctions();
		ArrayList<String> idList = new ArrayList<String>();
		for(int i=0;i<junctionList.size();i++){
			idList.add(junctionList.get(i).getId());
		}
		return idList;		
	}
	
	public static ArrayList<String> createPipesIdList(DMAmodel model){
		
		ArrayList<Piping> pipeList = model.getPipes();
		ArrayList<String> idList = new ArrayList<String>();
		for(int i=0;i<pipeList.size();i++){
			idList.add(pipeList.get(i).getId());
		}
		return idList;		
	}
	
	public static ArrayList<String> createReservoirsIdList(DMAmodel model){
		
		ArrayList<Reservoir> reservoirList = model.getReservoirs();
		ArrayList<String> idList = new ArrayList<String>();
		for(int i=0;i<reservoirList.size();i++){
			idList.add(reservoirList.get(i).getId());
		}
		return idList;		
	}
	
	public static ArrayList<String> createValvesIdList(DMAmodel model){
		
		ArrayList<Valve> valveList = model.getValves();
		ArrayList<String> idList = new ArrayList<String>();
		for(int i=0;i<valveList.size();i++){
			idList.add(valveList.get(i).getId());
		}
		return idList;		
	}
	
	public static ArrayList<String> createScenarioNamesList(DMAmodel model){
		
		ArrayList<Scenario> scenarioList = (ArrayList<Scenario>)model.getScenarios();
		ArrayList<String> nameList = new ArrayList<String>();
		for(int i=0;i<scenarioList.size();i++){
			nameList.add(scenarioList.get(i).getName());
		}
		return nameList;		
	}
	

}
