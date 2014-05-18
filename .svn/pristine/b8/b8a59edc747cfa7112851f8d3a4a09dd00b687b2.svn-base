//this class is to get the index and type of any component given its ID.
//this is to make the searching process faster. we create a hashmap with all the IDs instead of having each time to iterate over all the collection of components to find a match for each ID and then return its index and type.


package com.watergrid.dst.client.manager;

import java.util.HashMap;

import com.google.gwt.user.client.Window;
import com.watergrid.dst.client.model.DMAmodel;

public class ComponentIdentifier {
	
	
	HashMap<String,IndexType> componentDirectory;		//the key is the components ID, and the value is an array (position 0 index and position 1 the type)
	
	
	public ComponentIdentifier(DMAmodel model){
		
		buildDirectory(model);
		
	}
	
	
	//build hashMap
	private void buildDirectory(DMAmodel model){
		
		componentDirectory = new HashMap<String,IndexType>();
		
		
		//iterate over junctions
		for (int i=0;i<model.getJunctions().size();i++){
			
			String id=model.getJunctions().get(i).getId();
			int index=i;
			String type="junction";
			componentDirectory.put(id, new IndexType(index, type));
		}
		
		//iterate over pipes
		for (int i=0;i<model.getPipes().size();i++){
			
			String id=model.getPipes().get(i).getId();
			int index=i;
			String type="pipe";
			componentDirectory.put(id, new IndexType(index, type));
		}
		
		//iterate over reservoirs
		for (int i=0;i<model.getReservoirs().size();i++){
			
			String id=model.getReservoirs().get(i).getId();
			int index=i;
			String type="reservoir";
			componentDirectory.put(id, new IndexType(index, type));
		}
		
		//iterate over valves
		for (int i=0;i<model.getValves().size();i++){
			
			String id=model.getValves().get(i).getId();
			int index=i;
			String type="valve";
			componentDirectory.put(id, new IndexType(index, type));
		}
	}
	
	
	////////////
	// Public methods
	///////////
	
	public int getComponentIndex(String ID){
		return componentDirectory.get(ID).getIndex();
		
	}
	
	public String getComponentType(String ID){
		return componentDirectory.get(ID).getType();
	}
	
	
	
	
	///////////////////////////////
	// class to represent index and type pairs in the hashMap
	/////////////////////////////
	public class IndexType{
		
		int index;
		String type;
		
		public IndexType(int index,String type){
			this.index=index;
			this.type=type;
		}
		
		public int getIndex(){
			return index;
		}
		
		public String getType(){
			return type;
		}
		
		
	}
	

}
