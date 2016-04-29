package com.dleal.linkfinder.component.main;

import com.dleal.linkfinder.component.base.ErrorView;
import com.dleal.linkfinder.component.base.Presenter;
import com.dleal.linkfinder.component.base.ProgressView;

import java.io.Serializable;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public interface MainView extends Presenter.View, ProgressView, ErrorView {

    String getWebsiteURL();

    void showEmptyWebsiteURLError();

    void showWrongWebsiteURLFormatError();

    void showNoLinksError();

    void showNoInternetError();

    void showTimeoutError();

    void navigateToLinkList(Serializable data);

    void loadUrl(String url);

    void setAlarm(int time);

    void cancelAlarm();
}
