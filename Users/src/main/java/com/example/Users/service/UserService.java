package com.example.Users.service;

import com.example.Users.model.User;
import com.example.Users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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

    @Transactional
    public User updateUser(Long id, String firstname, String lastname, LocalDate dob, String address,
                           String gender, String email, long phone, LocalDate joineddate, Integer deptnumber){
        User user =  repository.findById(id).
                orElseThrow(()-> new IllegalStateException("Error: User with Id-"+id+" does not exists."));
        if (firstname!=null && firstname.length()>0 && !Objects.equals(user.getFirstname(),firstname)){ user.setFirstname(firstname); }
        if (lastname!=null && lastname.length()>0 && !Objects.equals(user.getLastname(),lastname)){ user.setLastname(lastname); }
        if (dob!=null  && !Objects.equals(user.getDob(),dob)){ user.setDob(dob); }
        if (address!=null && address.length()>0 && !Objects.equals(user.getAdress(),address)){ user.setAdress(address); }
        if (gender!=null && gender.length()>0 && !Objects.equals(user.getGender(),gender)){ user.setGender(gender); }
        if (email!=null && email.length()>0 && !Objects.equals(user.getEmail(),email)){ user.setEmail(email); }
        if (!Objects.equals(user.getPhone(),phone)){ user.setPhone(phone); }
        if (joineddate!=null  && !Objects.equals(user.getJoineddate(),joineddate)){ user.setJoineddate(joineddate); }
        if (deptnumber!=null  && !Objects.equals(user.getDeptnumber(),deptnumber)){ user.setDeptnumber(deptnumber); }
        return repository.save(user);
    }
}
