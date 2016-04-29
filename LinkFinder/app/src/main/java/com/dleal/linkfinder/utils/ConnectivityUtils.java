package com.dleal.linkfinder.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class ConnectivityUtils {

    private Context applicationContext;

    @Inject
    public ConnectivityUtils(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
