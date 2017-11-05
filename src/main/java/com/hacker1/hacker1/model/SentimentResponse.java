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

    private String searchUrl;

    private int gravity;

    private Content content;

    public List<String> getLocationUrls() {
        return locationUrls;
    }

    public void setLocationUrls(List<String> locationUrls) {
        this.locationUrls = locationUrls;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrls(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
