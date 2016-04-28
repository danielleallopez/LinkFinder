package com.dleal.linkfinder.di.components;

import com.dleal.linkfinder.component.link_list.LinkListFragment;
import com.dleal.linkfinder.component.base.BaseActivity;
import com.dleal.linkfinder.component.main.MainActivity;
import com.dleal.linkfinder.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Daniel Leal on 09/03/16.
 */
@Singleton @Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

    void inject(LinkListFragment linkListFragment);
}
