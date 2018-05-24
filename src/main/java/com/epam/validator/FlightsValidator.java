package com.epam.validator;

import com.epam.dao.impl.MyFlightDAO;
import com.epam.entity.Flight;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class FlightsValidator {
    public static ArrayList<String> validateNewFlights(Flight flight, Connection connection) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Flight> searchFlight = new MyFlightDAO().searchFlight(connection, flight.getNumber());
        System.out.println(searchFlight);
        if (flight.getPlanedID() == 0) {
            list.add("You may choose plane");
        }
        if (!searchFlight.isEmpty()) {
            list.add("A flight with this name already exists");
        }
        if (flight.getDeparture_time().after(flight.getLanding_time())) {
            list.add("Back to the Future?");
        }
        if (flight.getDeparture_airport_id() == flight.getLanding_airport_id()) {
            list.add("You can not fly to the same airport");
        }
        if (flight.getNumber().equals("")) {
            list.add("Flight number cannot be empty");
        }
        return list;
    }

    public static ArrayList<String> validateChangeFlights(Flight flight, Connection connection) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Flight> searchFlight = new MyFlightDAO().searchFlight(connection, flight.getNumber());
        System.out.println(searchFlight);
        if (flight.getDeparture_time().after(flight.getLanding_time())) {
            list.add("Back to the Future?");
        }
        if (flight.getDeparture_airport_id() == flight.getLanding_airport_id()) {
            list.add("You can not fly to the same airport");
        }
        if (flight.getNumber().equals("")) {
            list.add("Flight number cannot be empty");
        }
        return list;
    }

    public static String searchValidate(List<Flight> flights) {
        if (flights.isEmpty()) {
            return "This flight does not exist";
        }
        return "OK";
    }
}
