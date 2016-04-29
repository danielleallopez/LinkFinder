package com.dleal.linkfinder.component.main;

import com.dleal.linkfinder.component.base.Presenter;
import com.dleal.linkfinder.usecase.GetWebsiteContent;
import com.dleal.linkfinder.utils.ValidationUtils;

import javax.inject.Inject;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class MainPresenter extends Presenter<MainView> {

    private GetWebsiteContent getWebsiteContent;

    private MainView view;

    @Inject
    public MainPresenter(GetWebsiteContent getWebsiteContent) {
        this.getWebsiteContent = getWebsiteContent;
    }

    @Override public void onCreate() {
        super.onCreate();
        this.view = getView();
    }

    public void onDownloadLinksClick() {
        String url = view.getWebsiteURL();
        switch (ValidationUtils.checkURLValidity(url)) {
            case EMPTY:
                view.showEmptyWebsiteURLError();
                return;
            case NOT_VALID:
                view.showWrongWebsiteURLFormatError();
                return;
            case VALID:
                //TODO: Call usecase to download website
        }
    }
}
