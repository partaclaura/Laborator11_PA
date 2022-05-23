package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/social")
public class NetworkController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendshipRepository friendshipRepository;

    @GetMapping("popularity")
    public List<User> getMostPopular(@RequestBody int k)
    {
        List<User> ranking = new ArrayList<>();
        User[] mostPopular = new User[k];
        int[] popularity = new int[k];
        mostPopular[0] = userRepository.findAll().get(0);
        for(User user : userRepository.findAll())
        {
            int friends = 0;
            for(Friendship friendship : friendshipRepository.findAll())
            {
                if(friendship.getIdFriend1() == user.getId() || friendship.getIdFriend2() == user.getId())
                    friends++;
            }
            for(int i = 0; i < k; i++)
            {
                if(popularity[i] < friends) {
                    popularity[i] = friends;
                    mostPopular[i] = user;
                    i = k;
                }
            }
        }
        Collections.addAll(ranking, mostPopular);
        return ranking;
    }

    @GetMapping("importance")
    public List<User> getMostImportant()
    {
        List<User> mostImp = new ArrayList<>();
        List<Friendship> friendships = friendshipRepository.findAll();
        int nUsers = 0;
        for(User user : userRepository.findAll())
            nUsers++;
        ImportantUsers importantUsers = new ImportantUsers(friendships, nUsers);
        for(int i : importantUsers.getImportantUsers())
        {
            for(User user : userRepository.findAll())
                if(user.getId() == i + 1)
                    mostImp.add(user);
        }
        return mostImp;
    }

}
