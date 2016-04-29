package com.dleal.linkfinder.repository.responses;

import org.jsoup.nodes.Document;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class WebResponse extends BaseResponse {
    private Document document;

    public WebResponse(Document document) {
        this.document = document;
    }

    public WebResponse() {}

    public static WebResponse withErrorBundle(ErrorBundle errorBundle){
        WebResponse webResponse = new WebResponse();
        webResponse.setErrorBundle(errorBundle);
        return webResponse;
    }

    public Document getDocument() {
        return document;
    }
}
