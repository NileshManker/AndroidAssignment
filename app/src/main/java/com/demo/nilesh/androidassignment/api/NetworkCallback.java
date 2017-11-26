package com.demo.nilesh.androidassignment.api;

import com.demo.nilesh.androidassignment.beans.ListItemObj;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nilesh on 23/11/17.
 */

public interface NetworkCallback {

    public void onSuccess(ListItemObj listItemObj);

    public void onFailure(String failureMessage);
}
