package vv.praktikum.messaging.sender;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vv.praktikum.messaging.config.Configuration;

import java.util.Random;


@Service
public class TelematicUnit {
    @Autowired
    private RabbitTemplate template;
    private Alarm alarm = new Alarm();

    @Autowired
    private TopicExchange appExchange;

    public TelematicUnit(){}
    @Scheduled(fixedRate = 1000, initialDelayString = "#{new Double((T(Math).random() * 500)).intValue()}")
    public void send(){
//        String message = new VehicleMessage().toString();
        VehicleMessage currentVehicle = Controller.getInstance().randomVehicleMessage();
        // Generate new message
        String message = new VehicleMessage(currentVehicle.getId()).toString();
        //        this.template.convertAndSend(unfiltered_DrivingQueue.getName(), message);
        try {
            String filteredString = doFiltering(message);
            this.template.convertAndSend(appExchange.getName(),"normal",filteredString);
            System.out.println(" [x] Sent '" + message + "'");
        }catch (InterruptedException e) {
            e.printStackTrace();
            }

    }

    private String doFiltering(String message) throws InterruptedException {
        System.out.println("Filtering");
        return message;
        //        for (char ch : in.toCharArray()) {
//            if (ch == '.') {
//                Thread.sleep(1000);
//            }
//        }
//        outputFilter.send(in);
    }


}
////    @Autowired
////    private Queue queue;
//
////    @Scheduled(fixedDelay = 10000, initialDelay = 500)
//    public void send() {
//        String message =  new VehicleMessage().toString();
////        this.template.convertAndSend(unfiltered_DrivingQueue.getName(), message);
//        int random = new Random().nextInt(10);
//        if (random < 5 ){
////            this.alarm.convertAndSend(Configuration.QUEUE_FILTERED_DRIVINGDATA,"alarm.message",message.toString()+" Alarm!!!!!");
//            alarm.alarm();
//            System.out.println(" [x] Sent 'ALLLAAARRRRRMMMMM!!!!!!'");
//        }
//        this.template.convertAndSend(Configuration.QUEUE_UNFILTERED_DRIVINGQUEUE,message);
//
////        this.template.convertAndSend(Configuration.QUEUE_FILTERED_DRIVINGDATA,"alarm.message",message.toString()+" Alarm!!!!!");
//        System.out.println(" [x] Sent '" + message + "'");
//    }
//////    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
////    @Scheduled(fixedRate = 5000)
////    public void alarm() {
////        String message = new VehicleMessage().toString();
//////        this.template.convertAndSend(unfiltered_DrivingQueue.getName(), message);
////        this.alarm.convertAndSend(Configuration.QUEUE_FILTERED_DRIVINGDATA,"alarm.message",message);
////        System.out.println(" [x] Sent '" + message + "'");
////    }
//}
