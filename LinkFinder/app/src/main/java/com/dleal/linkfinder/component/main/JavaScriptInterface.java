package com.dleal.linkfinder.component.main;

import android.webkit.JavascriptInterface;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class JavaScriptInterface {

    private Callback callback;

    public JavaScriptInterface(Callback callback) {
        this.callback = callback;
    }

    @JavascriptInterface
    @SuppressWarnings("unused")
    public void processHTML(String html) {
        if (callback != null)
            callback.onHTMLLoaded(html);
    }

    interface Callback {
        void onHTMLLoaded(String html);
    }
}
