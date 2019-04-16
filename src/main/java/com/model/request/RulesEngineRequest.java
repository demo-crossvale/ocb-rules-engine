package com.model.request;

import java.io.Serializable;
import java.util.ArrayList;

public class RulesEngineRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private java.lang.String currentDateTime;
	private ArrayList<com.model.FleetInfo> fleetInfo;
	private ArrayList<com.model.RoleInfo> roleInfo;

	public RulesEngineRequest() {
		fleetInfo =  new ArrayList<com.model.FleetInfo>();
		roleInfo =  new ArrayList<com.model.RoleInfo>();
	}

	public RulesEngineRequest(java.lang.String currentDateTime,
			ArrayList<com.model.FleetInfo> fleetInfo,
			ArrayList<com.model.RoleInfo> roleInfo) {
		this.currentDateTime = currentDateTime;
		this.fleetInfo = fleetInfo;
		this.roleInfo = roleInfo;
	}

	public java.lang.String getCurrentDateTime() {
		return currentDateTime;
	}

	public void setCurrentDateTime(java.lang.String currentDateTime) {

		this.currentDateTime = currentDateTime;
	}

	public ArrayList<com.model.FleetInfo> getFleetInfo() {
		return fleetInfo;
	}

	public void setFleetInfo(ArrayList<com.model.FleetInfo> fleetInfo) {
		this.fleetInfo = fleetInfo;
	}

	public ArrayList<com.model.RoleInfo> getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(ArrayList<com.model.RoleInfo> roleInfo) {
		this.roleInfo = roleInfo;
	}

	public void addRoleInfo(com.model.RoleInfo roleInfo) {
		this.roleInfo.add(roleInfo);
	}

}
