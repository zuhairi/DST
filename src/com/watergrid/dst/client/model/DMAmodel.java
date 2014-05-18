package com.watergrid.dst.client.model;

import java.util.ArrayList;
import java.util.List;

public class DMAmodel {
	
	String name;
	Boundary boundary;
	ArrayList<Piping> pipes;
	ArrayList<Junction> junctions;
	ArrayList<Reservoir> reservoirs;
	ArrayList<Valve> valves;
	List<Scenario> scenarios;
	
	
	public DMAmodel(String name){
		this.name=name;
		junctions = new ArrayList<Junction>();
		pipes = new ArrayList<Piping>();
		reservoirs = new ArrayList<Reservoir>();
		valves = new ArrayList<Valve>();
		scenarios = new ArrayList<Scenario>();
	}
	
	//methods for adding components to the model
	public void setBounds(Boundary boundary){
		this.boundary=boundary;
	}
	public void addJunction(Junction junction){
		junctions.add(junction);
	}
	public void addPipe(Piping pipe){
		pipes.add(pipe);
	}
	public void addReservoir(Reservoir reservoir){
		reservoirs.add(reservoir);
	}
	public void addValve(Valve valve){
		valves.add(valve);
	}
	
	
	//getters
	public Boundary getBoundary(){
		return boundary;
	}
	public ArrayList<Junction> getJunctions(){
		return junctions;
	}
	public ArrayList<Piping> getPipes(){
		return pipes;
	}
	public ArrayList<Reservoir> getReservoirs(){
		return reservoirs;
	}
	public ArrayList<Valve> getValves(){
		return valves;
	}
	public List<Scenario> getScenarios(){
		return scenarios;
	}
	
	
	//for handling scenarios
	public void addScenario(Scenario experiment){
		scenarios.add(experiment);
	}
	public void removeScenario(int scenarioIndex){
		scenarios.remove(scenarioIndex);					//when removing, the other elements shift one position
	}
	public void removeScenario(Scenario scenario){			//overloaded. remove using the object
		scenarios.remove(scenario);						//when removing, the other elements shift one position
	}

	

}
