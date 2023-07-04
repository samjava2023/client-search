package com.client.search.app.model;

import lombok.Data;

@Data
public class NewsRequest {
    private String queryString;
    private String country;
}
