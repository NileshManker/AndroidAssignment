package com.demo.nilesh.androidassignment.api;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.demo.nilesh.androidassignment.adapters.ListItemAdapter;
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
 */

public class MakeWebServiceCall {

    private List<ListItemRowObj> listItemobjList = new ArrayList<>();
    private NetworkCallback networkCallback;

    public MakeWebServiceCall(NetworkCallback networkCallback) {
        this.networkCallback = networkCallback;
    }

    public void intiateAPICall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
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
                networkCallback.onFailure(t.toString());
            }
        });
    }
}
