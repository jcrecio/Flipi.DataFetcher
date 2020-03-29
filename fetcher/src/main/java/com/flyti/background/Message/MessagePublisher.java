package com.flyti.background.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.flyti.background.MessageBusConfiguration;
import com.flyti.background.tools.HttpClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MessagePublisher {
	private final KafkaProducer kafkaProducer;
	private final MessageBusConfiguration configuration;

	public MessagePublisher(final KafkaProducer<?, ?> kafkaProducer) {
		this.configuration = new MessageBusConfiguration();
		this.kafkaProducer = kafkaProducer;
	}

	public void run() throws IOException {
		final ArrayList<FlightRequest> flightRequests = getFlightRequests();

		for (int index = 0; index < flightRequests.size(); index++) {
			final FlightRequest flightRequest = flightRequests.get(index);
			final ArrayList<Flight> flights = findFlightsForSpeficicRequest(flightRequest);

			for (int j = 0; j < flights.size(); j++) {
				final Message message = new Message(flightRequest, flights.get(j).getReference());
				final ProducerRecord<String, Message> record = new ProducerRecord<String, Message>(configuration.TOPIC_NAME, message);

				try {
					final RecordMetadata metadata = (RecordMetadata) kafkaProducer.send(record).get();
					System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
					+ " with offset " + metadata.offset());
				} catch (final ExecutionException e) {
					System.out.println("Error in sending record");
					System.out.println(e);
				} catch (final InterruptedException e) {
					System.out.println("Error in sending record");
					System.out.println(e);
				}
			}
		}
	}

	private ArrayList<Flight> findFlightsForSpeficicRequest(final FlightRequest flightRequest) {
		// TODO: find the flights from an external provider, like sky scanner
		// This is a fake provider code
		final ArrayList<Flight> flights = new ArrayList<Flight>();
		for (int i = 0; i < new Random().nextInt(100); i++) {
			flights.add(new Flight(createRandomFlightNumber()));
		}

		return flights;
	}

	private ArrayList<FlightRequest> getFlightRequests() {
		final String rawData = (new HttpClient()).getJSON("http://requestcontainer_web:5000/requests", 5000);
		return new Gson().fromJson(rawData, new TypeToken<ArrayList<FlightRequest>>() {}.getType());
	}
	
	private String createRandomFlightNumber() {
		final Random random = new Random();
		final char[] word = new char[2];
        for(int j = 0; j < word.length; j++)
        {
            word[j] = (char)('a' + random.nextInt(26));
        }
	    final String companyPreffix = new String(word).toUpperCase();
	    final String flightDailyNumber = String.format("%04d", new Random().nextInt(10000));
	    
	    return companyPreffix + flightDailyNumber;
	}
}