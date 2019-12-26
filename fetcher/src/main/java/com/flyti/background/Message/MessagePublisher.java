package com.flyti.background.Message;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.flyti.background.Configuration;
import com.flyti.background.tools.HttpClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MessagePublisher {
	private final KafkaProducer kafkaProducer;
	private final Configuration configuration;

	public MessagePublisher(KafkaProducer<?, ?> kafkaProducer) {
		this.configuration = new Configuration();
		this.kafkaProducer = kafkaProducer;
	}

	public void run() throws IOException {

		ArrayList<FlightRequest> flightRequests = getFlightRequests();

		for (int index = 0; index < flightRequests.size(); index++) {
			FlightRequest flightRequest = flightRequests.get(index);
			ArrayList<Flight> flights = getFlightsForRequest(flightRequest);

			for (int j = 0; j < flights.size(); j++) {
				Message message = new Message(flightRequest, flights.get(j).getReference());

				ProducerRecord<String, Message> record = new ProducerRecord<String, Message>(configuration.TOPIC_NAME, message);

				try {
					RecordMetadata metadata = (RecordMetadata) kafkaProducer.send(record).get();
					System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
							+ " with offset " + metadata.offset());
				} catch (ExecutionException e) {
					System.out.println("Error in sending record");
					System.out.println(e);
				} catch (InterruptedException e) {
					System.out.println("Error in sending record");
					System.out.println(e);
				}
			}
		}
	}

	private ArrayList<Flight> getFlightsForRequest(FlightRequest flightRequest) {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		flights.add(new Flight("12345"));
		flights.add(new Flight("24680"));
		
		return flights;
	}

	private ArrayList<FlightRequest> getFlightRequests() {
		String data = (new HttpClient()).getJSON("http://localhost:5000/requests", 5000);
		return new Gson().fromJson(data, new TypeToken<ArrayList<FlightRequest>>() {
		}.getType());
	}
}