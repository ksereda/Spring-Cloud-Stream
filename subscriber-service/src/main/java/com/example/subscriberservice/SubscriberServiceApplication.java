package com.example.subscriberservice;

import com.example.subscriberservice.model.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class SubscriberServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriberServiceApplication.class, args);
	}

	@StreamListener(target = Sink.INPUT)
	public void handleMessage(Message message) throws Exception {
		if(message.getMessage().contains("fuck")) {
			throw new Exception("Error spam!!!");
		}
		System.out.println("Spam message: " + message);
	}

}
