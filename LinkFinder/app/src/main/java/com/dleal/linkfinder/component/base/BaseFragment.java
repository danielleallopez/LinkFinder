package com.dleal.linkfinder.component.base;


import android.support.v4.app.Fragment;

import com.dleal.linkfinder.R;
import com.dleal.linkfinder.utils.ProgressDialogManager;

/**
 * Created by Daniel Leal on 09/03/16.
 */
public abstract class BaseFragment extends Fragment {

    protected void initializePresenter(Presenter<Presenter.View> presenter, Presenter.View view) {
        presenter.setView(view);
        presenter.onCreate();
    }

    public void showProgress(String message) {
        ProgressDialogManager.showProgressDialog(getContext(), message != null ? message : getString(R.string.loading));
    }

    public void hideProgress() {
        ProgressDialogManager.hideProgressDialog();
    }
}
