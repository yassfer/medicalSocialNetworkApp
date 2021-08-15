package com.health.talan.controllers;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
 
import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.talan.message.request.LoginForm;
import com.health.talan.message.request.SignUpForm;
import com.health.talan.message.response.JwtResponse;
import com.health.talan.message.response.ResponseMessage;
import com.health.talan.entities.Role;
import com.health.talan.entities.RoleName;
import com.health.talan.entities.User;
import com.health.talan.repositories.RoleRepository;
import com.health.talan.repositories.UserRepository;
import com.health.talan.security.jwt.JwtProvider;
 
 
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
 
  @Autowired
  AuthenticationManager authenticationManager;
 
  @Autowired
  UserRepository userRepository;
 
  @Autowired
  RoleRepository roleRepository;
 
  @Autowired
  PasswordEncoder encoder;
 
  @Autowired
  JwtProvider jwtProvider;
 
  @PostMapping("/signin")
  public ResponseEntity authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
 
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
 
    SecurityContextHolder.getContext().setAuthentication(authentication);
 
    String jwt = jwtProvider.generateJwtToken(authentication);
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
 
    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
  }
 
  @PostMapping("/signup")
  public ResponseEntity registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
	  System.out.println("wsoooool 11!!");
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
          HttpStatus.BAD_REQUEST);
    }
    System.out.println("wsoooool 22!!");
    if (userRepository.existsByMail(signUpRequest.getMail())) {
      return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
          HttpStatus.BAD_REQUEST);
    }
    System.out.println("wsoooool 33!!");
    // Creating user's account
    
    User user = new User (signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getMail(), signUpRequest.getUsername(), 
    		encoder.encode(signUpRequest.getPassword()),
    		signUpRequest.getBirthDate(), signUpRequest.getAddress(), signUpRequest.getProfession(), signUpRequest.isProfessionnalisme());
    
    Set strRoles = signUpRequest.getRole();
    Set roles = new HashSet<>();
 
    strRoles.forEach(role -> {
      if (role.equals("ADMINCOMUNITY")) {
     
        Role adminRole = roleRepository.findByName(RoleName.ADMINCOMUNITY)
            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(adminRole);
      }
      else {
        Role userRole = roleRepository.findByName(RoleName.USER)
            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(userRole);
      }
    });
 
    user.setRoles(roles);
    userRepository.save(user);
 
    return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
  }
}