package com.dleal.linkfinder.repository.responses;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class ErrorBundle {
    private String message;

    public ErrorBundle(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
