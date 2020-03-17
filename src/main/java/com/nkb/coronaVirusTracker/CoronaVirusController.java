package com.nkb.coronaVirusTracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nkb/covid-19-tracker")
@CrossOrigin
public class CoronaVirusController {

	@Autowired
	private CoronaVirusDataService service;
	
	@GetMapping(path = "/locationStats")
	public ResponseEntity<?> getCovid19LocationStats(){
		LocationStatsResponseEntity response = new LocationStatsResponseEntity();
		int totalConfirmedCases =0;
		int totalDeathCases = 0;
		int totalRecoveredCases = 0;
		Map<String,LocationStats> map = new HashMap<>();
		try {
			List<LocationStats> locationStatsList = service.getCoronaVirusData();
			totalConfirmedCases = locationStatsList.stream().mapToInt(location -> location.getConfirmedCases()).sum();
			totalDeathCases = locationStatsList.stream().mapToInt( location -> location.getDeaths()).sum();
			totalRecoveredCases = locationStatsList.stream().mapToInt( location -> location.getRecoveredCases()).sum();
			for(LocationStats locationStat:locationStatsList) {
				if(map.containsKey(locationStat.getCountry())) {
					LocationStats location = map.get(locationStat.getCountry());
					location.setConfirmedCases(location.getConfirmedCases() + locationStat.getConfirmedCases());
					location.setDeaths(location.getDeaths() + locationStat.getDeaths());
					location.setRecoveredCases(location.getRecoveredCases() + locationStat.getRecoveredCases());
				}
				else
				map.put(locationStat.getCountry(), locationStat);
			}
			List<LocationStats> sortedLocationsList = new ArrayList<LocationStats>();
			sortedLocationsList.addAll(new ArrayList<LocationStats>( map.values()));
			Collections.sort(sortedLocationsList, new SortLocationByCountry()); 
			
			response.getLocationStatsList().addAll(sortedLocationsList);
			response.setTotalConfirmedCases(totalConfirmedCases);
			response.setTotalDeaths(totalDeathCases);
			response.setTotalRecoveredCases(totalRecoveredCases);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<LocationStatsResponseEntity>(response,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<LocationStatsResponseEntity>(response,HttpStatus.OK);
		
	}
}
