package com.dleal.linkfinder.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class Website implements Serializable {
    private String url;
    private Collection<WebLink> links;

    public Website(String url, Collection<WebLink> links) {
        this.url = url;
        this.links = links;
    }

    public String getUrl() {
        return url;
    }

    public Collection<WebLink> getLinks() {
        return links;
    }
}
