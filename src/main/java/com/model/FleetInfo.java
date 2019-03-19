package com.model;

import java.io.Serializable;

public class FleetInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private java.lang.String fleetId;
	private java.lang.String role;
	private java.lang.Integer currentCapacity;
	private java.lang.Integer intendedTargetCapacity;
	private java.lang.Integer currentCost;
	private java.lang.Integer currentSaving; // Percent
	private java.lang.Integer currentTime;
	
	public FleetInfo() {
		
	}

	public FleetInfo(String fleetId, String role, 
			Integer currentCapacity, Integer intendedTargetCapacity,
			Integer currentCost, Integer currentSaving, Integer currentTime) {
		
		this.fleetId = fleetId;
		this.role = role;
		this.currentCapacity = currentCapacity;
		this.intendedTargetCapacity = intendedTargetCapacity;
		this.currentCost = currentCost;
		this.currentSaving = currentSaving;
		this.currentTime = currentTime;
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

	public java.lang.Integer getCurrentCost() {
		return currentCost;
	}

	public void setCurrentCost(java.lang.Integer currentCost) {
		this.currentCost = currentCost;
	}

	public java.lang.Integer getCurrentSaving() {
		return currentSaving;
	}

	public void setCurrentSaving(java.lang.Integer currentSaving) {
		this.currentSaving = currentSaving;
	}

	public java.lang.Integer getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(java.lang.Integer currentTime) {
		this.currentTime = currentTime;
	}
	
}
