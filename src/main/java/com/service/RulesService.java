package com.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.RoleInfo;

@Service
public class RulesService {

	private final KieContainer kieContainer;

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

}
