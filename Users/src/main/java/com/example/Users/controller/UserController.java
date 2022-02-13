package com.example.Users.controller;

import com.example.Users.model.User;
import com.example.Users.repository.UserRepository;
import com.example.Users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PutMapping(path="/update/{userID}")
    public User updateUser(@PathVariable("userID") Long id,
                           @RequestParam(required = false) String firstname,
                           @RequestParam(required = false) String lastname,
                           @RequestParam(required = false) LocalDate dob,
                           @RequestParam(required = false) String address,
                           @RequestParam(required = false) String gender,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) long phone,
                           @RequestParam(required = false) LocalDate joineddate,
                           @RequestParam(required = false) Integer deptnumber
                           ){
        return service.updateUser(id, firstname, lastname, dob, address, gender, email, phone, joineddate, deptnumber);
    }

    @DeleteMapping("delete/{id}")
    public String deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }
}
