package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusBookingController {

    @GetMapping("/")
    public String home() {
        return "busBooking";
    }

    @GetMapping("/bookBusTicket")
    public String bookBusTicket(Model model) {
        // Additional logic if needed
        return "bookBusTicket";
    }
}

