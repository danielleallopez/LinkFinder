package com.dleal.linkfinder.component.main;

import com.dleal.linkfinder.component.base.Presenter;
import com.dleal.linkfinder.repository.responses.ErrorBundle;
import com.dleal.linkfinder.usecase.FindLinksInWebsite;
import com.dleal.linkfinder.usecase.GetWebsiteContent;
import com.dleal.linkfinder.utils.ConnectivityUtils;
import com.dleal.linkfinder.utils.ValidationUtils;

import org.jsoup.nodes.Document;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class MainPresenter extends Presenter<MainView> {

    private ConnectivityUtils connectivityUtils;
    private GetWebsiteContent getWebsiteContent;
    private FindLinksInWebsite findLinksInWebsite;

    private MainView view;

    @Inject
    public MainPresenter(ConnectivityUtils connectivityUtils, GetWebsiteContent getWebsiteContent, FindLinksInWebsite findLinksInWebsite) {
        this.connectivityUtils = connectivityUtils;
        this.getWebsiteContent = getWebsiteContent;
        this.findLinksInWebsite = findLinksInWebsite;
    }

    @Override public void onCreate() {
        super.onCreate();
        this.view = getView();
    }

    public void onDownloadLinksClick() {
        startLinkSearch();
    }

    public void onRetryConnectionClick() {
        startLinkSearch();
    }

    private GetWebsiteContent.Callback callback = new GetWebsiteContent.Callback() {
        @Override public void onSuccess(Document document) {
            findLinksInWebsite.getLinks(document, linksCallback);
        }

        @Override public void onError(ErrorBundle errorBundle) {
            view.hideProgress();
            view.showError(errorBundle.getMessage());
        }
    };

    private FindLinksInWebsite.Callback linksCallback = new FindLinksInWebsite.Callback() {
        @Override public void onSuccess(Collection<String> links) {
            view.hideProgress();
            if (links.size() > 0)
                view.navigateToLinkList((Serializable) links);
            else
                view.showNoLinksError();
        }
    };

    private void startLinkSearch() {
        String url = view.getWebsiteURL();
        switch (ValidationUtils.checkURLValidity(url)) {
            case EMPTY:
                view.showEmptyWebsiteURLError();
                return;
            case NOT_VALID:

                view.showWrongWebsiteURLFormatError();
                return;
            case VALID:
                if (!connectivityUtils.isInternetAvailable()) {
                    view.showNoInternetError();
                    return;
                }
                view.showProgress(null);
                try {
                    getWebsiteContent.getWebsiteData(new URL(url), callback);
                } catch (MalformedURLException e) {
                    view.showWrongWebsiteURLFormatError();
                }
        }
    }
}
