package com.nkb.coronaVirusTracker;

import java.io.Serializable;


public class LocationStats implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4602587753395273479L;
	private String country;
	private String state;
	private int confirmedCases;
    private int deaths;
    private int recoveredCases;
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the confirmedCases
	 */
	public int getConfirmedCases() {
		return confirmedCases;
	}
	/**
	 * @param confirmedCases the confirmedCases to set
	 */
	public void setConfirmedCases(int confirmedCases) {
		this.confirmedCases = confirmedCases;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public int getRecoveredCases() {
		return recoveredCases;
	}
	public void setRecoveredCases(int recoveredCases) {
		this.recoveredCases = recoveredCases;
	}	
}
