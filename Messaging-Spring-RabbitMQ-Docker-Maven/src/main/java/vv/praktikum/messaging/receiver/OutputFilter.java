package vv.praktikum.messaging.receiver;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class OutputFilter {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private TopicExchange topic;

    OutputFilter(String message){
//        this.message = message;
    }

//    private String message;

    private int index;

    private int count;

    private final String[] keys = {"alarm.message",
            "normal.message"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(String message) {
        StringBuilder builder = new StringBuilder("Hello to ");
        if (++this.index == keys.length) {
            this.index = 0;
        }
        String key = keys[this.index];
;
        template.convertAndSend(topic.getName(), key, message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
