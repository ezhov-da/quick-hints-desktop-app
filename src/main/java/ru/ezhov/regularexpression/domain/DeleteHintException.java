package ru.ezhov.regularexpression.domain;

public class DeleteHintException extends Exception {
    public DeleteHintException() {
    }

    public DeleteHintException(String message) {
        super(message);
    }

    public DeleteHintException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteHintException(Throwable cause) {
        super(cause);
    }

    public DeleteHintException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
