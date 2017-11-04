package com.hacker1.hacker1.service;

import com.hacker1.hacker1.model.SentimentRequest;
import com.hacker1.hacker1.model.SentimentResponse;

/**
 * Created by dww055 on 11/4/17.
 */
public interface SentimentService {

    public SentimentResponse returnSentiments(SentimentRequest sentimentRequest);
}
