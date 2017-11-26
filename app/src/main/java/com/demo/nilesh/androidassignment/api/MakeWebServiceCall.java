package com.demo.nilesh.androidassignment.api;

import com.demo.nilesh.androidassignment.beans.ListItemObj;
import com.demo.nilesh.androidassignment.beans.ListItemRowObj;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nilesh on 23/11/17.
 * This class is used to make service call and get the response.
 */

public class MakeWebServiceCall {

    private List<ListItemRowObj> listItemobjList = new ArrayList<>();
    private NetworkCallback networkCallback;

    public MakeWebServiceCall(NetworkCallback networkCallback) {
        this.networkCallback = networkCallback;
    }

    /**
     * @author nilesh
     * This method is used to initiate api call using retrofit
     */
    public void intiateAPICall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIClient apiClient = retrofit.create(APIClient.class);

        Call<ListItemObj> call = apiClient.getAPIResponse();

        call.enqueue(new Callback<ListItemObj>() {
            @Override
            public void onResponse(Call<ListItemObj> call, Response<ListItemObj> response) {
                ListItemObj listItemObj = response.body();
                networkCallback.onSuccess(listItemObj);
            }

            @Override
            public void onFailure(Call<ListItemObj> call, Throwable t) {
                networkCallback.onFailure();
            }
        });
    }
}
