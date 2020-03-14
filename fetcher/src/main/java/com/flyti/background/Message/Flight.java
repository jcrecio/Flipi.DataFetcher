package com.flyti.background.Message;

public class Flight {

	public Flight(String reference) {
		this.reference = reference;
	}
	
	private String reference;
	
	public String getReference() {
		return this.reference;
	}
	
	public String getDateAsString() {
		return dateAsString;
	}

	public void setDateAsString(String dateAsString) {
		this.dateAsString = dateAsString;
	}

	private String dateAsString;
}
