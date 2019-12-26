package com.flyti.background.Message;

public class Message {
	private String flightReference;
	private FlightRequest flightRequest;
	
	public Message(FlightRequest flightRequest, String flightReference) {
		this.flightReference = flightReference;
		this.flightRequest = flightRequest;
	}
	
	public String getFlightReference() {
		return flightReference;
	}
	public void setFlightReference(String flightReference) {
		this.flightReference = flightReference;
	}
	public FlightRequest getFlightRequest() {
		return flightRequest;
	}
	public void setFlightRequest(FlightRequest flightRequest) {
		this.flightRequest = flightRequest;
	}
}
