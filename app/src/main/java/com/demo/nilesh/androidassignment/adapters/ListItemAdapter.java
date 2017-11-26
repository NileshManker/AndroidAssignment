package com.demo.nilesh.androidassignment.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.nilesh.androidassignment.R;
import com.demo.nilesh.androidassignment.beans.ListItemObj;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nilesh on 23/11/17.
 */

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder> {

    private List<ListItemObj> listItems;
    private int rowLayout;
    private Context context;

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {

        TextView tv_listItemRowTitle;
        TextView tv_listItemRowDescription;
        ImageView iv_listItemRowImage;


        public ListItemViewHolder(View v) {
            super(v);

            tv_listItemRowTitle = (TextView) v.findViewById(R.id.tv_rowTitle);
            tv_listItemRowDescription = (TextView) v.findViewById(R.id.tv_rowDescription);
            iv_listItemRowImage = (ImageView) v.findViewById(R.id.iv_rowImage);

        }
    }

    public ListItemAdapter(List<ListItemObj> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ListItemAdapter.ListItemViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_row_layout, parent, false);
        return new ListItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ListItemViewHolder holder, final int position) {
        holder.tv_listItemRowTitle.setText(listItems.get(position).getRowTitle());
        holder.tv_listItemRowDescription.setText(listItems.get(position).getRowDescription());

        if(listItems.get(position).getRowImageURL()==null){
            Picasso.with(context)
                    .load(R.mipmap.ic_launcher)
                    .into((holder).iv_listItemRowImage);
        }else {
            Picasso.with(context)
                    .load(listItems.get(position).getRowImageURL().replace("http","https"))
                    .into((holder).iv_listItemRowImage);
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}