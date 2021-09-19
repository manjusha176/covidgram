package com.nuance.covidgram.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuance.covidgram.service.CovidgramService;

import lombok.extern.slf4j.Slf4j;

/**
 * The main controller handling the runtime requests.
 */
@Controller
@Slf4j
public class CovidgramController {
    
	@Autowired
	private CovidgramService covidgramService;
	
    @RequestMapping(value = "/start", method={RequestMethod.POST, RequestMethod.GET})
    public String start(
    		HttpServletRequest request,
            HttpSession httpSession,
            Model model,
            @RequestParam(name="symptom", required = true) String symptom,
            @RequestParam(name="severity", required = true) String severity,
            @RequestParam(name="location", required = true) String location
            )
    {
    	log.info("Start params: symptom [{}], severity [{}], location [{}]", symptom, severity, location);
    	
    	try {
    		
			covidgramService.constructGeoJson(symptom, severity, location);
			
		} catch (Exception e) {
			
			log.error("Error {} ", e.getMessage());
			e.printStackTrace();
			return "error_vxml";
		}
    	
    	//return ResponseEntity.ok().body("Data sent to heatmap successfully..");
    	return "vxml_base";
    }

}
