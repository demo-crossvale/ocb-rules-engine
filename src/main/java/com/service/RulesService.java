package com.service;

import org.kie.api.runtime.Calendars;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.FleetInfo;
import com.model.PerformanceInfo;
import com.model.RoleInfo;
import com.model.request.RulesEngineRequest;
import com.model.response.RulesEngineResponse;

@Service
public class RulesService {

	private final KieContainer kieContainer;

	//private final String[] RULE_FLOW_GROUP_ORDER = {"initialize","override","default",
	//		"scheduler","overloaded","underloaded",
	//		"finetune","upperlimit","lowerlimit"};
	
	private final String[] RULES_ORDER = {"initialize","scheduler","adhoc","performance","workload"};
	
	@Autowired
	public RulesService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	public RoleInfo roleBalancer(RoleInfo roleFact) {
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(roleFact);
		for(String group: RULES_ORDER) {
			kieSession.getAgenda().getAgendaGroup(group).setFocus();
			kieSession.fireAllRules();
		}
		kieSession.dispose();
		return roleFact;
	}
	
	/*
	public FleetInfo cloudBalancer(FleetInfo fleetFact) {
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		//Calendars cal = kieSession.getCalendars();
		kieSession.insert(fleetFact);
		// Need to fire rules on roleInfo and not fleetInfo
		System.out.println("FleetId: "+fleetFact.getFleetId());
		for(String group: RULE_FLOW_GROUP_ORDER) {
			kieSession.getAgenda().getAgendaGroup(group).setFocus();
			kieSession.fireAllRules();
		}
		kieSession.dispose();
		return fleetFact;
	}
	
	public PerformanceInfo balancer(PerformanceInfo performanceFact) {
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(performanceFact);
		for(String group: RULES_ORDER) {
			kieSession.getAgenda().getAgendaGroup(group).setFocus();
			kieSession.fireAllRules();
		}
		kieSession.dispose();
		return performanceFact;
	}*/
	

}
