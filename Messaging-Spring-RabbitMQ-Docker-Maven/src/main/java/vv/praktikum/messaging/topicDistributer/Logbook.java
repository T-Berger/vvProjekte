package vv.praktikum.messaging.topicDistributer;

import ch.qos.logback.core.util.StringCollectionUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessageOperations;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

@Service
public class Logbook {
    ConcurrentHashMap<String,String> logEntrys = new ConcurrentHashMap<String,String>();
    FileWriter fileWriter;
    {
        try {
            fileWriter = new FileWriter("Logbook",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    final BufferedWriter writer = new BufferedWriter(fileWriter);;



    List<String> s  = Collections.synchronizedList(new ArrayList<String>());
    //    private static Logbook instance;
//    public Logbook() {
//    }
//    public static Logbook getInstance () {
//        if (Logbook.instance == null) {
//            Logbook.instance = new Logbook ();
//        }
//        return Logbook.instance;
//    }

    @RabbitListener(queues = "#{filteredDrivingData.name}")
//    @RabbitListener(queues = Configuration.QUEUE_FILTERED_DRIVINGDATA)
    public void receive1(String in) throws InterruptedException {
        receive(in);
    }

//    @RabbitListener(queues = "#{filteredDrivingData.name}")
    private void receive(String in) throws
            InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("                    instance " + 1 + " [x] Received '"
                + in + "'");
        doWork(in);
        watch.stop();

        System.out.println("*******************************+" + s);
        System.out.println("                    instance " + 1 + " [x] Done in "
                + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        String id = StringUtils.substringBetween(in,"id=",", range");
        System.out.println(id);
        s.add(id);
//        Thread.sleep(5000);
        //        synchronized (this) {
            logEntrys.put(id, in);
        System.out.println(logEntrys);
//        }
        /**
         * Funktioniert
         * runiert aber den ram*/
//        synchronized (writer) {
//            try {
//                writer.write(in + "\n");
//                writer.write(id);
//                wait(10000);
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }

    public List<String> getS() {
        return s;
    }


}

