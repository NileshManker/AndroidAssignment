package com.demo.nilesh.androidassignment.ui.fragments;

import com.demo.nilesh.androidassignment.R;
import com.demo.nilesh.androidassignment.utility.IDilogCallBack;
import com.demo.nilesh.androidassignment.utility.Utils;

public class HomeFragment extends BaseFragment {
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setUpToolBar() {

    }

    @Override
    protected void setUpView() {

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
