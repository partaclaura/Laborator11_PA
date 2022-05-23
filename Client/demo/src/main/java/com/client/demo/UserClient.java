package com.client.demo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class UserClient {
    static final String URL_GET_USERS = "http://localhost:8080/users";
    static final String URL_GET_USER = "http://localhost:8080/users/{id}";

    public static void GetUsersRequest()
    {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URL_GET_USERS, String.class);
        System.out.println(result);
    }

    public static void GetUserRequest()
    {
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        RestTemplate restTemplate = new RestTemplate();
        User result = restTemplate.getForObject(URL_GET_USER, User.class, params);
        System.out.println(result.getId() + " " + result.getName());
    }

    public static void PostRequest()
    {
        User user = new User();
        user.setName("Cosmin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> requestBody = new HttpEntity<>(user, headers);

        User u = restTemplate.postForObject(URL_GET_USERS, requestBody, User.class);
        if(u != null)
        {
            System.out.println("User created: " + u.getId());
        }
        else{
            System.out.println("Can't create user");
        }
    }

    public static void PutRequest()
    {
        Map<String, String> params = new HashMap<>();
        params.put("id", "6");
        String name = "Davi";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(URL_GET_USER, name, params);
    }

    public static void DeleteRequest()
    {
        Map<String, String> params = new HashMap<>();
        params.put("id", "");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URL_GET_USER, params);
    }
}
