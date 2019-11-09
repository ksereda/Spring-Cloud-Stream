package com.example.publisherservice.controller;

import com.example.publisherservice.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableBinding(Source.class)
public class PublisherController {

    @Autowired
    private Source source;

    @GetMapping(value = "/send")
    public void sendMessage() {
        Message message = new Message("Send message from publisher");
        source.output().send(MessageBuilder.withPayload(message).build());
    }

    @GetMapping(value = "/send/spam")
    public Message sendSpamMessage() {
        Message message = new Message("I'm sorry, but fuck you");
        source.output().send(MessageBuilder.withPayload(message).build());
        return message;
    }

}
