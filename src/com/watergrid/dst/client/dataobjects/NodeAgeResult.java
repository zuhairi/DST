//object instances of these classes represent the summary results of a SINGLE component (link or node) for a specific type of simulation.

package com.watergrid.dst.client.dataobjects;

public class NodeAgeResult {
	
	String nodeId;
	float minAge,maxAge, avgAge;
	public NodeAgeResult(String nodeId, float minAge, float maxAge, float avgAge) {
		this.nodeId = nodeId;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.avgAge = avgAge;
	}
	public String getNodeId() {
		return nodeId;
	}
	public float getMinAge() {
		return minAge;
	}
	public float getMaxAge() {
		return maxAge;
	}
	public float getAvgAge() {
		return avgAge;
	}
	
	
	
	

}
