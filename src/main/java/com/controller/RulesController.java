package com.controller;

import java.util.ArrayList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.FleetChange;
import com.model.FleetInfo;
import com.model.PerformanceInfo;
import com.model.RoleInfo;
import com.model.RulesFact;
import com.model.request.RulesEngineRequest;
import com.service.RulesService;

@RestController
public class RulesController {
	/** Controller for Rules Engine. REST requests are consumed here
	 * @author ragrahari
	 **/

	private final RulesService rulesService;

	@Autowired
	public RulesController(RulesService rulesService) {
		this.rulesService = rulesService;
	}

	@RequestMapping(value="/cloudbalancer", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	@ResponseBody
	public String cloudBalance(@RequestBody RulesEngineRequest request) throws Exception{

		// Step 1: Fire rules to calculate intendedTargetCapacity for each roles
		RulesFact inputFact = new RulesFact();
		RulesFact outputFact = new RulesFact();
		boolean invalidState = false;

		System.out.println("<== Begin: Rules Engine ==>");

		try {
			if(request.getCurrentDateTime()==null) {
				invalidState = true;
				System.out.println("Current Date-Time not set!");
				throw new Exception();
			}
			BeanUtils.copyProperties(request, inputFact.getRulesRequest());
			// Flat out PerformanceInfo[]
			calculateFlatPerformanceInfo(inputFact.getRulesRequest());
			for(RoleInfo ri: inputFact.getRulesRequest().getRoleInfo()) {
				// Execute rules per role:
				System.out.println("<-- Working on role: "+ri.getRole()+" -->");
				rulesService.roleBalancer(ri);
				outputFact.getRulesRequest().addRoleInfo(ri);
			}
			System.out.println("<== End: Rules Engine ==>");

			// Step 2: Calculate FleetsChange
			calculateFleetsChange(inputFact, outputFact);

		}catch (BeansException be) {
			System.out.println("Exception occurred copying request info to inputFact!");
			invalidState = true;
			be.printStackTrace();
			//throw new Exception();
		}catch(NullPointerException ne) {
			System.out.println("Null RulesRequest in input!");
			invalidState = true;
			ne.printStackTrace();
			//throw new Exception();
		}catch (Exception e) {
			System.out.println("Exception occurred!");
			invalidState = true;
			e.printStackTrace();
			//throw new Exception();
		}

		if(invalidState) {
			outputFact.getRulesResponse().setStatus("invalid");
		}

		/*
		 * 	1. Rule: Scheduled
		 *  	- based on performanceInfo.dateTime
		 * 	2. Rule: Performance based
		 * 		- based on cpu and memory
		 * 	3. Rule: Workload based
		 * 		** Need to understand this later
		 * */

		String jsonResponse = getJson(outputFact.getRulesResponse());
		System.out.println("<<=====================>>\n");
		return jsonResponse;

	}

	private void calculateFlatPerformanceInfo(RulesEngineRequest rulesRequest) throws NullPointerException {

		if(null == rulesRequest || null == rulesRequest.getRoleInfo()) {
			throw new NullPointerException("Null RulesRequest in input!");
		}
		if(rulesRequest.getRoleInfo().size()>0) {
			for(RoleInfo role: rulesRequest.getRoleInfo()) {
				int size = role.getPerformanceInfo().size();
				PerformanceInfo flatPI = new PerformanceInfo();
				double cpuSum = 0;
				double memorySum = 0;
				for(PerformanceInfo pi: role.getPerformanceInfo()) {
					cpuSum += pi.getCpu();
					memorySum += pi.getMemory();
					// Selecting latest dateTime because the average is for last few minutes:
					flatPI.setDateTime(pi.getDateTime());
				}
				flatPI.setCpu(cpuSum/size);
				flatPI.setMemory(memorySum/size);
				role.setFlatPerformanceInfo(flatPI);
			}
		}
	}

	private void calculateFleetsChange(RulesFact inputFact, RulesFact outputFact) {

		//*TODO: Need to update this method to make decision on both savings and cost, also multiple fleets can be updated*//
		for(RoleInfo ri: outputFact.getRulesRequest().getRoleInfo()) {
			java.util.ArrayList<com.model.FleetInfo> fleetList = getRoleFleets(inputFact.getRulesRequest().getFleetInfo(),ri.getRole());
			Integer fleetTargetSum = targetCapacitySum(fleetList);

			FleetChange fc = null;
			// No change if fleets already are at target capacity
			if(fleetTargetSum < ri.getRoleTargetCapacity()) { 
				// Scale up
				System.out.print("Scaling Up \n >> role: "+ri.getRole());
				FleetInfo maxSavingFleet = findMaxSavingFleet(fleetList);
				if(maxSavingFleet == null) {
					System.out.println("Error computing max saving Fleets");
				}else {
					fc = new FleetChange();
					System.out.println(" >> fleet: "+maxSavingFleet.getFleetId());
					Integer capacityDiff = (ri.getRoleTargetCapacity() - fleetTargetSum); 
					fc.setFleetId(maxSavingFleet.getFleetId());
					fc.setNewTargetCapacity(maxSavingFleet.getCurrentCapacity()+capacityDiff);
				}
			} else if(fleetTargetSum > ri.getRoleTargetCapacity()) { 
				// Scale down
				System.out.print("Scaling Down \n >> role: "+ri.getRole());
				java.util.ArrayList<com.model.FleetInfo> ignoreMinFleets = new java.util.ArrayList<com.model.FleetInfo>();
				while(true) {
					FleetInfo minSavingFleet = findMinSavingFleet(fleetList, ignoreMinFleets);
					if(minSavingFleet == null) {
						System.out.println("\nNo fleets eligible for scale down");
						break;
					}
 					Integer capacityDiff = (fleetTargetSum - ri.getRoleTargetCapacity()); 
					int newTarget = minSavingFleet.getCurrentCapacity()-capacityDiff;
					// make sure the targetFleet don't go below 0 
					if(newTarget > 0) {
						fc = new FleetChange();
						System.out.println(" >> fleet: "+minSavingFleet.getFleetId());
						fc.setFleetId(minSavingFleet.getFleetId());
						fc.setNewTargetCapacity(newTarget);
						break;
					}else {
						ignoreMinFleets.add(minSavingFleet); // Find another minSavingFleet
					}
				}
			}
			if(fc!=null) {
				outputFact.getRulesResponse().addFleetsChange(fc);
			}
		}
	}

	private FleetInfo findMinSavingFleet(ArrayList<FleetInfo> fleetList, ArrayList<FleetInfo> ignoreMinFleets) {
		FleetInfo minFi = null;
		double minSaving = 100;
		for(FleetInfo f: fleetList) {
			if(!ignoreMinFleets.contains(f) && f.getCurrentSaving()!=null && (minSaving > f.getCurrentSaving())) {
				minSaving = f.getCurrentSaving();
				minFi = f;
			}
		}
		return minFi;
	}

	private FleetInfo findMaxSavingFleet(ArrayList<FleetInfo> fleetList) {
		FleetInfo maxFi = null;
		double maxSaving = 0;
		for(FleetInfo f: fleetList) {
			if(f.getCurrentSaving()!=null && (maxSaving < f.getCurrentSaving())) {
				maxSaving = f.getCurrentSaving();
				maxFi = f;
			}
		}
		return maxFi;
	}

	private Integer targetCapacitySum(ArrayList<FleetInfo> fleetList) {
		Integer sum=0;
		for(FleetInfo f: fleetList) {
			sum +=f.getCurrentCapacity();
		}
		return sum;
	}

	private ArrayList<FleetInfo> getRoleFleets(ArrayList<FleetInfo> fleetInfo, String role) {
		ArrayList<FleetInfo> fis = new ArrayList<FleetInfo>();
		for(FleetInfo f: fleetInfo) {
			if(f.getRole().equals(role)) {
				fis.add(f);
			}
		}
		return fis;
	}

	private String getJson(Object input) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonResponse = null;
		try {
			jsonResponse = mapper.writeValueAsString(input);
		} catch (JsonProcessingException e) {
			System.out.println("Error converting response into JSON format!");
			e.printStackTrace();
			throw new Exception();
		}
		return jsonResponse;
	}

}
