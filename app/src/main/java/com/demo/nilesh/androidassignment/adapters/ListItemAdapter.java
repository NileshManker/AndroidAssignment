package com.demo.nilesh.androidassignment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.nilesh.androidassignment.R;
import com.demo.nilesh.androidassignment.beans.ListItemRowObj;
import com.demo.nilesh.androidassignment.utility.DataRepository;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nilesh on 23/11/17.
 * This Adapter is used to dislpay API responce in Recycler View
 */

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder> {

    private List<ListItemRowObj> listItems;
    private Context context;

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {

        TextView tv_listItemRowTitle;
        TextView tv_listItemRowDescription;
        ImageView iv_listItemRowImage;

        public ListItemViewHolder(View v) {
            super(v);
            tv_listItemRowTitle = v.findViewById(R.id.tv_rowTitle);
            tv_listItemRowDescription = v.findViewById(R.id.tv_rowDescription);
            iv_listItemRowImage = v.findViewById(R.id.iv_rowImage);
        }
    }

    public ListItemAdapter(Context context) {
        this.context = context;
    }

    public List<ListItemRowObj> updateListItems() {
        listItems = DataRepository.getInstance().getStoredDataInCache();
        return listItems;
    }

    @Override
    public ListItemAdapter.ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_row_layout, parent, false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, final int position) {
        holder.tv_listItemRowTitle.setText(listItems.get(position).getTitle());
        holder.tv_listItemRowDescription.setText(listItems.get(position).getDescription());

        if (listItems.get(position).getImageHref() == null) {
            Picasso.with(context)
                    .load(R.mipmap.ic_launcher)
                    .into((holder).iv_listItemRowImage);
        } else {
            Picasso.with(context)
                    .load(listItems.get(position).getImageHref().replace("http", "https"))
                    .into((holder).iv_listItemRowImage);
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}