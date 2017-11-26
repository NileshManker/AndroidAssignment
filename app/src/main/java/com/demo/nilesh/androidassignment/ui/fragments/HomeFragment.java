package com.demo.nilesh.androidassignment.ui.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.nilesh.androidassignment.R;
import com.demo.nilesh.androidassignment.adapters.ListItemAdapter;
import com.demo.nilesh.androidassignment.api.MakeWebServiceCall;
import com.demo.nilesh.androidassignment.api.NetworkCallback;
import com.demo.nilesh.androidassignment.beans.ListItemObj;
import com.demo.nilesh.androidassignment.beans.ListItemRowObj;
import com.demo.nilesh.androidassignment.utility.IDilogCallBack;
import com.demo.nilesh.androidassignment.utility.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by nilesh on 24/11/17.
 */

public class HomeFragment extends BaseFragment implements NetworkCallback{

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
    protected void setUpView() {

    }

    @Override
    protected void initiateNetworkRequest() {
        MakeWebServiceCall makeWebServiceCall = new MakeWebServiceCall(this);
        makeWebServiceCall.intiateAPICall();
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
        //Creating an String array for the ListView
        listItemobjList = listItemObj.getRows();

        //displaying the string array into listview
        rv_ListItemView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_ListItemView.setLayoutManager(mLayoutManager);
        ListItemAdapter listItemAdapter = new ListItemAdapter(listItemobjList , getActivity());
        rv_ListItemView.setAdapter(listItemAdapter);
    }

    @Override
    public void onFailure(String failureMessage) {

    }
}
