package com.health.talan.controllers;

import com.health.talan.entities.User;
import com.health.talan.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
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

	@PostMapping()
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User newUser = userServiceImpl.SaveUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
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
}
