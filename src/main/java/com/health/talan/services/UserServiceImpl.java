package com.health.talan.services;

import com.health.talan.entities.User;
import com.health.talan.message.response.ResponseMessage;
import com.health.talan.repositories.UserRepository;
import com.health.talan.services.serviceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    ChallengeService challengeService;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>) userRepo.findAll();
        return users;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        //user.get().setImage(challengeService.decompressBytes(user.get().getImage()));
        return Optional.ofNullable(user.orElse(null));
    }

    @Override
    public String deleteUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.deleteById(id);
            return "User Deleted";
        } else {
            return "User doesn't exit";
        }
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userRepo.findByUsername(username).get();
        //user.setImage(challengeService.decompressBytes(user.getImage()));
        return user;
    }

    @Override
    public Long findUserIdByUsername(String username) {
        System.out.println("testID:: " + findUserByUsername(username).getId());
        return findUserByUsername(username).getId();
    }

    @Override
    public int getScoreById(Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.get().getScore();
    }

    @Override
    public void manageStatusConnection(Long id) {
        User user = userRepo.findById(id).get();
        if (user.isConnected()) {
            user.setConnected(false);
        } else {
            user.setConnected(true);
        }
        userRepo.save(user);
    }

    @Override
    public boolean getStatusConnection(Long id) {
        User user = userRepo.findById(id).get();
        return user.isConnected();
    }

    @Override
    public Set<User> getMyFiends(Long id) {
        User user = userRepo.findById(id).get();
        Set<User> friends = user.getFriends();
        return friends;
    }

    @Override
    public void updateUserImage(Long id, MultipartFile file) throws IOException {
        User user = userRepo.findById(id).get();
        user.setLogo(challengeService.compressBytes(file.getBytes()));
        userRepo.save(user);
    }

    @Override
    public ResponseEntity<?> updateUserProfile(Long id, User user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userRepo.existsByMail(user.getMail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        User oldUser = userRepo.findById(id).get();
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setAddress(user.getAddress());
        oldUser.setBirthDate(user.getBirthDate());
        oldUser.setMail(user.getMail());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(encoder.encode(user.getPassword()));
        return new ResponseEntity<>(new ResponseMessage("User updated successfully !"),
                HttpStatus.OK);
    }

    @Override
    public User updateScore(long idu, int score) {
        User user = userRepo.findById(idu).get();
        user.setScore(score);
        userRepo.save(user);
        return user;
    }


}
