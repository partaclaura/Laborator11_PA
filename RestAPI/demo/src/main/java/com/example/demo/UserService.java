package com.example.demo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> listAll(){
        return repo.findAll();
    }

    public User save(User user)
    {
        return repo.save(user);
    }

    public User get(Integer id)
    {
        return repo.findById(id).get();
    }

    public void delete(Integer id)
    {
        repo.deleteById(id);
    }
}
