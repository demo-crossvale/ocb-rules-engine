package com.model;

import java.io.Serializable;

public class FleetInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private java.lang.String fleetId;
	private java.lang.String role;
	private java.lang.Integer currentCapacity;
	private java.lang.Integer intendedTargetCapacity;
	private java.lang.Float currentCost;
	private java.lang.Float currentSaving; // Percent
	//private java.lang.Integer currentTime;
	
	public FleetInfo() {
		
	}

	public FleetInfo(String fleetId, String role, 
			Integer currentCapacity, Integer intendedTargetCapacity,
			Float currentCost, Float currentSaving, Integer currentTime) {
		
		this.fleetId = fleetId;
		this.role = role;
		this.currentCapacity = currentCapacity;
		this.intendedTargetCapacity = intendedTargetCapacity;
		this.currentCost = currentCost;
		this.currentSaving = currentSaving;
		//this.currentTime = currentTime;
	}

	public java.lang.String getFleetId() {
		return fleetId;
	}

	public void setFleetId(java.lang.String fleetId) {
		this.fleetId = fleetId;
	}

	public java.lang.String getRole() {
		return role;
	}

	public void setRole(java.lang.String role) {
		this.role = role;
	}

	public java.lang.Integer getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(java.lang.Integer currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public java.lang.Integer getIntendedTargetCapacity() {
		return intendedTargetCapacity;
	}

	public void setIntendedTargetCapacity(java.lang.Integer intendedTargetCapacity) {
		this.intendedTargetCapacity = intendedTargetCapacity;
	}

	public java.lang.Float getCurrentCost() {
		return currentCost;
	}

	public void setCurrentCost(java.lang.Float currentCost) {
		this.currentCost = currentCost;
	}

	public java.lang.Float getCurrentSaving() {
		return currentSaving;
	}

	public void setCurrentSaving(java.lang.Float currentSaving) {
		this.currentSaving = currentSaving;
	}

	/*public java.lang.Integer getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(java.lang.Integer currentTime) {
		this.currentTime = currentTime;
	}*/
	
}
