package com.nkb.coronaVirusTracker;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class CoronaVirusDataService {

	
	RestTemplate restTemplate = new RestTemplate();
	 private static String DATA_URL= "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/";
	@PostConstruct
	@Scheduled(cron = "0 * * ? * *")
	public List<LocationStats> getCoronaVirusData() throws IOException {
		SimpleDateFormat sdfmt= new SimpleDateFormat("MM-dd-yyyy");
		Date dateNow = new Date();
		dateNow.setDate(dateNow.getDate()-1);
		String date = sdfmt.format(dateNow);
		String result  = restTemplate.getForObject(DATA_URL+date+".csv", String.class);
        StringReader csvStringReader = new StringReader(result);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvStringReader);
        List<LocationStats> listOfLocs = new ArrayList<>();
		for (CSVRecord record : records) {
			LocationStats locationStats = new LocationStats();
			locationStats.setCountry(record.get("Country/Region"));
			locationStats.setState(record.get("Province/State"));
			locationStats.setConfirmedCases(Integer.parseInt(record.get("Confirmed")));
			locationStats.setDeaths(Integer.parseInt(record.get("Deaths")));
			locationStats.setRecoveredCases(Integer.parseInt(record.get("Recovered")));
		   listOfLocs.add(locationStats);
		}
		
		return listOfLocs;
	}
}
