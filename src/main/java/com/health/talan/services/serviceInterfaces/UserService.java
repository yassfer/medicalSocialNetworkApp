package com.health.talan.services.serviceInterfaces;

import com.health.talan.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getAllUsers();

    public Optional<User> getUserById(Long id);

    public User SaveUser(User user);

    public String deleteUser(Long id);

    public User findUserByUsername(String username);

    public Long findUserIdByUsername(String username);

    public int getScoreById(Long id);
}
