package com.dleal.linkfinder.component.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.dleal.linkfinder.R;
import com.dleal.linkfinder.component.LinkFinderApplication;
import com.dleal.linkfinder.di.components.ApplicationComponent;
import com.dleal.linkfinder.utils.ProgressDialogManager;

/**
 * Created by Daniel Leal on 09/03/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link com.dleal.linkfinder.di.components.ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((LinkFinderApplication) getApplication()).getApplicationComponent();
    }

    protected void showFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected void initializePresenter(Presenter presenter, Presenter.View view) {
        presenter.setView(view);
        presenter.onCreate();
    }

    public void showProgress(String message) {
        ProgressDialogManager.showProgressDialog(this, message != null ? message : getString(R.string.loading));
    }

    public void hideProgress() {
        ProgressDialogManager.hideProgressDialog();
    }
}
