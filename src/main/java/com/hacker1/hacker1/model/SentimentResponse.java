package com.hacker1.hacker1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dww055 on 11/4/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SentimentResponse implements Serializable {


    private List<String> locationUrls;

    private List<String> searchUrls;

    private int gravity;


    public List<String> getLocationUrls() {
        return locationUrls;
    }

    public void setLocationUrls(List<String> locationUrls) {
        this.locationUrls = locationUrls;
    }

    public List<String> getSearchUrls() {
        return searchUrls;
    }

    public void setSearchUrls(List<String> searchUrls) {
        this.searchUrls = searchUrls;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        gravity = gravity;
    }

}
