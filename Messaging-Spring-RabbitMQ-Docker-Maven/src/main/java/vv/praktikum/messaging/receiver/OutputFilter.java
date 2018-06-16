package vv.praktikum.messaging.receiver;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vv.praktikum.messaging.config.Configuration;
@Deprecated
@Service
public class OutputFilter {
    @Autowired
    private RabbitTemplate template;

//    @Autowired
//    private Queue filteredDrivingData;

//    @Autowired
//    private TopicExchange topic;

    @Autowired
    private TopicExchange appExchange;

    public OutputFilter(){
//        this.message = message;
    }

//    private String message;

    private int index;

    private int count;

    private final String[] keys = {"alarm.message",
            "normal.message"};

//    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(String message) {
        if (++this.index == keys.length) {
            this.index = 0;
        }
        String key = keys[this.index];
//        template.convertAndSend(topic.getName(), key, message);
        template.convertAndSend(appExchange.getName(), key, message);
        System.out.println("            [x] SentFiltered '" + message + "'");
    }
}
