package com.demo.nilesh.androidassignment.ui.fragments;

import android.os.Handler;

import com.demo.nilesh.androidassignment.R;
import com.demo.nilesh.androidassignment.utility.Utils;


public class SplashFragment extends BaseFragment{

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void setUpToolBar() {

    }

    @Override
    protected void setUpView() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.reuseFragmentWIthAnimation(R.id.frag_container, Utils.HOME, getActivity(), Utils.AnimationType.SLIDE_LEFT);
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected void initiateNetworkRequest() {

    }

    @Override
    protected void onBackPressed() {

    }
}