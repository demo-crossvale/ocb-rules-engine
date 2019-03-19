package com.model;

import java.io.Serializable;
import java.util.ArrayList;

public class RulesEngineRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private java.lang.String currentDateTime;
	private ArrayList<com.model.FleetInfo> fleetInfo;

	public RulesEngineRequest() {
		currentDateTime = "";
		fleetInfo =  new ArrayList<com.model.FleetInfo>();
	}

	public RulesEngineRequest(java.lang.String currentDateTime,
			ArrayList<com.model.FleetInfo> fleetInfo) {
		this.currentDateTime = currentDateTime;
		this.fleetInfo = fleetInfo;
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

}
