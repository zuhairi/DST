//object instances of these classes represent the summary results of a SINGLE component (link or node) for a specific type of simulation.

package com.watergrid.dst.client.dataobjects;

public class NodeSourceTraceResult {
	
	String nodeId;
	float minSourceTrace,maxSourceTrace, avgSourceTrace;
	
	
	public NodeSourceTraceResult(String nodeId, float minSourceTrace,float maxSourceTrace, float avgSourceTrace) {
		this.nodeId = nodeId;
		this.minSourceTrace = minSourceTrace;
		this.maxSourceTrace = maxSourceTrace;
		this.avgSourceTrace = avgSourceTrace;
	}


	public String getNodeId() {
		return nodeId;
	}


	public float getMinSourceTrace() {
		return minSourceTrace;
	}


	public float getMaxSourceTrace() {
		return maxSourceTrace;
	}


	public float getAvgSourceTrace() {
		return avgSourceTrace;
	}
	
	
	
	
	

}
