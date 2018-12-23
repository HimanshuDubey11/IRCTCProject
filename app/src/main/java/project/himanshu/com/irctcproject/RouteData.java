package project.himanshu.com.irctcproject;

import java.io.Serializable;

public class RouteData implements Serializable {

    String number;
    String scharr;
    String schdep;
    String day;
    String distance;
    String name;
    String code;

    public RouteData(String number, String scharr, String schdep, String day, String distance, String name, String code) {
        this.number = number;
        this.scharr = scharr;
        this.schdep = schdep;
        this.day = day;
        this.distance = distance;
        this.name = name;
        this.code = code;
    }
}
