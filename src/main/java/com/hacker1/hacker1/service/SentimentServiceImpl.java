package com.hacker1.hacker1.service;

import com.hacker1.hacker1.model.SentimentRequest;
import com.hacker1.hacker1.model.SentimentResponse;
import com.hacker1.hacker1.model.SentimentResponseDS;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dww055 on 11/4/17.
 */
@Service
public class SentimentServiceImpl implements SentimentService {

    private SentimentResponseDS publishSentimentDS(SentimentRequest sentimentRequest) {
        SentimentResponseDS sentimentResponse = new SentimentResponseDS();
        //Mock
        sentimentResponse.setCategory("Loneliness");
        sentimentResponse.setGravity(1);
        //
        return sentimentResponse;
    }

    private List<String> getUrlsFromSearch(String searchParameter) {

        List<String> urls = new ArrayList<>();

        //Mock
        urls.add("google.com");


        return urls;
    }

    private List<String> getLocations(String location) {

        List<String> locations = new ArrayList<>();

        //Mock
        locations.add("google.com");

        return locations;
    }

    @Override
    public SentimentResponse returnSentiments(SentimentRequest sentimentRequest) {
        SentimentResponse sentimentResponse = new SentimentResponse();
        SentimentResponseDS sentimentResponseDS = publishSentimentDS(sentimentRequest);
        List<String> urlsFromSearch = getUrlsFromSearch(sentimentResponseDS.getCategory());
        List<String> locations = getLocations(sentimentRequest.getIpAddress());
        sentimentResponse.setGravity(sentimentResponseDS.getGravity());
        sentimentResponse.setLocationUrls(locations);
        sentimentResponse.setSearchUrls(urlsFromSearch);
        return sentimentResponse;
    }
}
