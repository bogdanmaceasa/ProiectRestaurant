package com.siit.finalproject.exceptions;

public class DuplicateRestaurantEntryException extends RuntimeException{

    public DuplicateRestaurantEntryException(String message) {
        super(message);
    }
}
