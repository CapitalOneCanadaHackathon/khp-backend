package com.hacker1.hacker1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dww055 on 11/5/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Content implements Serializable {

    List<String> youtubeUrls;

    List<Article> articleList;

    public List<String> getYoutubeUrls() {
        return youtubeUrls;
    }

    public void setYoutubeUrls(List<String> youtubeUrls) {
        this.youtubeUrls = youtubeUrls;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
