package com.dleal.linkfinder.component.main;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.ViewGroup;

import com.dleal.linkfinder.R;
import com.dleal.linkfinder.component.base.BaseActivity;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Daniel Leal on 09/03/16.
 */
public class MainActivity extends BaseActivity implements MainView {

    @Bind(R.id.edit_login_user) TextInputEditText editLoginUser;
    @Bind(R.id.parent_main) ViewGroup parentMain;

    @Inject MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeDagger();
        initializePresenter(presenter, this);
    }

    @Override public String getWebsiteURL() {
        return editLoginUser.getText().toString();
    }

    @Override public void showEmptyWebsiteURLError() {
        editLoginUser.setError(getString(R.string.error_website_empty));
    }

    @Override public void showWrongWebsiteURLFormatError() {
        editLoginUser.setError(getString(R.string.error_website_wrong_format));
    }

    @OnClick(R.id.fab) public void onClick() {presenter.onDownloadLinksClick();}

    private void initializeDagger() {
        super.getApplicationComponent().inject(this);
    }
}
