package com.example.Users.service;

import com.example.Users.model.User;
import com.example.Users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User saveUser(User user){
        Optional<User> UserOptional =  repository.findUserByEMail(user.getEmail());
        if (UserOptional.isPresent()){
            throw new IllegalStateException("Error: This email is already taken!");
        }
        return repository.save(user);
    }

    public List<User> saveUsers(List<User> users){
        return repository.saveAll(users);
    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public User getUserById(Long id){
        Optional<User> UserOptional = repository.findById(id);
        if (UserOptional.isEmpty()){
            throw new IllegalStateException("Error: User with Id-"+id+" does not exists.");
        }
        return repository.findById(id).orElse(null);
    }

    public String deleteUser(Long id){
        Optional<User> UserOptional = repository.findById(id);
        if (UserOptional.isEmpty()){
            throw new IllegalStateException("Error: User with Id-"+id+" does not exists.");
        }

        repository.deleteById(id);
        return "User with id : "+id+" successfully removed!";
    }

    public User updateUser(User user){
        User existingProd = repository.findById(user.getId()).orElse(null);
        existingProd.setFirstname(user.getFirstname());
        existingProd.setLastname(user.getLastname());
        existingProd.setDob(user.getDob());
        existingProd.setAdress(user.getAdress());
        existingProd.setGender(user.getGender());
        existingProd.setEmail(user.getEmail());
        existingProd.setPhone(user.getPhone());
        existingProd.setJoineddate(user.getJoineddate());
        existingProd.setDeptnumber(user.getDeptnumber());
        return repository.save(existingProd);
    }
}
