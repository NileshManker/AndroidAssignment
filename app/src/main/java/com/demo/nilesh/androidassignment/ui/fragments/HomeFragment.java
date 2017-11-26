package com.demo.nilesh.androidassignment.ui.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.demo.nilesh.androidassignment.R;
import com.demo.nilesh.androidassignment.adapters.ListItemAdapter;
import com.demo.nilesh.androidassignment.api.MakeWebServiceCall;
import com.demo.nilesh.androidassignment.api.NetworkCallback;
import com.demo.nilesh.androidassignment.beans.ListItemObj;
import com.demo.nilesh.androidassignment.beans.ListItemRowObj;
import com.demo.nilesh.androidassignment.utility.IDilogCallBack;
import com.demo.nilesh.androidassignment.utility.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by nilesh on 24/11/17.
 */

public class HomeFragment extends BaseFragment implements NetworkCallback {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rv_listItem)
    RecyclerView rv_ListItemView;

    private List<ListItemRowObj> listItemobjList = new ArrayList<>();

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setUpToolBar() {
        mToolbar.setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    protected void initiateNetworkRequest() {
        if (Utils.isNetworkAvailable(getActivity())) {
            MakeWebServiceCall makeWebServiceCall = new MakeWebServiceCall(this);
            makeWebServiceCall.intiateAPICall();
        } else {
            Utils.showErrorDailog(getActivity(), getResources().getString(R.string.no_network_available_message),
                    getResources().getString(R.string.no_network_available_title));
        }
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

    @Override
    public void onSuccess(ListItemObj listItemObj) {
        //Creating Row Item List for the Recycler View
        listItemobjList = listItemObj.getRows();

        //displaying the  Row Item List in the Recycler View
        rv_ListItemView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_ListItemView.setLayoutManager(mLayoutManager);
        ListItemAdapter listItemAdapter = new ListItemAdapter(listItemobjList, getActivity());
        rv_ListItemView.setAdapter(listItemAdapter);
    }

    @Override
    public void onFailure() {
        Utils.showErrorDailog(getActivity(), getResources().getString(R.string.please_try_again_message),
                getResources().getString(R.string.please_try_again_title));
    }
}
