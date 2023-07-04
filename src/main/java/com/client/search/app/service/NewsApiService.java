package com.client.search.app.service;

import com.client.search.app.model.NewsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class NewsApiService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${news.api.key}")
    private String newsApiKey;

    @Value("${news.api.endpoint}")
    private String newApiEndpoint;

    public Map<String,Object> getNewsApiResponse(NewsRequest request){
        String queryString = URLEncoder.encode(request.getQueryString(), StandardCharsets.UTF_8);
        String url = String.format(newApiEndpoint, queryString, newsApiKey);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity requestEntity = new HttpEntity(httpHeaders);
        log.info("url for news api {}", url);
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity
                , new ParameterizedTypeReference<Map<String, Object>>() {
                });
        return response.getBody();
    }

    public Map<String, Object> processNewsApiResponse(Map<String, Object> request) {
        log.info("request :: {}",request);
        List<Map<String, Object>> articles = (List<Map<String, Object>>) request.get("articles");
        for(Map<String, Object> article : articles){
            String description = (String) article.get("description");
            String url = (String) article.get("url");
            //TODO:Call the sentiment analysis service to get the score and magnitude
            //
            Map<String, Object> analysis = new HashMap<>();
            double magnitude = Math.random();
            double score = Math.random();
            String sentiment = "POSITIVE";
            analysis.put("magniture", magnitude);
            analysis.put("score", score);
            analysis.put("sentiment", sentiment);
            article.put("analysis", analysis);
            log.info("URL : {}, Description : {}", url, description);
        }
        return request;
    }
}
