package com.model;

import java.io.Serializable;

import com.model.request.RulesEngineRequest;
import com.model.response.RulesEngineResponse;

public class RulesFact implements Serializable {
	
	/**
	 * @author ragrahari
	 */

	private static final long serialVersionUID = 1L;
	
	private com.model.request.RulesEngineRequest rulesRequest;
	private com.model.response.RulesEngineResponse rulesResponse;

	public RulesFact() {
		rulesRequest = new com.model.request.RulesEngineRequest();
		rulesResponse = new com.model.response.RulesEngineResponse();
		
	}
	
	public RulesFact(RulesEngineRequest rulesRequest, RulesEngineResponse rulesResponse) {
		this.rulesRequest = rulesRequest;
		this.rulesResponse = rulesResponse;
	}

	public com.model.request.RulesEngineRequest getRulesRequest() {
		return rulesRequest;
	}

	public void setRulesRequest(com.model.request.RulesEngineRequest rulesRequest) {
		this.rulesRequest = rulesRequest;
	}

	public com.model.response.RulesEngineResponse getRulesResponse() {
		return rulesResponse;
	}

	public void setRulesResponse(com.model.response.RulesEngineResponse rulesResponse) {
		this.rulesResponse = rulesResponse;
	}
	
}
