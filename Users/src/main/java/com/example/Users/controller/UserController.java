package com.example.Users.controller;

import com.example.Users.model.User;
import com.example.Users.repository.UserRepository;
import com.example.Users.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public List<User> finAllProducts(){
        return service.getUsers();
    }

    @PostMapping("/addOne")
    public User addProduct(@RequestBody User product){
        return service.saveUser(product);
    }

    @PostMapping("/add")
    public List<User> addProducts(@RequestBody List<User> products){
        return service.saveUsers(products);
    }

    @GetMapping("/find/{id}")
    public User findProductById(@PathVariable Long id){
        return service.getUserById(id);
    }

    @PutMapping("/update")
    public User updateProduct(@org.springframework.web.bind.annotation.RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        return service.deleteUser(id);
    }
}
