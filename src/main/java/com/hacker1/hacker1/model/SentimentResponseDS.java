package com.hacker1.hacker1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by dww055 on 11/4/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SentimentResponseDS implements Serializable {

    private int gravity;

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

}
