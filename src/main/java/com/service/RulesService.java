package com.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.FleetInfo;
import com.model.RoleInfo;

@Service
public class RulesService {
	/** Rules Engine Service module.
	 * Executes rules against provided fact
	 * @author ragrahari
	**/

	private final KieContainer kieContainer;

	private final String[] RULES_ORDER = {"initialize","scheduler","adhoc","performance","workload","sanitycheck"};
	
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
	
	public boolean performanceSanityTrigger(RoleInfo roleInfo, FleetInfo fleetInfo) {
		boolean trigger = false;
		if(isPerformanceHigh(roleInfo) && roleInfo.getRoleTargetCapacity() < fleetInfo.getCurrentCapacity()) {
			trigger = true;
		}else if (isPerformanceLow(roleInfo) && roleInfo.getRoleTargetCapacity() > 1 
				&& roleInfo.getRoleTargetCapacity() >= fleetInfo.getCurrentCapacity()) {
			trigger = true;
		}
		return trigger;
	}

	private boolean isPerformanceLow(RoleInfo roleInfo) {
		if(roleInfo.getFlatPerformanceInfo().getCpu() < 15 && roleInfo.getFlatPerformanceInfo().getMemory() < 15) {
			return true;
		}
		return false;
	}

	private boolean isPerformanceHigh(RoleInfo roleInfo) {
		if(roleInfo.getFlatPerformanceInfo().getCpu() > 85 || roleInfo.getFlatPerformanceInfo().getMemory() > 85) {
			return true;
		}
		return false;
	}

}
