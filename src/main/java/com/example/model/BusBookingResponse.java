package com.example.model;

public class BusBookingResponse {

    private String confirmationMessage;

    public BusBookingResponse() {
    }

    public BusBookingResponse(String confirmationMessage) {
        this.confirmationMessage = confirmationMessage;
    }

    public String getConfirmationMessage() {
        return confirmationMessage;
    }

    public void setConfirmationMessage(String confirmationMessage) {
        this.confirmationMessage = confirmationMessage;
    }
}

