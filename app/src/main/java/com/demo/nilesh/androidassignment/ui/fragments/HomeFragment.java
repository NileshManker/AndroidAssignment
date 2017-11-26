package com.demo.nilesh.androidassignment.ui.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.demo.nilesh.androidassignment.R;
import com.demo.nilesh.androidassignment.adapters.ListItemAdapter;
import com.demo.nilesh.androidassignment.beans.ListItemObj;
import com.demo.nilesh.androidassignment.utility.IDilogCallBack;
import com.demo.nilesh.androidassignment.utility.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * Created by nilesh on 24/11/17.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rv_listItem)
    RecyclerView rv_ListItemView;

    private ArrayList<ListItemObj> listItemobjList = new ArrayList<>();

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setUpToolBar() {
        mToolbar.setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    protected void setUpView() {

        ListItemObj listItemObj = new ListItemObj("My Demo" , "Demo" , "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg");
        listItemobjList.add(listItemObj);

        rv_ListItemView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_ListItemView.setLayoutManager(mLayoutManager);
        ListItemAdapter listItemAdapter = new ListItemAdapter(listItemobjList , getActivity());
        rv_ListItemView.setAdapter(listItemAdapter);
    }

    @Override
    protected void initiateNetworkRequest() {

    }

    @Override
    protected void onBackPressed() {

        Utils.showBackButtonAlert(getActivity(), new IDilogCallBack() {
            @Override
            public void onYes() {
                 getActivity().finish();
            }
        });

    }
}
