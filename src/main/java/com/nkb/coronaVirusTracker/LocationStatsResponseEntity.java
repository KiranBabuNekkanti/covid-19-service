package com.nkb.coronaVirusTracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocationStatsResponseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3494131787500542269L;
    private List<LocationStats> locationStatsList = new ArrayList<>();
    private int totalConfirmedCases = 0;
    private int totalDeaths = 0;
    private int totalRecoveredCases = 0;
	/**
	 * @return the locationStatsList
	 */
	public List<LocationStats> getLocationStatsList() {
		return locationStatsList;
	}
	/**
	 * @param locationStatsList the locationStatsList to set
	 */
	public void setLocationStatsList(List<LocationStats> locationStatsList) {
		this.locationStatsList = locationStatsList;
	}
	/**
	 * @return the totalConfirmedCases
	 */
	public int getTotalConfirmedCases() {
		return totalConfirmedCases;
	}
	/**
	 * @param totalConfirmedCases the totalConfirmedCases to set
	 */
	public void setTotalConfirmedCases(int totalConfirmedCases) {
		this.totalConfirmedCases = totalConfirmedCases;
	}
	public int getTotalDeaths() {
		return totalDeaths;
	}
	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}
	public int getTotalRecoveredCases() {
		return totalRecoveredCases;
	}
	public void setTotalRecoveredCases(int totalRecoveredCases) {
		this.totalRecoveredCases = totalRecoveredCases;
	}    
}
