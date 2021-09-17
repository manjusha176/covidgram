package com.nuance.covidgram.service;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CovidgramService {

	ObjectMapper objectMapper = new ObjectMapper();

	public void constructGeoJson(String symptom, String severity, String location) {
		try {
			List<Double> locCoordinates = getLocationCoordinates(location);
			log.info("location co-ordinates are {} ", locCoordinates);
			LinkedHashMap<String, Object> feature = createFeatureObject(symptom, severity, locCoordinates);
			log.info("Constructed feature object {} ", feature);
			updateGeoJsonFile(feature);
		} catch (Exception e) {
			log.error("Error {} ", e.getMessage());
			e.printStackTrace();
		}
	}

	private List<Double> getLocationCoordinates(String location) {
		if (location.equalsIgnoreCase("Marrett Rd")) {
			return Arrays.asList(-71.207190, 42.496503);
		} else if (location.equalsIgnoreCase("Spring Valley Rd")) {
			return Arrays.asList(-71.185384, 42.520258);
		} else {
			return Arrays.asList(-71.191587, 42.525692);
		}
	}
	
	private LinkedHashMap<String, Object> createFeatureObject(String symptom, String severity, List<Double> locCoordinates){

		LinkedHashMap<String, Object> feature = new LinkedHashMap<>();
		feature.put("type", "Feature");
		feature.put("properties", Map.of("sev", severity, "symp", symptom));
		feature.put("geometry", Map.of("type", "Point", "coordinates", locCoordinates));

		return feature;
	}
	
	private void updateGeoJsonFile(LinkedHashMap<String, Object> feature) throws Exception {
		
		synchronized (this) {
			Map<String, Object> resultMap = objectMapper.readValue(new URL("classpath:static/patients.geojson"), new TypeReference<Map<String, Object>>() {});

			List<Object> features = objectMapper.convertValue(resultMap.get("features"), new TypeReference<List<Object>>() {});
			features.add(feature);

			resultMap.put("features", features);
			objectMapper.writeValue(ResourceUtils.getFile("classpath:static/patients.geojson"), resultMap);
			
			log.info("updated geojson file");
		}
	
	}

}
