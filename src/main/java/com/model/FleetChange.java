package com.model;

public class FleetChange implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private java.lang.String fleetId;
	private java.lang.Integer newTargetCapacity;
	

	public FleetChange(){

	}
	
	public FleetChange(java.lang.String fleetId, Integer newTargetCapacity){
		this.fleetId = fleetId;
		this.newTargetCapacity = newTargetCapacity;
	}

	public java.lang.String getFleetId() {
		return fleetId;
	}

	public void setFleetId(java.lang.String fleetId) {
		this.fleetId = fleetId;
	}

	public java.lang.Integer getNewTargetCapacity() {
		return newTargetCapacity;
	}

	public void setNewTargetCapacity(java.lang.Integer newTargetCapacity) {
		this.newTargetCapacity = newTargetCapacity;
	}

}
