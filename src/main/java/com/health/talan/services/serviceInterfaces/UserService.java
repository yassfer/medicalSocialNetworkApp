package com.health.talan.services.serviceInterfaces;

import com.health.talan.entities.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    public List<User> getAllUsers();

    public Optional<User> getUserById(Long id);

    public String deleteUser(Long id);

    public User findUserByUsername(String username);

    public Long findUserIdByUsername(String username);

    public int getScoreById(Long id);

    public void manageStatusConnection(Long id);

    public boolean getStatusConnection(Long id);

    public Set<User> getMyFiends(Long id);

    public void updateUserImage(Long id, MultipartFile file) throws IOException;

    public ResponseEntity<?> updateUserProfile(Long id, User user);

    public User updateScore(long idu, int score);
}
