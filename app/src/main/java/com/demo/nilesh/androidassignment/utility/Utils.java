package com.demo.nilesh.androidassignment.utility;

import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.demo.nilesh.androidassignment.R;
import com.demo.nilesh.androidassignment.ui.fragments.HomeFragment;
import com.demo.nilesh.androidassignment.ui.fragments.SplashFragment;

/**
 * Created by nilesh on 24/11/17.
 */

public class Utils {

    private static String CURRENT_TAG = null;
    public static final String SPLASH = "SplashScreen";
    public static final String HOME = "HomeScreen";
    public enum AnimationType {
        SLIDE_LEFT, SLIDE_RIGHT, SLIDE_UP, SLIDE_DOWN, FADE_IN, SLIDE_IN_SLIDE_OUT, FADE_OUT
    }

    /**
     * @param container_id de
     * @param fragment
     * @param activity
     * @param TAG
     * @param transitionStyle
     */
    public static void switchFragmentWithAnimation(int container_id, Fragment fragment,
                                                   FragmentActivity activity, String TAG, AnimationType transitionStyle) {

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        if (transitionStyle != null) {
            switch (transitionStyle) {

                // Exit from down
                case SLIDE_DOWN:
                    fragmentTransaction.setCustomAnimations(R.anim.slide_up,
                            R.anim.slide_down);
                    break;

                // Enter from Up
                case SLIDE_UP:
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_up,
                            R.anim.slide_out_up);
                    break;

                // Enter from left
                case SLIDE_LEFT:
                    fragmentTransaction.setCustomAnimations(R.anim.slide_left,
                            R.anim.slide_out_left);
                    break;

                // Enter from right
                case SLIDE_RIGHT:
                    fragmentTransaction.setCustomAnimations(R.anim.slide_right,
                            R.anim.slide_out_right);
                    break;

                default:
                    break;
            }
        }
        CURRENT_TAG = TAG;
        fragmentTransaction.replace(container_id, fragment);
        fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commit();
    }

    /**
     * @param containerID
     * @param FRAG_TAG
     * @param baseActivity
     * @param transitionStyle
     */
    public static void reuseFragmentWIthAnimation(int containerID, String FRAG_TAG,
                                                  FragmentActivity baseActivity, AnimationType transitionStyle) {

        Fragment fragmentToReplace = null;

        FragmentManager fragmentManager = baseActivity
                .getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (CURRENT_TAG == null || !FRAG_TAG.equals(CURRENT_TAG)) {

            if (transitionStyle != null) {
                switch (transitionStyle) {
                    case SLIDE_DOWN:
                        // Exit from down
                        transaction.setCustomAnimations(R.anim.slide_up,
                                R.anim.slide_down);
                        break;
                    case SLIDE_UP:
                        // Enter from Up
                        transaction.setCustomAnimations(R.anim.slide_in_up,
                                R.anim.slide_out_up);
                        break;
                    case SLIDE_LEFT:
                        // Enter from left
                        transaction.setCustomAnimations(R.anim.slide_left,
                                R.anim.slide_out_left);
                        break;
                    // Enter from right
                    case SLIDE_RIGHT:
                        transaction.setCustomAnimations(R.anim.slide_right,
                                R.anim.slide_out_right);
                        break;
                    default:
                        break;
                }
            }

            // Try to find the fragment we are switching to
            Fragment fragment = fragmentManager.findFragmentByTag(FRAG_TAG);

            // If the new fragment can't be found in the manager, create a new
            // one
            if (fragment == null) {
                if (FRAG_TAG.equals(HOME)) {
                    fragmentToReplace = new HomeFragment();
                } else if (FRAG_TAG.equals(SPLASH)) {
                    fragmentToReplace = new SplashFragment();
                }
            }
            else{
                if (FRAG_TAG.equals(HOME)) {
                    fragmentToReplace = (HomeFragment) fragment;
                } else if (FRAG_TAG.equals(SPLASH)) {
                    fragmentToReplace = (SplashFragment) fragment;
                }
            }

            CURRENT_TAG = FRAG_TAG;

            // Replace our current fragment with the one we are changing to
            transaction.replace(containerID, fragmentToReplace, FRAG_TAG);
            transaction.commit();

        }
    }

    public static Dialog showErrorDailog(FragmentActivity activityContext, String responseMessage , String errorTitle) {
        final Dialog showErrorDialog = new Dialog(activityContext);
        showErrorDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        showErrorDialog.getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
        showErrorDialog.getWindow().setGravity(Gravity.CENTER);
        showErrorDialog.setContentView(R.layout.error_dialog);
        ((TextView) showErrorDialog.findViewById(R.id.error_header)).setText(errorTitle);
        ((TextView) showErrorDialog.findViewById(R.id.error_message)).setText(responseMessage);

        showErrorDialog.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showErrorDialog.dismiss();
            }
        });
        showErrorDialog.show();
        return showErrorDialog;
    }

    public static void showBackButtonAlert(FragmentActivity activityContext, final IDilogCallBack dilogCallBack) {
        final Dialog showBackAlertDialog = new Dialog(activityContext);
        showBackAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        showBackAlertDialog.getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
        showBackAlertDialog.getWindow().setGravity(Gravity.CENTER);
        showBackAlertDialog.setContentView(R.layout.back_button_error_dialog);
        TextView tvErrorMessage = (TextView) showBackAlertDialog.findViewById(R.id.alert_message);
        tvErrorMessage.setText(activityContext.getResources().getString(R.string.back_message));

        showBackAlertDialog.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dilogCallBack.onYes();
                showBackAlertDialog.dismiss();
            }
        });

        showBackAlertDialog.findViewById(R.id.btn_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBackAlertDialog.dismiss();
            }
        });
        showBackAlertDialog.show();
    }
}
