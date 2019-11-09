# Spring-Cloud-Stream
Spring Cloud Stream + RabbitMQ + Dead Letter Queue

Introduction:

Spring Cloud Stream is part of the Spring Cloud project group.
It is based on Spring Boot and uses Spring Integration to communicate with message brokers.
It provides easy integration with various message brokers with minimal configuration.
It helps in messaging between two applications or microservices. It integrates seamlessly with RabbitMQ, Kafka, Amazon Kinesis, PubSub, RocketMQ, etc.

We will try RabbitMQ as an example.
There is nothing complicated in using the same Kafka - all settings will be identical.

___________________________________________________________________________________________________

The main features Spring Cloud Stream

What is the allure and simplicity of Spring Cloud Stream?
There are such concepts as:
- Sink - represents the basic structure for creating the necessary bindings (queue, topic, etc.) - on the receiving side (subscriber),
- Source is the opposite of Sink. It is used by the publisher (publisher).
Source and Sink are the connecting interfaces provided by Spring Cloud Stream.
We will use an object of the Source class to publish the Message object to RabbitMQ.
- Processor - can be used for an application that has both an inbound and outbound channel.

I’ll explain a little bit that it was even clearer.

Usually, without using Spring Cloud Stream, we create an interface

  interface MyTestChannel {

      @Output ("outputChannel")
      MessageChannel greeting ();
  }

and indicate the channel where messages will be published.

So, when using Spring Cloud Stream, we don’t need to do this and everything else; instead, we use the Sink / Source magic classes.
Look, if you fall inside the Source interface in the publisher, then we will see a familiar picture

  public interface Source {
      String OUTPUT = "output";

      @Output ("output")
      MessageChannel output ();
  }

And similar to Sink

  public interface Sink {
      String INPUT = "input";

      @Input ("input")
      SubscribableChannel input ();
  }
___________________________________________________________________________________________________


Launch RabbitMQ through docker

  docker run -d --hostname my-test-rabbit --name test-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management

Web interface (console)

  localhost:15672
  
Launch all three services.

Go to publisher-service

  localhost:8020/send

and go to subscriber-service in console and you will see this message from publisher-service

___________________________________________________________________________________________________

What about Error ?

Go to

  localhost:8020/send/spam

Go to RabbitMQ UI and you will see 2 queues (normal and DLQ for error messages)

And then go to filter-service in concole and you will see this spam message.
The filter-service simply displays a spam message in the console. You can also make it execute any other logic, for example, writing to the database.

Thanks.
