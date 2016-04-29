package com.dleal.linkfinder.component.main;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dleal.linkfinder.R;
import com.dleal.linkfinder.component.base.BaseActivity;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dleal.linkfinder.utils.Constants.JAVASCRIPT_INTERFACE;

/**
 * Created by Daniel Leal on 09/03/16.
 */
public class MainActivity extends BaseActivity implements MainView {

    @Bind(R.id.edit_website_url) TextInputEditText editLoginUser;
    @Bind(R.id.input_layout_website) TextInputLayout inputLayoutWebsite;
    @Bind(R.id.parent_main) ViewGroup parentMain;
    @Bind(R.id.webview) WebView browser;

    @Inject MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initBrowser();

        initializeDagger();
        initializePresenter(presenter, this);
    }

    @Override public String getWebsiteURL() {
        return editLoginUser.getText().toString();
    }

    @Override public void showEmptyWebsiteURLError() {
        inputLayoutWebsite.setError(getString(R.string.error_website_empty));
    }

    @Override public void showWrongWebsiteURLFormatError() {
        inputLayoutWebsite.setError(getString(R.string.error_website_wrong_format));
    }

    @Override public void showNoLinksError() {
        Snackbar.make(parentMain, R.string.error_main_no_links_found, Snackbar.LENGTH_SHORT).show();
    }

    @Override public void showNoInternetError() {
        Snackbar.make(parentMain, R.string.error_no_internet, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, v -> {
                    presenter.onRetryConnectionClick();
                })
                .show();
    }

    @Override public void navigateToLinkList(Serializable data) {
        //TODO
    }

    @Override public void loadUrl(String url) {
        initWebClient();
        browser.loadUrl(url);
    }

    @Override public void showError(String message) {
        Snackbar.make(parentMain, message, Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_main_search) public void onClick() {presenter.onDownloadLinksClick();}

    private void initializeDagger() {
        super.getApplicationComponent().inject(this);
    }

    private void initBrowser() {
        browser.getSettings().setJavaScriptEnabled(true);
        browser.addJavascriptInterface(new JavaScriptInterface(html -> presenter.onHTMLAvailable(html)), JAVASCRIPT_INTERFACE);
    }

    private void initWebClient(){
        WebViewClient webViewClient = new CustomWebClient();
        browser.setWebViewClient(webViewClient);
    }
}
