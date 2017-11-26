package com.demo.nilesh.androidassignment.ui.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

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
    RecyclerView mRecyclerView;

    @BindView(R.id.sw_pullToRefresh)
    SwipeRefreshLayout mPullToRefresh;

    private List<ListItemRowObj> listItemobjList = new ArrayList<>();
    private MakeWebServiceCall makeWebServiceCall;
    private ListItemAdapter listItemAdapter;

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
            mPullToRefresh.setRefreshing(true);
            makeWebServiceCall = new MakeWebServiceCall(this);
            makeWebServiceCall.intiateAPICall();
        } else {
            mPullToRefresh.setRefreshing(false);
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
    public void onSuccess() {
        displayDatInRecyclerView();
    }

    @Override
    public void onFailure() {
        displayFailureDilogue();
    }

    private void displayFailureDilogue() {
        mPullToRefresh.setRefreshing(false);
        Utils.showErrorDailog(getActivity(), getResources().getString(R.string.please_try_again_message),
                getResources().getString(R.string.please_try_again_title));
    }

    @Override
    protected void setUpView() {
        mPullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (Utils.isNetworkAvailable(getActivity())) {
                    mPullToRefresh.setRefreshing(true);
                    makeWebServiceCall = new MakeWebServiceCall(new NetworkCallback() {
                        @Override
                        public void onSuccess() {
                            mPullToRefresh.setRefreshing(false);
                            listItemAdapter.updateListItems();
                            listItemAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure() {
                            displayFailureDilogue();
                        }
                    });
                    makeWebServiceCall.intiateAPICall();
                } else {
                    mPullToRefresh.setRefreshing(false);
                    Utils.showErrorDailog(getActivity(), getResources().getString(R.string.no_network_available_message),
                            getResources().getString(R.string.no_network_available_title));
                }
            }
        });
    }

    /**
     * This method is used to display api response data in recycler view
     * @author nilesh
     * */
    private void displayDatInRecyclerView() {
        mPullToRefresh.setRefreshing(false);
      //displaying the  Row Item List in the Recycler View
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        listItemAdapter = new ListItemAdapter( getActivity());
        listItemAdapter.updateListItems();
        mRecyclerView.setAdapter(listItemAdapter);
    }
}
