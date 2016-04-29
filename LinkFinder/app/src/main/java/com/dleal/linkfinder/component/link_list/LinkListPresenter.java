package com.dleal.linkfinder.component.link_list;

import com.dleal.linkfinder.component.base.Presenter;
import com.dleal.linkfinder.model.WebLink;
import com.dleal.linkfinder.model.Website;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class LinkListPresenter extends Presenter<LinkListView> {

    private LinkListView view;

    private List<WebLink> linkList;

    @Inject
    public LinkListPresenter() {}

    @Override public void onCreate() {
        super.onCreate();
        this.view = getView();
    }

    public void onViewCreated(Serializable data) {
        Website website = (Website) data;
        linkList = new ArrayList<>(website.getLinks());
        view.setTitle(website.getUrl());
        view.setNumLinks(linkList.size());
        view.loadListData(linkList);
    }

    public void onItemSelected(int position) {
        WebLink link = linkList.get(position);
    }

}
