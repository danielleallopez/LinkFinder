package com.dleal.linkfinder.component.link_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dleal.linkfinder.R;
import com.dleal.linkfinder.component.base.listeners.OnRecyclerItemListener;
import com.dleal.linkfinder.model.WebLink;

import java.util.List;

import butterknife.ButterKnife;

import static com.dleal.linkfinder.utils.Constants.HTTPS;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.CustomViewHolder> {

    private List<WebLink> links;
    private OnRecyclerItemListener onClickListener;

    public LinkAdapter(List<WebLink> links, OnRecyclerItemListener onClickListener) {
        this.links = links;
        this.onClickListener = onClickListener;
    }

    @Override public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_links, parent, false);
        return new CustomViewHolder(view);
    }

    @Override public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.bind(position, onClickListener);
    }

    @Override public int getItemCount() {
        return links.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        View parent;
        ImageView image;
        TextView title;

        public CustomViewHolder(View view) {
            super(view);
            parent = ButterKnife.findById(view, R.id.parent_row_link_item);
            image = ButterKnife.findById(view, R.id.img_link_list_item);
            title = ButterKnife.findById(view, R.id.txt_link_list_item_title);
        }

        public void bind(final int position, OnRecyclerItemListener recyclerListener) {
            WebLink link = links.get(position);
            String url = link.getUriString();

            //Set drawable depending on url's protocol
            image.setImageResource(url.startsWith(HTTPS) ? R.drawable.ic_https_black_48dp : R.drawable.ic_http_black_48dp);

            //Set url in text field
            title.setText(url);

            //Set different background to odd and even elements for clarity
            parent.setBackgroundResource(position % 2 == 0 ? R.color.gray_f2 : R.color.white);

            //Set click listener
            itemView.setOnClickListener(v -> recyclerListener.onItemSelected(position));
        }
    }
}
