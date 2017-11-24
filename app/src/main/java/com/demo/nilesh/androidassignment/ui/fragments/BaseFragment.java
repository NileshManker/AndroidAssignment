package com.demo.nilesh.androidassignment.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.nilesh.androidassignment.R;
import com.demo.nilesh.androidassignment.utility.Utils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Base fragment created to be extended by every fragment in this application. This class provides
 * dependency injection configuration, ButterKnife Android library configuration and some methods
 * common to every fragment.
 */
public abstract class BaseFragment extends Fragment {

    Unbinder unbinder;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getFragmentLayout(), container, false);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i(getTag(), "keyCode: " + keyCode);
                if (event.getAction() != KeyEvent.ACTION_DOWN) {
                    onBackPressed();
                    return true;
                } else {
                    return false;
                }
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
        setUpToolBar();
        setUpView();
        initiateNetworkRequest();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
     * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
     * inflate in this method when extends BaseFragment.
     */
    protected abstract int getFragmentLayout();

    protected abstract void setUpToolBar();

    protected abstract void setUpView();

    protected abstract void initiateNetworkRequest();

    protected abstract void onBackPressed();
    public void showErrorMessage(String serverErrorMessage) {

        Utils.showErrorDailog(getActivity(), serverErrorMessage , getResources().getString(R.string.error));
    }

    /**
     * Replace every field annotated with ButterKnife annotations like @InjectView with the proper
     * value.
     *
     * @param view to extract each widget injected in the fragment.
     */
    private void injectViews(final View view) {
        unbinder = ButterKnife.bind(this, view);
    }
}