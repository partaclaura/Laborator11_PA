package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/friends")
public class FriendshipController {
    @Autowired
    private FriendshipRepository friendshipRepository;

    @GetMapping
    public List<Friendship> getAllFriendships()
    {
        return friendshipRepository.findAll();
    }

    @PostMapping
    public Friendship createFriendship(@RequestBody Friendship friendship)
    {
        return friendshipRepository.save(friendship);
    }

    @GetMapping("{id}")
    public ResponseEntity<Friendship> getFriendshipById(@PathVariable int id)
    {
        Friendship friendship = friendshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not exist with id:" + id));
        return ResponseEntity.ok(friendship);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteFriendship(@PathVariable int id)
    {
        Friendship friendship = friendshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not exist with id:" + id));
        friendshipRepository.delete(friendship);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
