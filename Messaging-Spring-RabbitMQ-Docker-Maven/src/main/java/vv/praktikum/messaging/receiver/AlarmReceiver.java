package vv.praktikum.messaging.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import vv.praktikum.messaging.config.Configuration;
@Service
public class AlarmReceiver {
    @Autowired
    private RabbitTemplate template;

    @RabbitListener(queues = Configuration.ALARM_QUEUE)
    public void receive(String in) throws InterruptedException {

        System.out.println("        [x] Alarm '" + in + "'");

    }

}
