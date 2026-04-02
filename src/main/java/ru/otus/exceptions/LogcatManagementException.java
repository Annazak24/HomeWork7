package ru.otus.exceptions;

public class LogcatManagementException extends RuntimeException {

    public LogcatManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}