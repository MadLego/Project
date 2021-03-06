package com.epam.dto;

import java.sql.Timestamp;

public class FlightDTO {
   private String name;
   private String plane;
   private String departure_airport;
   private String landing_airport;
   private String departure_time_out;
   private String landing_time_out;
   private Timestamp departure_time;
   private Timestamp landing_time;

    public String getDeparture_time_out() {
        return departure_time_out;
    }

    public void setDeparture_time_out(String departure_time_out) {
        this.departure_time_out = departure_time_out;
    }

    public String getLanding_time_out() {
        return landing_time_out;
    }

    public void setLanding_time_out(String landing_time_out) {
        this.landing_time_out = landing_time_out;
    }



    @Override
    public String toString() {
        return "FlightDTO{" +
                "name='" + name + '\'' +
                ", plane='" + plane + '\'' +
                ", departure_airport='" + departure_airport + '\'' +
                ", landing_airport='" + landing_airport + '\'' +
                ", departure_time=" + departure_time +
                ", landing_time=" + landing_time +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public String getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(String departure_airport) {
        this.departure_airport = departure_airport;
    }

    public String getLanding_airport() {
        return landing_airport;
    }

    public void setLanding_airport(String landing_airport) {
        this.landing_airport = landing_airport;
    }

    public Timestamp getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Timestamp departure_time) {
        this.departure_time = departure_time;
    }

    public Timestamp getLanding_time() {
        return landing_time;
    }

    public void setLanding_time(Timestamp landing_time) {
        this.landing_time = landing_time;
    }

}
