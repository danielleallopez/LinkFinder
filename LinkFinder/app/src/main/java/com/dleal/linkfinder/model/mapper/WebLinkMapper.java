package com.dleal.linkfinder.model.mapper;

import com.dleal.linkfinder.model.WebLink;
import com.dleal.linkfinder.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class WebLinkMapper {

    public WebLink transform(String url){
        if (ValidationUtils.isStringEmpty(url)) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        WebLink webLink = new WebLink();
        webLink.setUriString(url);
        return webLink;
    }

    public Collection<WebLink> transform(Collection<String> urls){
        List<WebLink> webLinks;

        if (urls != null && !urls.isEmpty()) {
            webLinks = new ArrayList<>();
            for (String url : urls) {
                webLinks.add(transform(url));
            }
        } else {
            webLinks = Collections.emptyList();
        }

        return webLinks;
    }
}
