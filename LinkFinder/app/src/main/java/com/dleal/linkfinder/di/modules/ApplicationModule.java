package com.dleal.linkfinder.di.modules;

import com.dleal.linkfinder.component.LinkFinderApplication;

import dagger.Module;

/**
 * Created by Daniel Leal on 09/03/16.
 */
@Module
public class ApplicationModule {

    private final LinkFinderApplication application;

    public ApplicationModule(LinkFinderApplication application) {
        this.application = application;
    }
}
