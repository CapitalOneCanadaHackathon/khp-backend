package com.hacker1.hacker1.service;

import com.hacker1.hacker1.model.SentimentRequest;
import com.hacker1.hacker1.model.SentimentResponse;
import com.maxmind.geoip2.exception.GeoIp2Exception;

/**
 * Created by dww055 on 11/4/17.
 */
public interface SentimentService {

    public SentimentResponse returnSentiments(SentimentRequest sentimentRequest) throws GeoIp2Exception;
}
