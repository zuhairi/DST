//COMPARATORS FOR THE DATA GRIDS COLUMNS



package com.watergrid.dst.client.view;

import java.util.Comparator;

import com.google.gwt.user.client.Window;
import com.watergrid.dst.client.dataobjects.LinkHydraulicResult;
import com.watergrid.dst.client.dataobjects.NodeAgeResult;
import com.watergrid.dst.client.dataobjects.NodeChemicalResult;
import com.watergrid.dst.client.dataobjects.NodeHydraulicResult;
import com.watergrid.dst.client.dataobjects.NodeSourceTraceResult;

public class ComparatorFactory {
	
	
	
	public ComparatorFactory(){
		

		
	}

	
				////////////////////////////////
				//   NODE HYDRAULIC DATA GRID
				///////////////////////////////

	//ID
	public static Comparator<NodeHydraulicResult> getComp_NodeHydraulic_Id() {

		return new Comparator<NodeHydraulicResult>() {
					@Override
					public int compare(NodeHydraulicResult o1, NodeHydraulicResult o2) {
		
						if (o1 == o2) {
							return 0;
						}
						// Compare
						if (o1 != null) {
							
							return (o2 != null) ? o1.getNodeId().compareTo(
									o2.getNodeId()) : 1;
						}
						return -1;
					}
				};
	}
	//MIN PRESSURE
	public static Comparator<NodeHydraulicResult> getComp_NodeHydraulic_MinPressure() {

		return new Comparator<NodeHydraulicResult>() {
					@Override
					public int compare(NodeHydraulicResult o1, NodeHydraulicResult o2) {
		
						float value1=o1.getMinPressure();
						float value2=o2.getMinPressure();
						
						if (o1 == o2) {
							return 0;
						}
						// Compare
						if(value1<value2){
							return -1;
						}
						if(value1>value2){
							return 1;
						}
						return 0;		//when equal or one if null
					}
				};
	}
	// MAX PRESSURE
	public static Comparator<NodeHydraulicResult> getComp_NodeHydraulic_MaxPressure() {

		return new Comparator<NodeHydraulicResult>() {
					@Override
					public int compare(NodeHydraulicResult o1, NodeHydraulicResult o2) {
		
						float value1=o1.getMaxPressure();
						float value2=o2.getMaxPressure();
						
						if (o1 == o2) {
							return 0;
						}
						// Compare
						if(value1<value2){
							return -1;
						}
						if(value1>value2){
							return 1;
						}
						return 0;		//when equal or one if null
					}
				};
	}
	// AVG PRESSURE
	public static Comparator<NodeHydraulicResult> getComp_NodeHydraulic_AvgPressure() {

		return new Comparator<NodeHydraulicResult>() {
					@Override
					public int compare(NodeHydraulicResult o1, NodeHydraulicResult o2) {
		
						float value1=o1.getAvgPressure();
						float value2=o2.getAvgPressure();
						
						if (o1 == o2) {
							return 0;
						}
						// Compare
						if(value1<value2){
							return -1;
						}
						if(value1>value2){
							return 1;
						}
						return 0;		//when equal or one if null
					}
				};
	}
	//MIN HEAD
	public static Comparator<NodeHydraulicResult> getComp_NodeHydraulic_MinHead() {

		return new Comparator<NodeHydraulicResult>() {
					@Override
					public int compare(NodeHydraulicResult o1, NodeHydraulicResult o2) {
		
						float value1=o1.getMinHead();
						float value2=o2.getMinHead();
						
						if (o1 == o2) {
							return 0;
						}
						// Compare
						if(value1<value2){
							return -1;
						}
						if(value1>value2){
							return 1;
						}
						return 0;		//when equal or one if null
					}
				};
	}
	// MAX HEAD
	public static Comparator<NodeHydraulicResult> getComp_NodeHydraulic_MaxHead() {

		return new Comparator<NodeHydraulicResult>() {
					@Override
					public int compare(NodeHydraulicResult o1, NodeHydraulicResult o2) {
		
						float value1=o1.getMaxHead();
						float value2=o2.getMaxHead();
						
						if (o1 == o2) {
							return 0;
						}
						// Compare
						if(value1<value2){
							return -1;
						}
						if(value1>value2){
							return 1;
						}
						return 0;		//when equal or one if null
					}
				};
	}
	// AVG HEAD
	public static Comparator<NodeHydraulicResult> getComp_NodeHydraulic_AvgHead() {

		return new Comparator<NodeHydraulicResult>() {
					@Override
					public int compare(NodeHydraulicResult o1, NodeHydraulicResult o2) {
		
						float value1=o1.getAvgHead();
						float value2=o2.getAvgHead();
						
						if (o1 == o2) {
							return 0;
						}
						// Compare
						if(value1<value2){
							return -1;
						}
						if(value1>value2){
							return 1;
						}
						return 0;		//when equal or one if null
					}
				};
	}
	
								////////////////////////////////
								//   LINK HYDRAULIC DATA GRID
								///////////////////////////////
	
		//ID
		public static Comparator<LinkHydraulicResult> getComp_LinkHydraulic_Id() {

			return new Comparator<LinkHydraulicResult>() {
						@Override
						public int compare(LinkHydraulicResult o1, LinkHydraulicResult o2) {
			
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if (o1 != null) {
								
								return (o2 != null) ? o1.getLinkId().compareTo(
										o2.getLinkId()) : 1;
							}
							return -1;
						}
					};
		}
		//MIN FLOW
		public static Comparator<LinkHydraulicResult> getComp_LinkHydraulic_MinFlow() {

			return new Comparator<LinkHydraulicResult>() {
						@Override
						public int compare(LinkHydraulicResult o1, LinkHydraulicResult o2) {
			
							float value1=o1.getMinFlow();
							float value2=o2.getMinFlow();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		// MAX FLOW
		public static Comparator<LinkHydraulicResult> getComp_LinkHydraulic_MaxFlow() {

			return new Comparator<LinkHydraulicResult>() {
						@Override
						public int compare(LinkHydraulicResult o1, LinkHydraulicResult o2) {
			
							float value1=o1.getMaxFlow();
							float value2=o2.getMaxFlow();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		// AVG FLOW
		public static Comparator<LinkHydraulicResult> getComp_LinkHydraulic_AvgFlow() {

			return new Comparator<LinkHydraulicResult>() {
						@Override
						public int compare(LinkHydraulicResult o1, LinkHydraulicResult o2) {
			
							float value1=o1.getAvgFlow();
							float value2=o2.getAvgFlow();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		//MIN VELOCITY
		public static Comparator<LinkHydraulicResult> getComp_LinkHydraulic_MinVelocity() {

			return new Comparator<LinkHydraulicResult>() {
						@Override
						public int compare(LinkHydraulicResult o1, LinkHydraulicResult o2) {
			
							float value1=o1.getMinVelocity();
							float value2=o2.getMinVelocity();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		// MAX VELOCITY
		public static Comparator<LinkHydraulicResult> getComp_LinkHydraulic_MaxVelocity() {

			return new Comparator<LinkHydraulicResult>() {
						@Override
						public int compare(LinkHydraulicResult o1, LinkHydraulicResult o2) {
			
							float value1=o1.getMaxVelocity();
							float value2=o2.getMaxVelocity();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		// AVG VELOCITY
		public static Comparator<LinkHydraulicResult> getComp_LinkHydraulic_AvgVelocity() {

			return new Comparator<LinkHydraulicResult>() {
						@Override
						public int compare(LinkHydraulicResult o1, LinkHydraulicResult o2) {
			
							float value1=o1.getAvgVelocity();
							float value2=o2.getAvgVelocity();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		//MIN HEADLOSS
		public static Comparator<LinkHydraulicResult> getComp_LinkHydraulic_MinHeadloss() {

			return new Comparator<LinkHydraulicResult>() {
						@Override
						public int compare(LinkHydraulicResult o1, LinkHydraulicResult o2) {
			
							float value1=o1.getMinHeadloss();
							float value2=o2.getMinHeadloss();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		// MAX HEADLOSS
		public static Comparator<LinkHydraulicResult> getComp_LinkHydraulic_MaxHeadloss() {

			return new Comparator<LinkHydraulicResult>() {
						@Override
						public int compare(LinkHydraulicResult o1, LinkHydraulicResult o2) {
			
							float value1=o1.getMaxHeadloss();
							float value2=o2.getMaxHeadloss();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		// AVG HEADLOSS
		public static Comparator<LinkHydraulicResult> getComp_LinkHydraulic_AvgHeadloss() {

			return new Comparator<LinkHydraulicResult>() {
						@Override
						public int compare(LinkHydraulicResult o1, LinkHydraulicResult o2) {
			
							float value1=o1.getAvgHeadloss();
							float value2=o2.getAvgHeadloss();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		
		
			////////////////////////////////
			//   NODE AGE DATA GRID
			///////////////////////////////
		
		
		//ID
		public static Comparator<NodeAgeResult> getComp_NodeAge_Id() {

			return new Comparator<NodeAgeResult>() {
						@Override
						public int compare(NodeAgeResult o1, NodeAgeResult o2) {
			
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if (o1 != null) {
								
								return (o2 != null) ? o1.getNodeId().compareTo(
										o2.getNodeId()) : 1;
							}
							return -1;
						}
					};
		}
		//MIN AGE
		public static Comparator<NodeAgeResult> getComp_NodeAge_MinAge() {

			return new Comparator<NodeAgeResult>() {
						@Override
						public int compare(NodeAgeResult o1, NodeAgeResult o2) {
			
							float value1=o1.getMinAge();
							float value2=o2.getMinAge();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		// MAX AGE
		public static Comparator<NodeAgeResult> getComp_NodeAge_MaxAge() {

			return new Comparator<NodeAgeResult>() {
						@Override
						public int compare(NodeAgeResult o1, NodeAgeResult o2) {
			
							float value1=o1.getMaxAge();
							float value2=o2.getMaxAge();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		// AVG AGE
		public static Comparator<NodeAgeResult> getComp_NodeAge_AvgAge() {

			return new Comparator<NodeAgeResult>() {
						@Override
						public int compare(NodeAgeResult o1, NodeAgeResult o2) {
			
							float value1=o1.getAvgAge();
							float value2=o2.getAvgAge();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}

		
			////////////////////////////////
			//   NODE SOURCETRACE DATA GRID
			///////////////////////////////
		
		//ID
		public static Comparator<NodeSourceTraceResult> getComp_NodeSource_Id() {

			return new Comparator<NodeSourceTraceResult>() {
						@Override
						public int compare(NodeSourceTraceResult o1, NodeSourceTraceResult o2) {
			
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if (o1 != null) {
								
								return (o2 != null) ? o1.getNodeId().compareTo(
										o2.getNodeId()) : 1;
							}
							return -1;
						}
					};
		}
		//MIN SOURCE PCT
		public static Comparator<NodeSourceTraceResult> getComp_NodeSource_MinSourcePct() {

			return new Comparator<NodeSourceTraceResult>() {
						@Override
						public int compare(NodeSourceTraceResult o1, NodeSourceTraceResult o2) {
			
							float value1=o1.getMinSourceTrace();
							float value2=o2.getMinSourceTrace();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		// MAX SOURCE PCT
		public static Comparator<NodeSourceTraceResult> getComp_NodeSource_MaxSourcePct() {

			return new Comparator<NodeSourceTraceResult>() {
						@Override
						public int compare(NodeSourceTraceResult o1, NodeSourceTraceResult o2) {
			
							float value1=o1.getMaxSourceTrace();
							float value2=o2.getMaxSourceTrace();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		// AVG SOURCE PCT
		public static Comparator<NodeSourceTraceResult> getComp_NodeSource_AvgSourcePct() {

			return new Comparator<NodeSourceTraceResult>() {
						@Override
						public int compare(NodeSourceTraceResult o1, NodeSourceTraceResult o2) {
			
							float value1=o1.getAvgSourceTrace();
							float value2=o2.getAvgSourceTrace();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		
			////////////////////////////////
			//   NODE CHEMICAL DATA GRID
			///////////////////////////////

		//ID
		public static Comparator<NodeChemicalResult> getComp_NodeChemical_Id() {

			return new Comparator<NodeChemicalResult>() {
						@Override
						public int compare(NodeChemicalResult o1, NodeChemicalResult o2) {
			
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if (o1 != null) {
								
								return (o2 != null) ? o1.getNodeId().compareTo(
										o2.getNodeId()) : 1;
							}
							return -1;
						}
					};
		}
		//MIN CHEMICAL CON
		public static Comparator<NodeChemicalResult> getComp_NodeChemical_MinChemicalCon() {

			return new Comparator<NodeChemicalResult>() {
						@Override
						public int compare(NodeChemicalResult o1, NodeChemicalResult o2) {
			
							float value1=o1.getMinChemical();
							float value2=o2.getMinChemical();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		// MAX CHEMICAL CON
		public static Comparator<NodeChemicalResult> getComp_NodeChemical_MaxChemicalCon() {

			return new Comparator<NodeChemicalResult>() {
						@Override
						public int compare(NodeChemicalResult o1, NodeChemicalResult o2) {
			
							float value1=o1.getMaxChemical();
							float value2=o2.getMaxChemical();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		// AVG CHEMICAL CON
		public static Comparator<NodeChemicalResult> getComp_NodeChemical_AvgChemicalCon() {

			return new Comparator<NodeChemicalResult>() {
						@Override
						public int compare(NodeChemicalResult o1, NodeChemicalResult o2) {
			
							float value1=o1.getAvgChemical();
							float value2=o2.getAvgChemical();
							
							if (o1 == o2) {
								return 0;
							}
							// Compare
							if(value1<value2){
								return -1;
							}
							if(value1>value2){
								return 1;
							}
							return 0;		//when equal or one if null
						}
					};
		}
		

}
