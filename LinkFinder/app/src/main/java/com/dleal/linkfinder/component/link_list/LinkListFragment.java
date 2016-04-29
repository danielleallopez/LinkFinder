package com.dleal.linkfinder.component.link_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dleal.linkfinder.R;
import com.dleal.linkfinder.component.LinkFinderApplication;
import com.dleal.linkfinder.component.base.BaseFragment;
import com.dleal.linkfinder.component.base.listeners.OnRecyclerItemListener;
import com.dleal.linkfinder.model.WebLink;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.dleal.linkfinder.utils.Constants.BUNDLE_DATA;

/**
 * Created by Daniel Leal on 09/03/16.
 */
public class LinkListFragment extends BaseFragment implements LinkListView {

    @Bind(R.id.txt_fragment_link_list_title) TextView txtTitle;
    @Bind(R.id.txt_fragment_link_list_quantity) TextView txtQuantity;
    @Bind(R.id.list_links) RecyclerView listLinks;

    @Inject LinkListPresenter presenter;

    private OnRecyclerItemListener listener = new OnRecyclerItemListener() {
        @Override public void onItemSelected(int position) {
            presenter.onItemSelected(position);
        }
    };

    public static LinkListFragment getInstance(Serializable data) {
        LinkListFragment fragment = new LinkListFragment();
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((LinkFinderApplication) getActivity().getApplication()).getApplicationComponent().inject(this);
        initializePresenter(presenter, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_link_list, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null)
            presenter.onViewCreated(args.getSerializable(BUNDLE_DATA));
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override public void setTitle(String title) {
        txtTitle.setText(title);
    }

    @Override public void setNumLinks(int quantity) {
        txtQuantity.setText(getString(R.string.link_list_subtitle_format, quantity));
    }

    @Override public void loadListData(List<WebLink> links) {
        LinkAdapter adapter = new LinkAdapter(links, listener);
        listLinks.setAdapter(adapter);
    }
}
