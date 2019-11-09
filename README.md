# Spring-Cloud-Stream
Spring Cloud Stream + RabbitMQ + Dead Letter Queue

Launch RabbitMQ through docker
docker run -d --hostname my-test-rabbit --name test-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management

Launch all three services.

Go to 

localhost:8020/send

and go to subscriber-service in console and you will see this message from publisher-service


What about Error ?
Go to

localhost:8020/send/spam

Go to RabbitMQ UI and you will see 2 queues (normal and DLQ for error messages)

And then go to filter-service in concole and you will see this spam message
