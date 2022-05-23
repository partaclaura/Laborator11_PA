package com.client.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("All users: ");
		UserClient.GetUsersRequest();
		System.out.println("User with id = 1: ");
		UserClient.GetUserRequest();
		System.out.println("Adding a user: ");
		UserClient.PutRequest();
		UserClient.GetUsersRequest();
		System.out.println("All friendships: ");
		FriendsClient.GetFriendshipsRequest();
		System.out.println("Most important users: ");
		SocialClient.GetImportanceRequest();
	}

}
