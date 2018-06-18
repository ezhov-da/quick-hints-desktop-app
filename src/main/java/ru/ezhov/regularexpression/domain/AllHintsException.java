package ru.ezhov.regularexpression.domain;

public class AllHintsException extends Exception {
    public AllHintsException() {
    }

    public AllHintsException(String message) {
        super(message);
    }

    public AllHintsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AllHintsException(Throwable cause) {
        super(cause);
    }

    public AllHintsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
