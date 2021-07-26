package com.example.forum.exception;

/**
 * Throws this exception for the Record Not Found scenarios.
 */
public class RecordNotFoundException extends Exception {
    public RecordNotFoundException(String msg) {
        super(msg);
    }
}
