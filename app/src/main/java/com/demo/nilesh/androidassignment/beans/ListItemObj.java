package com.demo.nilesh.androidassignment.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nilesh on 23/11/17.
 * This Class is used to store api service response object
 */

public class ListItemObj {

    @SerializedName("title")
    private String title;

    @SerializedName("rows")
    private List<ListItemRowObj> rows;

    public ListItemObj(String title, List<ListItemRowObj> rows) {
        this.title = title;
        this.rows = rows;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ListItemRowObj> getRows() {
        return rows;
    }

    public void setRows(List<ListItemRowObj> rows) {
        this.rows = rows;
    }
}
