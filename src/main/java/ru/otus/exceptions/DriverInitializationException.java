package ru.otus.exceptions;

public class DriverInitializationException extends RuntimeException {

    public DriverInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}