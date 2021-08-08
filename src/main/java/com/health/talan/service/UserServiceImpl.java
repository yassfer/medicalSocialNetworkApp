package com.health.talan.service;

import com.health.talan.entities.User;
import com.health.talan.Repository.UserRepo;
import com.health.talan.service.serviceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo){

        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUsers() {

        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id){

        return Optional.ofNullable(userRepo.findById(id).orElse(null));
    }

    @Override
    public User SaveUser(User user){

        return userRepo.save(user);
    }

    @Override
    public String deleteUser(Long id){
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            userRepo.deleteById(id);
            return "User Deleted";
        }
        else {
            return "User doesn't exit";
        }
    }
}

