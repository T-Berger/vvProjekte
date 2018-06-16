package vv.praktikum.messaging.sender;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.sql.Time;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;


@Component

@Validated
//@EnableConfigurationProperties(Message.class)
public class VehicleMessage {
    private UUID id;
    private int range;
    private Time time;
    private String gpsCoordinates;

    VehicleMessage(){
        this.gpsCoordinates =randomGPS();
        this.id = UUID.randomUUID();
        this.range = (int) (Math.random() * 100 +  Math.random() * 30 + Math.random() * 7);
        this.time = randomTime();
    }

    public VehicleMessage(UUID id) {
        this.id = id;
        this.gpsCoordinates =randomGPS();
        this.range = (int) (Math.random() * 100 +  Math.random() * 30 + Math.random() * 7);
        this.time = randomTime();
    }

    private Time randomTime(){
        Random random = new Random();
        int millisInDay = 24 * 60 * 60 * 1000;
        return new Time((long) random.nextInt(millisInDay));
    }

    private String randomGPS(){
        double minLat = -90.00;
        double maxLat = 90.00;
        double latitude = minLat + Math.random() * ((maxLat - minLat) + 1);
        double minLon = 0.00;
        double maxLon = 180.00;
        double longitude = minLon + (Math.random() * ((maxLon - minLon) + 1));
        DecimalFormat df = new DecimalFormat("#.#####");
        return ("latitude:longitude --> " + df.format(latitude) + "," + df.format(longitude));
    }

    @Override
    public String toString() {
        return "VehicleMessage{" +
                "id=" + id +
                ", range=" + range +
                ", time=" + time +
                ", gpsCoordinates='" + gpsCoordinates + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }
}
