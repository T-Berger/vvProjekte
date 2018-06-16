package vv.praktikum.messaging.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vv.praktikum.messaging.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@org.springframework.stereotype.Controller
@Service
public class Controller {
    private Random randomGenerator;
    private static Controller instance;
    private Controller () {
        this.vehicles = add(new ArrayList<>());
        randomGenerator = new Random();
    }
    public static Controller getInstance () {
        if (Controller.instance == null) {
            Controller.instance = new Controller ();
        }
        return Controller.instance;
    }

    private List<VehicleMessage> vehicles;
    private static List<VehicleMessage> add (List vehicles){
        for (int i = 0; i < 10; i++){
            vehicles.add(new VehicleMessage());
        }
        return vehicles;
    }

    public List<VehicleMessage> getVehicles() {
        return vehicles;
    }

    public VehicleMessage randomVehicleMessage () {
        int index = randomGenerator.nextInt(vehicles.size());
        return vehicles.get(index);
    }

    //    public Alarm(final RabbitTemplate rabbitTemplate) {
//        this.alarm = rabbitTemplate;
//    }
}
//
//    private TelematicUnit tele = new TelematicUnit();
//    private Configuration conf;
//    private RabbitTemplate template;
//    private Alarm alarm = new Alarm(template);
//
//    @Scheduled(fixedDelay = 10000, initialDelay = 500)
//    public void controll() {
//        System.out.println("controller");
//        String message =  new VehicleMessage().toString();
////        this.template.convertAndSend(unfiltered_DrivingQueue.getName(), message);
//        int random = new Random().nextInt(10);
//        if (random < 5 ){
////            this.alarm.convertAndSend(Configuration.QUEUE_FILTERED_DRIVINGDATA,"alarm.message",message.toString()+" Alarm!!!!!");
////            alarm.alarm();
//
//
//            System.out.println(" [x] Sent 'ALLLAAARRRRRMMMMM!!!!!!'");
//        }
////        tele.send();
//          send();
////        this.template.convertAndSend(Configuration.QUEUE_FILTERED_DRIVINGDATA,"alarm.message",message.toString()+" Alarm!!!!!");
//        System.out.println(" [x] Sent '" + message + "'");
//    }
//
//
//
//    public void send() {
//        String message =  new VehicleMessage().toString();
////        this.template.convertAndSend(unfiltered_DrivingQueue.getName(), message);
////        int random = new Random().nextInt(10);
////        if (random < 5 ){
//////            this.alarm.convertAndSend(Configuration.QUEUE_FILTERED_DRIVINGDATA,"alarm.message",message.toString()+" Alarm!!!!!");
////            alarm.alarm();
////            System.out.println(" [x] Sent 'ALLLAAARRRRRMMMMM!!!!!!'");
////        }
////        this.template.convertAndSend(Configuration.QUEUE_UNFILTERED_DRIVINGQUEUE,message);
//
////        this.template.convertAndSend(Configuration.QUEUE_FILTERED_DRIVINGDATA,"alarm.message",message.toString()+" Alarm!!!!!");
//        System.out.println(" [x] Sent '" + message + "'");
//    }
//
////    public void alarm() {
////        String message = new VehicleMessage().toString();
//////        this.template.convertAndSend(unfiltered_DrivingQueue.getName(), message);
////        this.alarm.convertAndSend(appExchange.getName(),"alarm",message);
////        System.out.println(" [x] Sent '" + message + "'");
////    }
//
//
//}
