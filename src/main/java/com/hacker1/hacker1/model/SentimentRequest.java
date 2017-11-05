package com.hacker1.hacker1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dww055 on 11/4/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SentimentRequest implements Serializable{

    private String ipAddress;

    private Map<String, String> socialMediaInfo;

    private Map<Integer, List<Integer>> surveyResponse;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Map<String, String> getSocialMediaInfo() {
        return socialMediaInfo;
    }

    public void setSocialMediaInfo(Map<String, String> socialMediaInfo) {
        this.socialMediaInfo = socialMediaInfo;
    }

    public Map<Integer, List<Integer>> getSurveyResponse() {
        return surveyResponse;
    }

    public void setSurveyResponse(Map<Integer, List<Integer>> surveyResponse) {
        this.surveyResponse = surveyResponse;
    }

}
