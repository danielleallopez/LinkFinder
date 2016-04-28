package com.dleal.linkfinder.component;

import android.app.Application;

import com.dleal.linkfinder.di.components.ApplicationComponent;
import com.dleal.linkfinder.di.components.DaggerApplicationComponent;
import com.dleal.linkfinder.di.modules.ApplicationModule;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class LinkFinderApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
