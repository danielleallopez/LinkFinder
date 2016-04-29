package com.dleal.linkfinder.usecase;

import android.os.Handler;
import android.os.Looper;

import com.dleal.linkfinder.model.WebLink;
import com.dleal.linkfinder.model.mapper.WebLinkMapper;
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

    private WebLinkMapper mapper;

    @Inject
    public FindLinksInWebsite(WebLinkMapper mapper) {
        this.mapper = mapper;
    }

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
        Collection linkCollection = mapper.transform(links);
        new Handler(Looper.getMainLooper()).post(() -> callback.onSuccess(linkCollection));
    }

    public interface Callback {

        void onSuccess(Collection<WebLink> links);
    }
}
