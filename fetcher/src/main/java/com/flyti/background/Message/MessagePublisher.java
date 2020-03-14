package com.flyti.background.Message;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
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
			ArrayList<Flight> flights = findFlightsForSpeficicRequest(flightRequest);

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

	private ArrayList<Flight> findFlightsForSpeficicRequest(FlightRequest flightRequest) {
		// TODO: find the flights from an external provider, like sky-scanner
		ArrayList<Flight> flights = new ArrayList<Flight>();
		for (int i = 0; i < new Random().nextInt(100); i++) {
			flights.add(new Flight(createRandomFlightNumber()));
		}

		return flights;
	}

	private ArrayList<FlightRequest> getFlightRequests() {
		String data = (new HttpClient()).getJSON("http://localhost:5000/requests", 5000);
		return new Gson().fromJson(data, new TypeToken<ArrayList<FlightRequest>>() {
		}.getType());
	}
	
	private String createRandomFlightNumber() {
		
		Random random = new Random();
		char[] word = new char[2]; // words of length 3 through 10. (1 and 2 letter words are boring.)
        for(int j = 0; j < word.length; j++)
        {
            word[j] = (char)('a' + random.nextInt(26));
        }
	    String companyPreffix = new String(word).toUpperCase();
	    String flightDailyNumber = String.format("%04d", new Random().nextInt(10000));
	    
	    return companyPreffix + flightDailyNumber;
	}
}