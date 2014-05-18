//object instances of these classes represent the summary results of a SINGLE component (link or node) for a specific type of simulation.

package com.watergrid.dst.client.dataobjects;

public class NodeHydraulicResult {
	
	String nodeId;
	float minPressure,maxPressure, avgPressure;
	float minHead,maxHead, avgHead;
	
	
	public NodeHydraulicResult(String nodeId, float minPressure,float maxPressure, float avgPressure, float minHead, float maxHead,float avgHead) {
		this.nodeId = nodeId;
		this.minPressure = minPressure;
		this.maxPressure = maxPressure;
		this.avgPressure = avgPressure;
		this.minHead = minHead;
		this.maxHead = maxHead;
		this.avgHead = avgHead;
	}


	public String getNodeId() {
		return nodeId;
	}


	public float getMinPressure() {
		return minPressure;
	}


	public float getMaxPressure() {
		return maxPressure;
	}


	public float getAvgPressure() {
		return avgPressure;
	}


	public float getMinHead() {
		return minHead;
	}


	public float getMaxHead() {
		return maxHead;
	}


	public float getAvgHead() {
		return avgHead;
	}
	
	

	
	

}
