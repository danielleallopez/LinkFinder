package com.dleal.linkfinder.component.main;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.dleal.linkfinder.utils.Constants.JAVASCRIPT_GET_HTML;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class CustomWebClient extends WebViewClient {
    private boolean isRedirected;
    private boolean isFinished;

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        isRedirected = false;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        isRedirected = true;
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (!isRedirected && !isFinished) {
            view.loadUrl(JAVASCRIPT_GET_HTML);
            isFinished = true;
        }
    }
}
