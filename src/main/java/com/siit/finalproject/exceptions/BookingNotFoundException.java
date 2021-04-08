package com.siit.finalproject.exceptions;

public class BookingNotFoundException extends RuntimeException{

    public BookingNotFoundException(String message) {
        super(message);
    }
}
