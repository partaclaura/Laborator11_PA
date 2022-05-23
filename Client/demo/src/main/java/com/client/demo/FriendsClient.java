package com.client.demo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class FriendsClient {
    static final String URL_GET_FRIENDS = "http://localhost:8080/friends";
    static final String URL_GET_FRIEND = "http://localhost:8080/friends/{id}";
    public static void GetFriendshipsRequest()
    {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URL_GET_FRIENDS, String.class);
        System.out.println(result);
    }

    public static void GetFriendshipRequest()
    {
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        RestTemplate restTemplate = new RestTemplate();
        User result = restTemplate.getForObject(URL_GET_FRIEND, User.class, params);
        System.out.println(result.getId() + " " + result.getName());
    }

    public static void PostRequest()
    {
        Friendship friendship = new Friendship(4, 5);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Friendship> requestBody = new HttpEntity<>(friendship, headers);

        Friendship u = restTemplate.postForObject(URL_GET_FRIENDS, requestBody, Friendship.class);
        if(u != null)
        {
            System.out.println("Friendship created: " + u.getId());
        }
        else{
            System.out.println("Can't create friendship");
        }
    }

    public static void DeleteRequest()
    {
        Map<String, String> params = new HashMap<>();
        params.put("id", "");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URL_GET_FRIEND, params);
    }

}
