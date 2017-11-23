package com.demo.nilesh.androidassignment.beans;

/**
 * Created by nilesh on 23/11/17.
 */

public class ListItemObj {

    private String rowTitle;
    private String rowDescription;
    private String rowImageURL;

    public ListItemObj(String rowTitle, String rowDescription, String rowImageURL) {
        this.rowTitle = rowTitle;
        this.rowDescription = rowDescription;
        this.rowImageURL = rowImageURL;
    }

    public String getRowTitle() {
        return rowTitle;
    }

    public void setRowTitle(String rowTitle) {
        this.rowTitle = rowTitle;
    }

    public String getRowDescription() {
        return rowDescription;
    }

    public void setRowDescription(String rowDescription) {
        this.rowDescription = rowDescription;
    }

    public String getRowImageURL() {
        return rowImageURL;
    }

    public void setRowImageURL(String rowImageURL) {
        this.rowImageURL = rowImageURL;
    }
}
