package com.dleal.linkfinder.component.link_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dleal.linkfinder.R;
import com.dleal.linkfinder.component.base.BaseActivity;

import java.io.Serializable;

import static com.dleal.linkfinder.utils.Constants.BUNDLE_DATA;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class LinkListActivity extends BaseActivity {

    public static void open(Context context, Serializable data){
        Intent intent = new Intent(context, LinkListActivity.class);
        intent.putExtra(BUNDLE_DATA, data);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_list);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Serializable data = extras.getSerializable(BUNDLE_DATA);
            LinkListFragment fragment = LinkListFragment.getInstance(data);
            showFragment(R.id.item_detail_container, fragment);
        }
    }
}
