package com.demo.nilesh.androidassignment.api;

import com.demo.nilesh.androidassignment.beans.ListItemObj;

/**
 * Created by nilesh on 23/11/17.
 * This interface is created to handle service response.
 */

public interface NetworkCallback {

    /**
     * This method is used to get api success response
     *
     * @author : Nilesh
     */
    void onSuccess();

    /**
     * This method is used to get api failure response
     *
     * @author : Nilesh
     */
    void onFailure();
}
