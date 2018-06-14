package vv.praktikum.messaging.sender;

import java.text.DecimalFormat;

public class VehicleMessage {
    int  id;
    int range;
    int time;
    String gpsCoordniats;



    public String randomGPS (){
        double minLat = -90.00;
        double maxLat = 90.00;
        double latitude = minLat + (double)(Math.random() * ((maxLat - minLat) + 1));
        double minLon = 0.00;
        double maxLon = 180.00;
        double longitude = minLon + (double)(Math.random() * ((maxLon - minLon) + 1));
        DecimalFormat df = new DecimalFormat("#.#####");
        return ("latitude:longitude --> " + df.format(latitude) + "," + df.format(longitude));
    }
}
