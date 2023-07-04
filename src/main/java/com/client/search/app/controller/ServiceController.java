package com.client.search.app.controller;

import com.client.search.app.model.NewsRequest;
import com.client.search.app.service.NewsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private NewsApiService newsApiService;

    //@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @RequestMapping("/test")
    public String getResponse(){
        return "success";
    }

    @PostMapping("/news")
    public Map<String, Object> newsApi(@RequestBody NewsRequest request){
        return newsApiService.getNewsApiResponse(request);
    }

    @PostMapping("/news/process")
    public Map<String, Object> processNewsApi(@RequestBody Map<String, Object> request){
        return newsApiService.processNewsApiResponse(request);
    }


}
