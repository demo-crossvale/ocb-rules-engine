package com.model.request;

import java.io.Serializable;
import java.util.ArrayList;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.model.FleetInfo;
import com.model.RoleInfo;

public class RulesEngineRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private java.lang.String currentDateTime;
	private ArrayList<com.model.FleetInfo> fleetInfo;
	private ArrayList<com.model.RoleInfo> roleInfo;

	public RulesEngineRequest() {
		currentDateTime = "";
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
		DateTimeFormatter parser = ISODateTimeFormat.dateTimeNoMillis();
		try {
			this.currentDateTime = String.valueOf(parser.parseDateTime(currentDateTime).getHourOfDay()*100);
		}catch(Exception e) {
			this.currentDateTime = currentDateTime;
		}
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
