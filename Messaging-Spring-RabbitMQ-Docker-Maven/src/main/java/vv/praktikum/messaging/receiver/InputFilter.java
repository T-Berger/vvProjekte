package vv.praktikum.messaging.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "unfiltered_DrivingQueue")
public class InputFilter {
//    private final int instance;

//    public InputFilter(int i) {
//        this.instance = i;
//    }
    OutputFilter outputFilter;

    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();

        System.out.println("[x] Received '" + in + "'");
        doFiltering(in);
        outputFilter.send(in);
        watch.stop();
        System.out.println("Received instance " +" [x] Done in "
                + watch.getTotalTimeSeconds() + "s");
    }

    private void doFiltering(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}
