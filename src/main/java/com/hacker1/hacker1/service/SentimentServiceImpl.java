package com.hacker1.hacker1.service;

import com.hacker1.hacker1.model.*;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.InsightsResponse;
import com.sun.corba.se.spi.activation.LocatorPackage.ServerLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.WebServiceClient;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dww055 on 11/4/17.
 */
@Service
public class SentimentServiceImpl implements SentimentService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static final String khpUrl = "https://kidshelpphone.ca";

    public static final int radius = 15;

    public static final String dsUrl = "https://141.117.117.217/get-score";


    private SentimentResponseDS publishSentimentDS(Map<String, String> sentimentRequest) {
        SentimentResponseDS sentimentResponse = new SentimentResponseDS();
        RestTemplate template = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        SentimentRequestDS sentimentRequestDS = new SentimentRequestDS();
        sentimentRequestDS.setSocialMediaInfo(sentimentRequest);

        HttpEntity<SentimentRequestDS> entity = new HttpEntity<SentimentRequestDS>(
                sentimentRequestDS, httpHeaders);

        try {
            ResponseEntity<SentimentResponseDS> sentimentResponseDS = template.
                    exchange(dsUrl, HttpMethod.POST, entity, SentimentResponseDS.class);
             sentimentResponse = new SentimentResponseDS();
            if (sentimentResponseDS.getBody()!= null && sentimentResponseDS.getBody().getGravity() == 0) {
                sentimentResponse.setGravity(sentimentResponseDS.getBody().getGravity());
                return sentimentResponse;

            }
        } catch (Exception e){
            sentimentResponse.setGravity(2);
            return sentimentResponse;
        }
        return sentimentResponse;
    }


    @Override
    public SentimentResponse returnSentiments(SentimentRequest sentimentRequest) throws GeoIp2Exception {
        SentimentResponse sentimentResponse = new SentimentResponse();
        SentimentResponseDS sentimentResponseDS = publishSentimentDS(sentimentRequest.getSocialMediaInfo());
        String urlFromSearch = getUrlForSearch(sentimentRequest.getCategory());
        List<String> locations = getLocations(sentimentRequest.getCategory(), sentimentRequest.getIpAddress());
        sentimentResponse.setGravity(sentimentResponseDS.getGravity());
        sentimentResponse.setLocationUrls(locations);
        sentimentResponse.setSearchUrls(urlFromSearch);
        return sentimentResponse;
    }

    private com.hacker1.hacker1.model.ServerLocation getServerLocation(String ipAddress) throws GeoIp2Exception {
        com.hacker1.hacker1.model.ServerLocation serverLocation = new com.hacker1.hacker1.model.ServerLocation();
        try (com.maxmind.geoip2.WebServiceClient webServiceClient = new com.maxmind.geoip2.WebServiceClient.Builder(128419, "syfgeVm2ntuB").build()) {

            InetAddress ip = InetAddress.getByName(ipAddress);
            InsightsResponse response = webServiceClient.insights(ip);
            serverLocation.setLatitude(response.getLocation().getLatitude());
            serverLocation.setLongitude(response.getLocation().getLongitude());
        } catch (IOException e) {
            serverLocation.setLatitude(43.6569962);
            serverLocation.setLongitude(-79.4524287);
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
            query.addCriteria(Criteria.where("TopCategory").is(category));
            mongoResponses = mongoTemplate.find(query, MongoResponse.class);
            for (MongoResponse mongoResponse: mongoResponses) {
                if (distance(latitude, longitude, mongoResponse.getLatitude(), mongoResponse.getLongitude()) < radius) {
                    locations.add(mongoResponse.getPhysicalAddress()+
                            mongoResponse.getCity()+mongoResponse.getCountry()+
                            mongoResponse.getPostalCode()+mongoResponse.getProvince());
                }
            }
            if (locations.size() == 0) {
                locations.add("2340 Dundas Street West Suite 301, Toronto, ON M6P 4A9, Canada");
            }
            return locations;

        } catch (Exception e) {

          locations.add("2340 Dundas Street West Suite 301, Toronto, ON M6P 4A9, Canada");
          return locations;
        }

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

    private Article buildArticleAnxiety1() {
        Article article = new Article();
        article.setHyperlink("https://kidshelpphone.ca/article/how-cope-panic-and-anxiety");
        article.setTitle("How to cope with panic and anxiety");
        article.setDescription("panic anxiety");
        article.setImageUrl("https://kidshelpphone.ca/sites/default/files/2017-10/photo-of-young-man-viewing-tablet-on-beige-couch-tile.jpg");
        return article;
    }

    private Content buildContentAnxiety1() {
        List<Article> articles = new ArrayList<>();
        List<String> youtubeUrls = new ArrayList<>();
        articles.add(buildArticleAnxiety1());
        Content content = new Content();
        content.setArticleList(articles);
        youtubeUrls.add("xqOxxbxWUV8");
        youtubeUrls.add("Z_jkNmj5S0s");
        content.setYoutubeUrls(youtubeUrls);
        return content;
    }

    private Content buildContentAnxiety2() {
        List<Article> articles = new ArrayList<>();
        List<String> youtubeUrls = new ArrayList<>();
        articles.add(buildArticleAnxiety1());
        Content content = new Content();
        content.setArticleList(articles);
        youtubeUrls.add("xqOxxbxWUV8");
        youtubeUrls.add("Z_jkNmj5S0s");
        content.setYoutubeUrls(youtubeUrls);
        return content;
    }

    private Content buildContentAnxiety3() {
        List<Article> articles = new ArrayList<>();
        List<String> youtubeUrls = new ArrayList<>();
        articles.add(buildArticleAnxiety1());
        Content content = new Content();
        content.setArticleList(articles);
        youtubeUrls.add("xqOxxbxWUV8");
        youtubeUrls.add("Z_jkNmj5S0s");
        content.setYoutubeUrls(youtubeUrls);
        return content;
    }
}
