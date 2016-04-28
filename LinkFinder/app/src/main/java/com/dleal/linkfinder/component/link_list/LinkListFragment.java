package com.dleal.linkfinder.component.link_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dleal.linkfinder.R;
import com.dleal.linkfinder.component.base.BaseFragment;

/**
 * Created by Daniel Leal on 09/03/16.
 */
public class LinkListFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);
        return rootView;
    }
}
