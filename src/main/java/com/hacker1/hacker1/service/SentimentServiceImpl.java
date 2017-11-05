package com.hacker1.hacker1.service;

import com.hacker1.hacker1.model.MongoResponse;
import com.hacker1.hacker1.model.SentimentRequest;
import com.hacker1.hacker1.model.SentimentResponse;
import com.hacker1.hacker1.model.SentimentResponseDS;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.InsightsResponse;
import com.sun.corba.se.spi.activation.LocatorPackage.ServerLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.xml.ws.WebServiceClient;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dww055 on 11/4/17.
 */
@Service
public class SentimentServiceImpl implements SentimentService {

    @Autowired
    private MongoTemplate mongoTemplate;

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
        String urlFromSearch = getUrlForSearch(sentimentResponseDS.getCategory());
//        List<String> locations = getLocations(sentimentRequest.());
        sentimentResponse.setGravity(sentimentResponseDS.getGravity());
//        sentimentResponse.setLocationUrls(locations);
        sentimentResponse.setSearchUrls(urlFromSearch);
        return sentimentResponse;
    }

    private com.hacker1.hacker1.model.ServerLocation getServerLocation(String ipAddress) throws GeoIp2Exception {
        com.hacker1.hacker1.model.ServerLocation serverLocation = new com.hacker1.hacker1.model.ServerLocation();
        try (com.maxmind.geoip2.WebServiceClient webServiceClient = new com.maxmind.geoip2.WebServiceClient.Builder(128419, "syfgeVm2ntuB").build()) {

            InetAddress ip = InetAddress.getByName(ipAddress);
            InsightsResponse response = webServiceClient.insights(ip);
            response.getLocation().getLatitude();
            response.getLocation().getLongitude();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return serverLocation;


    }
    private String getUrlForSearch(String searchParameter) {

//        RestTemplate template = new RestTemplate();
        String url;
//        try {
//            url =
//                    template.getForObject(khpUrl + "search?keys={category}&sort=field_node_views_count&order=asc",
//                    String.class, searchParameter);
        if (searchParameter == null) {
            url = "https://kidshelpphone.ca/search?keys=LGBTQ&sort=field_node_views_count&order=asc";
        } else
//        } catch (RestClientException e) {
            url = "https://kidshelpphone.ca/search?keys=" + searchParameter + "&sort=field_node_views_count&order=asc";

        return url;
    }

    private List<String> getLocations(String category, String ipAddress) throws GeoIp2Exception {


        List<MongoResponse> mongoResponses = new ArrayList<>();
        List<String> locations = new ArrayList<>();
        double latitude, longitude = 0;
        latitude = getServerLocation(ipAddress).getLatitude();
        longitude = getServerLocation(ipAddress).getLongitude();

        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("").is(category))
                    .addCriteria(Criteria.where("").is(category));
            mongoResponses = mongoTemplate.find(query, MongoResponse.class);
            for (MongoResponse mongoResponse: mongoResponses) {
                if (distance(latitude, longitude, mongoResponse.getLatitude(), mongoResponse.getLongitude()) < 15) {
                    locations.add(mongoResponse.getPhysicalAddress()+
                            mongoResponse.getCity()+mongoResponse.getCountry()+
                            mongoResponse.getPostalCode()+mongoResponse.getProvince());
                }
            }

        } catch (Exception e) {


        }

        return locations;
    }
    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }


}
