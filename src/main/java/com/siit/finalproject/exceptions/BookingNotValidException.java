package com.siit.finalproject.exceptions;

public class BookingNotValidException extends RuntimeException{

    public BookingNotValidException(String message) {
        super(message);
    }
}
