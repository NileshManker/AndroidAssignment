package com.demo.nilesh.androidassignment.utility;

import android.util.Log;

import com.demo.nilesh.androidassignment.beans.ListItemObj;
import com.demo.nilesh.androidassignment.beans.ListItemRowObj;

import java.util.List;

/**
 * Created by nileshmanker on 26/11/17.
 * This class is used to keep cache of data
 */

public class DataRepository {
    private static DataRepository dataRepositoryInstance;
    private ListItemObj listItemObj;
    /**
     * Create private constructor
     */
    private DataRepository(){

    }
    /**
     * Create a static method to get instance.
     */
    public static DataRepository getInstance(){
        if(dataRepositoryInstance == null){
            dataRepositoryInstance = new DataRepository();
        }
        return dataRepositoryInstance;
    }

    public void storeDataToCache(ListItemObj listItemObj){
        this.listItemObj = listItemObj;
    }

    public List<ListItemRowObj> getStoredDataInCache(){
        List<ListItemRowObj> listItemRowObjs = listItemObj.getRows();
        return listItemRowObjs;
    }
}
