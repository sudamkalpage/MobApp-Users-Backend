package com.example.Users.controller;

import com.example.Users.model.User;
import com.example.Users.repository.UserRepository;
import com.example.Users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public List<User> finAllUsers(){
        return service.getUsers();
    }

    @PostMapping("/addOne")
    public User addUser(@RequestBody User user){return service.saveUser(user);    }

    @PostMapping("/add")
    public List<User> addUsers(@RequestBody List<User> users){
        return service.saveUsers(users);
    }

    @GetMapping("/find/{id}")
    public User findUserById(@PathVariable Long id){
        return service.getUserById(id);
    }

    @PutMapping("/update")
    public User updateUser(@org.springframework.web.bind.annotation.RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("delete/{id}")
    public String deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }
}
