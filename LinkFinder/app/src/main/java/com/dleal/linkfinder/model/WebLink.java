package com.dleal.linkfinder.model;

import java.io.Serializable;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class WebLink implements Serializable {
    private String uriString;

    public String getUriString() {
        return uriString;
    }

    public void setUriString(String uriString) {
        this.uriString = uriString;
    }
}
