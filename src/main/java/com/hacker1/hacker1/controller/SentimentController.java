package com.hacker1.hacker1.controller;

import com.hacker1.hacker1.model.SentimentRequest;
import com.hacker1.hacker1.model.SentimentResponse;
import com.hacker1.hacker1.service.SentimentService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dww055 on 11/4/17.
 */
@RestController
@RequestMapping("/sentiment")
@CrossOrigin(origins = "http://10.250.45.159:3001")
public class SentimentController {

    @Autowired
    private SentimentService sentimentService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SentimentResponse createSentimentAnalysis(@RequestBody SentimentRequest sentimentRequest) throws GeoIp2Exception {
        return sentimentService.returnSentiments(sentimentRequest);
    }

}
