package com.dleal.linkfinder.utils;

import java.net.MalformedURLException;
import java.net.URL;

import static com.dleal.linkfinder.utils.StringValidity.EMPTY;
import static com.dleal.linkfinder.utils.StringValidity.NOT_VALID;
import static com.dleal.linkfinder.utils.StringValidity.VALID;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class ValidationUtils {

    public static boolean isStringEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static StringValidity checkURLValidity(String url) {
        StringValidity validity = isStringEmpty(url) ? EMPTY : VALID;
        if (validity != VALID)
            return validity;
        else {
            try {
                new URL(url);
                return VALID;
            } catch (MalformedURLException e) {
                return NOT_VALID;
            }
        }
    }
}
