package com.health.talan.controllers;

import com.health.talan.entities.User;
import com.health.talan.message.response.ResponseMessage;
import com.health.talan.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/users")
public class UserController {

	private final UserServiceImpl userServiceImpl;

	@Autowired
	public UserController(UserServiceImpl userServiceImpl) {

		this.userServiceImpl = userServiceImpl;
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userServiceImpl.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") Long id) {
		Optional<User> user = userServiceImpl.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		String message = userServiceImpl.deleteUser(id);
		if (message.equals("Deleted")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getUser/{username}")
	public User findUserByUsername(@PathVariable String username) {
		return userServiceImpl.findUserByUsername(username);
	}

	@GetMapping("/getUserId/{username}")
	@ResponseBody
	public Long findUserIdByUsername(@PathVariable String username) {
		return userServiceImpl.findUserIdByUsername(username);
	}

	@GetMapping("/getScore/{id}")
	public int getScoreById(@PathVariable Long id) {
		return userServiceImpl.getScoreById(id);
	}

	@PutMapping("manageStatus/{id}")
	public void manageStatusConnection(@PathVariable Long id) {
		userServiceImpl.manageStatusConnection(id);
	}

	@GetMapping("getStatus/{id}")
	public boolean getStatusConnection(Long id) {
		return userServiceImpl.getStatusConnection(id);
	}

	@GetMapping("getMyFriends/{id}")
	@ResponseBody
	public Set<User> getMyFiends(@PathVariable Long id){
		return userServiceImpl.getMyFiends(id);
	}

	@PutMapping(value = "updateUserImage/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public void updateUserImage(@PathVariable Long id, @RequestParam("image") MultipartFile file) throws IOException {
		userServiceImpl.updateUserImage(id, file);
	}

	@PatchMapping("/upadteUserProfile/{id}")
	public ResponseEntity<?> updateUserProfile(@PathVariable Long id, @RequestBody User user) {
		return userServiceImpl.updateUserProfile(id, user);
	}

    @GetMapping("score/{idu}/{score}")
    public ResponseEntity<?> updateScore(@PathVariable("idu") Long id, @PathVariable("score") int score) {
        User user=userServiceImpl.updateScore(id, score);
        return new ResponseEntity<>(user, HttpStatus.OK);


    }

}
