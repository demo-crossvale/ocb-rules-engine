package com.model;

import java.io.Serializable;

public class RulesEngineResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private java.lang.String status;
	private java.util.ArrayList<com.model.FleetChange> fleetsChange;
	
	public RulesEngineResponse() {
		status = "valid";
		fleetsChange = new java.util.ArrayList<com.model.FleetChange>();
	}
	
	public RulesEngineResponse(java.util.ArrayList<com.model.FleetChange> fleetsChange,
			java.lang.String status) {
		this.fleetsChange = fleetsChange;
		this.status = status;
	}

	public java.util.List<com.model.FleetChange> getFleetsChange() {
		return fleetsChange;
	}

	public void addFleetsChange(com.model.FleetChange fleet) {
		java.util.ArrayList<com.model.FleetChange> fc = this.fleetsChange;
		fc.add(fleet);
		this.fleetsChange = fc;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
}
