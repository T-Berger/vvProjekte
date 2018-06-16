package vv.praktikum.messaging.topicListener;

//import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataWarehouse {

//    ConcurrentHashMap<String,Integer> dataWarehouseEntrys = new ConcurrentHashMap<String,Integer>();
    Map<String,Integer> dataWarehouseEntrys = Collections.synchronizedMap(new HashMap<>());


    @RabbitListener(queues = "#{filteredDrivingData.name}")
    private void receive(String in) throws
            InterruptedException {

        computeDistance(in);

        System.out.println("ÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜ"+ dataWarehouseEntrys.toString());
    }
    private void computeDistance(String in) throws InterruptedException {
        String id = StringUtils.substringBetween(in,"id=",", range");
        String rangeString = StringUtils.substringBetween(in,"range=",", ");
        int range = Integer.parseInt(rangeString);
        if (dataWarehouseEntrys.containsKey(id)){
            System.out.println("ÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜ" + rangeString);
            int currentRange = dataWarehouseEntrys.get(id);
            dataWarehouseEntrys.put(id, range + currentRange);
        }else{
            dataWarehouseEntrys.put(id,range);
        }
    }

}
