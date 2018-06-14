package vv.praktikum.messaging.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Queue;

public class TelematicUnit {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue drivingData;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        String message = "Hello World!";
        this.template.convertAndSend(dr.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
