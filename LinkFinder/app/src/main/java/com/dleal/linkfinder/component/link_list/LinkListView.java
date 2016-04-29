package com.dleal.linkfinder.component.link_list;

import com.dleal.linkfinder.component.base.Presenter;
import com.dleal.linkfinder.model.WebLink;

import java.util.List;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public interface LinkListView extends Presenter.View {

    void setTitle(String title);

    void setNumLinks(int quantity);

    void loadListData(List<WebLink> links);
}
