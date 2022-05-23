package com.client.demo;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocialClient {
    static final String URL_GET_POPULARITY = "http://localhost:8080/social/popularity";
    static final String URL_GET_IMPORTANCE = "http://localhost:8080/social/importance";
    public static void GetPopularityRanking()
    {
        //
    }
    public static void GetImportanceRequest()
    {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URL_GET_IMPORTANCE, String.class);
        System.out.println(result);
    }
}
