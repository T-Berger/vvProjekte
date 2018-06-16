package vv.praktikum.messaging.receiver;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import vv.praktikum.messaging.config.Configuration;


@Service
//@RabbitListener(queues = Configuration.QUEUE_UNFILTERED_DRIVINGQUEUE)
public class InputFilter {
//    private final int instance;

//    public InputFilter(int i) {
//        this.instance = i;
//    }
@Autowired
private RabbitTemplate template;
    private final String[] keys = {"alarm.message",
            "normal.message"};

//    @Autowired
//    private RabbitTemplate template;

    @Autowired
    private TopicExchange appExchange;


    OutputFilter outputFilter;
    private int index;
    @RabbitListener(queues = Configuration.QUEUE_UNFILTERED_DRIVINGQUEUE)
    public void receive(String in) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();

        System.out.println("        [x] Received '" + in + "'");
        doFiltering(in);
//        String o = in;

        wait();
//        template.convertAndSend(appExchange.getName(), "normal", in);


//        outputFilter.send(in);
//        String key = keys[this.index];
//        template.convertAndSend(Configuration.EXCHANGE_NAME, key, in);
        watch.stop();
        System.out.println("        Received instance " +" [x] Done in "
                + watch.getTotalTimeSeconds() + "s");
    }

    private void doFiltering(String in) throws InterruptedException {
//        for (char ch : in.toCharArray()) {
//            if (ch == '.') {
//                Thread.sleep(1000);
//            }
//        }
//        outputFilter.send(in);
    }
}
