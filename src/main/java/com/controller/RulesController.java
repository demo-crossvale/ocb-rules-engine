package com.controller;

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
import com.model.RulesEngineRequest;
import com.model.RulesEngineResponse;
import com.model.RulesFact;
import com.service.RulesService;

@RestController
public class RulesController {

	private final RulesService rulesService;

	@Autowired
	public RulesController(RulesService rulesService) {
		this.rulesService = rulesService;
	}

	@RequestMapping(value="/cloudbalancer", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	@ResponseBody
	public String cloudBalance(@RequestBody RulesEngineRequest request) throws Exception{
		RulesFact inputFact = new RulesFact();
		RulesFact outputFact = new RulesFact();
		boolean invalidState = false;
		
		System.out.println("<== Begin: Rules Engine ==>");
		
		try {
			BeanUtils.copyProperties(request, inputFact.getRulesRequest());
			// Set time
			outputFact.getRulesRequest().setCurrentDateTime(inputFact.getRulesRequest().getCurrentDateTime());
			
			for(FleetInfo fi: inputFact.getRulesRequest().getFleetInfo()) {
				rulesService.cloudBalancer(fi);
				// Update output
				outputFact.getRulesRequest().getFleetInfo().add(fi);
				FleetChange fc = new FleetChange();
				fc.setFleetId(fi.getFleetId());
				fc.setNewTargetCapacity(fi.getIntendedTargetCapacity());
				outputFact.getRulesResponse().addFleetsChange(fc);
			}
			
		}catch (BeansException be) {
			System.out.println("Exception occurred copying request info to inputFact!");
			invalidState = true;
			throw new Exception();
		}catch (Exception e) {
			System.out.println("Unknown Exception occurred!");
			invalidState = true;
			e.printStackTrace();
			throw new Exception();
		}
	
		if(invalidState) {
			outputFact.getRulesResponse().setStatus("invalid");
		}
		
		String jsonResponse = getJson(outputFact.getRulesResponse());
		System.out.println("<== End: Rules Engine ==>");
		return jsonResponse;

	}

	private String getJson(RulesEngineResponse response) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonResponse = null;
		try {
			jsonResponse = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			System.out.println("Error converting response into JSON format!");
			e.printStackTrace();
			throw new Exception();
		}
		return jsonResponse;
	}

}
