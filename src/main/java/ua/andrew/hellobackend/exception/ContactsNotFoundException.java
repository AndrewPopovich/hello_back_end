package ua.andrew.hellobackend.exception;

import java.io.Serializable;

public class ContactsNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

    private long timeStamp;

    public ContactsNotFoundException() {
        this("Contacts not found!");
    }

    public ContactsNotFoundException(String message) {
        timeStamp = System.currentTimeMillis();
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String getMessage() {
        return message;
    }
}