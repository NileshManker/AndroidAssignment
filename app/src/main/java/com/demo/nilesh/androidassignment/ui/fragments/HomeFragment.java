package com.demo.nilesh.androidassignment.ui.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.demo.nilesh.androidassignment.R;
import com.demo.nilesh.androidassignment.adapters.ListItemAdapter;
import com.demo.nilesh.androidassignment.beans.ListItemObj;
import com.demo.nilesh.androidassignment.utility.IDilogCallBack;
import com.demo.nilesh.androidassignment.utility.Utils;

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

        ListItemObj listItemObj = new ListItemObj("My Demo" , "Demo" , "https://www.google.co.in/imgres?imgurl=https%3A%2F%2Fpbs.twimg.com%2Fprofile_images%2F866364429687898112%2F6FV2SRfL_bigger.jpg&imgrefurl=https%3A%2F%2Ftwitter.com%2F%40manker_nilesh&docid=sIwUkZ5IVeNutM&tbnid=OlPsDBJYwccEwM%3A&vet=10ahUKEwiCkaqgktrXAhXMOo8KHbPYARwQMwhCKAYwBg..i&w=73&h=73&itg=1&bih=826&biw=1440&q=nilesh%20manker&ved=0ahUKEwiCkaqgktrXAhXMOo8KHbPYARwQMwhCKAYwBg&iact=mrc&uact=8");
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
