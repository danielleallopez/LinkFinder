package com.dleal.linkfinder.utils;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class Constants {
    public static final int SECOND = 1000;
    public static final long NANOSECONDS = 1000000000;
    public static final int CONNECTION_TIMEOUT = 20 * SECOND;
    public static final long CONNECTION_TIMEOUT_NS = 20 * NANOSECONDS;
    public static final int CALLBACK_TIMEOUT = 4*SECOND;

    public static final String JAVASCRIPT_INTERFACE = "html_out";
    public static final String JAVASCRIPT_GET_HTML = "javascript:" + JAVASCRIPT_INTERFACE +
            ".processHTML(document.documentElement.outerHTML);";

    public static final String HTTPS = "https";

    public static final String BUNDLE_DATA = "data";

}
