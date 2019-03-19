package com.service;

import org.kie.api.runtime.Calendars;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.FleetInfo;
import com.model.RulesEngineRequest;
import com.model.RulesEngineResponse;

@Service
public class RulesService {

	private final KieContainer kieContainer;

	private final String[] RULE_FLOW_GROUP_ORDER = {"initialize","override","default",
														"scheduler","overloaded","underloaded",
														"finetune","upperlimit","lowerlimit"};
	
	@Autowired
	public RulesService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
	
	public FleetInfo cloudBalancer(FleetInfo fleetFact) {
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		//Calendars cal = kieSession.getCalendars();
		kieSession.insert(fleetFact);
		System.out.println("FleetId: "+fleetFact.getFleetId());
		for(String group: RULE_FLOW_GROUP_ORDER) {
			kieSession.getAgenda().getAgendaGroup(group).setFocus();
			kieSession.fireAllRules();
		}
		kieSession.dispose();
		return fleetFact;
	}

}
