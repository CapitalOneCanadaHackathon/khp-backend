package com.hacker1.hacker1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by dww055 on 11/4/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SentimentResponseDS implements Serializable {

    private int gravity;

    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        gravity = gravity;
    }

}
