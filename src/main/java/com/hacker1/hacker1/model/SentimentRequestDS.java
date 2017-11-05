package com.hacker1.hacker1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dww055 on 11/4/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SentimentRequestDS implements Serializable {

    private Map<String, String> socialMediaInfo;

    public Map<String, String> getSocialMediaInfo() {
        return socialMediaInfo;
    }

    public void setSocialMediaInfo(Map<String, String> socialMediaInfo) {
        this.socialMediaInfo = socialMediaInfo;
    }

}
