package com.dleal.linkfinder.usecase;

import android.os.Handler;
import android.os.Looper;

import com.dleal.linkfinder.utils.ValidationUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import static com.dleal.linkfinder.utils.StringValidity.VALID;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class FindLinksInWebsite {

    @Inject public FindLinksInWebsite() {}

    private final String LINK_A = "a";
    private final String HREF = "href";

    public void getLinks(String html, final Callback callback) {
        new Thread(() -> {
            Document document = Jsoup.parse(html);
            searchLinksInDocument(document, callback);
        }).start();
    }

    private void searchLinksInDocument(Document document, final Callback callback) {
        Elements resultLinks = document.select(LINK_A);
        Set<String> links = new HashSet<>();
        for (Element link : resultLinks) {
            String href = link.attr(HREF);
            if (ValidationUtils.checkURLValidity(href) == VALID)
                links.add(href);
        }
        new Handler(Looper.getMainLooper()).post(() -> callback.onSuccess(links));
    }

    public interface Callback {

        void onSuccess(Collection<String> links);
    }
}
