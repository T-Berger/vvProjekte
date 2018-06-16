package vv.praktikum.messaging.sender;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vv.praktikum.messaging.config.Configuration;
@Service
public class Alarm {
    @Autowired
    private RabbitTemplate alarm;
    @Autowired
    private TopicExchange appExchange;

    @Autowired
    public Alarm(final RabbitTemplate rabbitTemplate) {
        this.alarm = rabbitTemplate;
    }

    public Alarm(){}
//    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    @Scheduled(fixedRateString = "#{new Double((T(Math).random() * 300)).intValue()+1000}", initialDelayString = "#{new Double((T(Math).random() * 500)).intValue()}")
    public void alarm() {
//        String message = new VehicleMessage().toString();
        VehicleMessage currentVehicle = Controller.getInstance().randomVehicleMessage();
        // Generate new message
        String message = new VehicleMessage(currentVehicle.getId()).toString();
        System.out.println("ALARM!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        this.template.convertAndSend(unfiltered_DrivingQueue.getName(), message);
        this.alarm.convertAndSend(appExchange.getName(),"alarm",message);
        this.alarm.convertAndSend(Configuration.ALARM_QUEUE,message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
