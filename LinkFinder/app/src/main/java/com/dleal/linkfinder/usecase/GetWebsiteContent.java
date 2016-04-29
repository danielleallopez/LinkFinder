package com.dleal.linkfinder.usecase;

import android.os.Handler;
import android.os.Looper;

import com.dleal.linkfinder.repository.DataRepository;
import com.dleal.linkfinder.repository.responses.ErrorBundle;
import com.dleal.linkfinder.repository.responses.WebResponse;

import org.jsoup.nodes.Document;

import java.net.URL;

import javax.inject.Inject;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class GetWebsiteContent {

    private final DataRepository repository;

    @Inject public GetWebsiteContent(DataRepository repository) {
        this.repository = repository;
    }

    public void getWebsiteData(URL url, final Callback callback) {
        new Thread(() -> {
            requestWebsiteData(url, callback);
        }).start();
    }

    private void requestWebsiteData(URL url, final Callback callback) {
        WebResponse webResponse = repository.getWebsiteContent(url);
        ErrorBundle errorBundle = webResponse.getErrorBundle();
        if (errorBundle == null) {
            new Handler(Looper.getMainLooper()).post(() -> callback.onSuccess(webResponse.getDocument()));
        } else
            new Handler(Looper.getMainLooper()).post(() -> callback.onError(errorBundle));
    }

    public interface Callback {

        void onSuccess(Document document);

        void onError(ErrorBundle errorBundle);
    }
}
