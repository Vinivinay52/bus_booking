package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class BusBookingService {

    public String bookBusTicket(String destination, int numberOfTickets) {
        // Your bus booking logic goes here
        return "Bus tickets booked for " + numberOfTickets + " passengers to " + destination;
    }
}

