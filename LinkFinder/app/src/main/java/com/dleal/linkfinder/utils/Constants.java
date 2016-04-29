package com.dleal.linkfinder.utils;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class Constants {
    public static final int SECOND = 1000;
    public static final int CONNECTION_TIMEOUT = 5 * SECOND;

    public static final String JAVASCRIPT_INTERFACE = "html_out";
    public static final String JAVASCRIPT_GET_HTML = "javascript:" + JAVASCRIPT_INTERFACE +
            ".processHTML(document.documentElement.outerHTML);";
}
