package com.hacker1.hacker1.controller;

import com.hacker1.hacker1.model.SentimentRequest;
import com.hacker1.hacker1.model.SentimentResponse;
import com.hacker1.hacker1.service.SentimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dww055 on 11/4/17.
 */
@RestController
@RequestMapping("/sentiment")
public class SentimentController {

    @Autowired
    private SentimentService sentimentService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SentimentResponse createSentimentAnalysis(@RequestBody SentimentRequest sentimentRequest) {
        return sentimentService.returnSentiments(sentimentRequest);
    }

}
