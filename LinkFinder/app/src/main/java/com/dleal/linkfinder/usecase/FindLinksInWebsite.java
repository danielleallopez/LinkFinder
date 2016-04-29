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
import javax.inject.Singleton;

import static com.dleal.linkfinder.utils.Constants.CALLBACK_TIMEOUT;
import static com.dleal.linkfinder.utils.StringValidity.VALID;

/**
 * Created by Daniel Leal on 29/04/16.
 */
@Singleton
public class FindLinksInWebsite {

    private WebLinkMapper mapper;
    private String html;
    private Callback dataAvailableCallback;
    private Collection<WebLink> linkCollection;

    private Handler callbackHandler = new Handler(Looper.getMainLooper());

    @Inject
    public FindLinksInWebsite(WebLinkMapper mapper) {
        this.mapper = mapper;
    }

    private final String LINK_A = "a";
    private final String HREF = "href";

    public void getLinks(String html, final Callback callback) {
        this.html = html;
        this.dataAvailableCallback = callback;

        //Sometimes we get a first response which is not valid, because website has not loaded properly.
        //If a new request is received before one second, we assume it is a redirection, and abort previous response
        callbackHandler.removeCallbacks(postCallback);
        new Thread(searchLinks).start();
    }

    public void cancel(){
        callbackHandler.removeCallbacks(postCallback);
    }

    private void searchLinksInDocument(Document document) {
        Elements resultLinks = document.select(LINK_A);
        Set<String> links = new HashSet<>();
        for (Element link : resultLinks) {
            String href = link.attr(HREF);
            if (ValidationUtils.checkURLValidity(href) == VALID)
                links.add(href);
        }
        linkCollection = mapper.transform(links);

        //We set a delay if we get no links, because we assume it is due to a slow web loading
        callbackHandler.postDelayed(postCallback, linkCollection.size() > 0 ? 0 : CALLBACK_TIMEOUT);
    }

    private Runnable searchLinks = new Runnable() {
        @Override public void run() {
            Document document = Jsoup.parse(html);
            searchLinksInDocument(document);
        }
    };

    private Runnable postCallback = new Runnable() {
        @Override public void run() {
            dataAvailableCallback.onSuccess(linkCollection);
        }
    };

    public interface Callback {

        void onSuccess(Collection<WebLink> links);
    }
}
