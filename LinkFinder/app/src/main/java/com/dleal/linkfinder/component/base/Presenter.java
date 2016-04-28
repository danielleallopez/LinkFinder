package com.dleal.linkfinder.component.base;

import android.support.annotation.NonNull;

/**
 * Created by Daniel Leal on 09/03/16.
 */
public class Presenter<T extends Presenter.View> {

    private T view;

    public T getView() {
        return view;
    }

    public void setView(@NonNull T view) {
        this.view = view;
    }

    public void onCreate() {}

    public interface View {}
}
