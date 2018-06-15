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
        String message =  new VehicleMessage().toString();
//        this.template.convertAndSend(unfiltered_DrivingQueue.getName(), message);
        this.template.convertAndSend(message);
        System.out.println(" [x] Sent '" + message + "'");
    }
//    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void alarm() {
        String message = new VehicleMessage().toString();
//        this.template.convertAndSend(unfiltered_DrivingQueue.getName(), message);
        this.template.convertAndSend(message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
