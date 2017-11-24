package com.demo.nilesh.androidassignment.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.demo.nilesh.androidassignment.R;
import com.demo.nilesh.androidassignment.ui.fragments.SplashFragment;
import com.demo.nilesh.androidassignment.utility.Utils;

/**
 * Created by nilesh on 23/11/17.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Utils.switchFragmentWithAnimation(R.id.frag_container, new SplashFragment(), this, null, null);
    }
}
