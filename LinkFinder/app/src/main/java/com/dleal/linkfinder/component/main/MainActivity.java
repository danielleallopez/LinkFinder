package com.dleal.linkfinder.component.main;

import android.os.Bundle;

import com.dleal.linkfinder.R;
import com.dleal.linkfinder.component.base.BaseActivity;

/**
 * Created by Daniel Leal on 09/03/16.
 */
public class MainActivity extends BaseActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
