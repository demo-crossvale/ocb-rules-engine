package com.model;

import java.io.Serializable;

public class RulesFact implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private com.model.RulesEngineRequest rulesRequest;
	private com.model.RulesEngineResponse rulesResponse;

	public RulesFact() {
		rulesRequest = new com.model.RulesEngineRequest();
		rulesResponse = new com.model.RulesEngineResponse();
		
	}
	
	public RulesFact(RulesEngineRequest rulesRequest, RulesEngineResponse rulesResponse) {
		this.rulesRequest = rulesRequest;
		this.rulesResponse = rulesResponse;
	}

	public com.model.RulesEngineRequest getRulesRequest() {
		return rulesRequest;
	}

	public void setRulesRequest(com.model.RulesEngineRequest rulesRequest) {
		this.rulesRequest = rulesRequest;
	}

	public com.model.RulesEngineResponse getRulesResponse() {
		return rulesResponse;
	}

	public void setRulesResponse(com.model.RulesEngineResponse rulesResponse) {
		this.rulesResponse = rulesResponse;
	}
	
}
