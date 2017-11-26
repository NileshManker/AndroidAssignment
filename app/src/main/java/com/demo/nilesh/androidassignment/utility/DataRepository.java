package com.demo.nilesh.androidassignment.utility;

import android.util.Log;

import com.demo.nilesh.androidassignment.beans.ListItemObj;
import com.demo.nilesh.androidassignment.beans.ListItemRowObj;

import java.util.List;

/**
 * Created by nileshmanker on 26/11/17.
 * This class is used to keep data in cache.
 */

public class DataRepository {
    private static DataRepository dataRepositoryInstance;
    private ListItemObj listItemObj;

    /**
     * Create private constructor
     */
    private DataRepository() {

    }

    /**
     * Create a static method to get singleton instance.
     */
    public static DataRepository getInstance() {
        if (dataRepositoryInstance == null) {
            dataRepositoryInstance = new DataRepository();
        }
        return dataRepositoryInstance;
    }

    /**
     * This method is used to store data in Cache.
     *
     * @param listItemObj : server response need to store
     * @author nilesh
     */
    public void storeDataToCache(ListItemObj listItemObj) {
        this.listItemObj = listItemObj;
    }

    /**
     * This method is used to fetch stored data in Cache.
     *
     * @return listItemObj
     * @author nilesh
     */
    public ListItemObj getStoredDataInCache() {
        return listItemObj;
    }
}
