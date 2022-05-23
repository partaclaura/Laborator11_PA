package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    // RESTful API methods for Retrieval operations
    @GetMapping
    public List<User> list(){
        return service.listAll();
    }
    // RESTful API method for Create operation
    @GetMapping("{id}")
    public ResponseEntity<User> get(@PathVariable Integer id)
    {
        try{
            User user = service.get(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public User add(@RequestBody User user)
    {
        return service.save(user);
    }

    // RESTful API method for Update operation
    //PUT
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody String name)
    {
        User user = service.get(id);
        user.setName(name);
        service.save(user);
        return ResponseEntity.ok(user);
    }

    // RESTful API method for Delete operation
    @DeleteMapping("{id}")
    public  ResponseEntity<HttpStatus> deleteUser(@PathVariable int id){
        User user = service.get(id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
