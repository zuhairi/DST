package com.watergrid.dst.client.manager;

import com.watergrid.dst.client.model.DMAmodel;

public class ComponentIdentifierSLOW {
	
	
	public static int getComponentIndex(String ID, DMAmodel model){
		
		int index;

		//check Junctions
		for(int i=0;i<model.getJunctions().size();i++){
			if(model.getJunctions().get(i).getId().equals(ID)){
				index=i;
				return index;
			}
		}
		//check Pipes
		for(int i=0;i<model.getPipes().size();i++){
			if(model.getPipes().get(i).getId().equals(ID)){
				index=i;
				return index;
			}
		}
		//check Reservoirs
		for(int i=0;i<model.getReservoirs().size();i++){
			if(model.getReservoirs().get(i).getId().equals(ID)){
				index=i;
				return index;
			}
		}
		//check Valves
		for(int i=0;i<model.getValves().size();i++){
			if(model.getValves().get(i).getId().equals(ID)){
				index=i;
				return index;
			}
		}

		return -1;
	}
	
	//get the Type of a component by its ID
	public static String getComponentType(String ID,DMAmodel model){
		

		String type;

		//check Junctions
		for(int i=0;i<model.getJunctions().size();i++){
			if(model.getJunctions().get(i).getId().equals(ID)){
				type="junction";
				return type;
			}
		}
		//check Pipes
		for(int i=0;i<model.getPipes().size();i++){
			if(model.getPipes().get(i).getId().equals(ID)){
				type="pipe";
				return type;
			}
		}
		//check Reservoirs
		for(int i=0;i<model.getReservoirs().size();i++){
			if(model.getReservoirs().get(i).getId().equals(ID)){
				type="reservoir";
				return type;
			}
		}
		//check Valves
		for(int i=0;i<model.getValves().size();i++){
			if(model.getValves().get(i).getId().equals(ID)){
				type="valve";
				return type;
			}
		}

		return "";
	}

}
