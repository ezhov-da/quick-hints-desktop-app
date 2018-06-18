package ru.ezhov.regularexpression.domain;

public class AddHintException extends Throwable {
    public AddHintException() {
    }

    public AddHintException(String message) {
        super(message);
    }

    public AddHintException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddHintException(Throwable cause) {
        super(cause);
    }

    public AddHintException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
