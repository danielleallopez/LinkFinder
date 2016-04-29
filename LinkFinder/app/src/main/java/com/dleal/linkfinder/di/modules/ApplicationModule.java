package com.dleal.linkfinder.di.modules;

import com.dleal.linkfinder.component.LinkFinderApplication;
import com.dleal.linkfinder.repository.DataRepository;
import com.dleal.linkfinder.utils.ConnectivityUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Daniel Leal on 09/03/16.
 */
@Module
public class ApplicationModule {

    private final LinkFinderApplication application;

    public ApplicationModule(LinkFinderApplication application) {
        this.application = application;
    }

    @Provides @Singleton public DataRepository dataRepository(){
        return new DataRepository();
    }

    @Provides @Singleton public ConnectivityUtils connectivityUtils(){
        return new ConnectivityUtils(application);
    }
}
