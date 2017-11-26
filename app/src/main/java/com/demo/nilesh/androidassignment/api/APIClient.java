package com.demo.nilesh.androidassignment.api;

import com.demo.nilesh.androidassignment.beans.ListItemObj;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nileshmanker on 26/11/17.
 */

public interface APIClient {

    String BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";

    @GET("facts.json")
    Call<ListItemObj> getAPIResponse();
}
