package com.health.talan.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.health.talan.entities.Role;
import com.health.talan.entities.RoleName;
import com.health.talan.entities.User;
import com.health.talan.message.request.LoginForm;
import com.health.talan.message.request.SignUpForm;
import com.health.talan.message.response.JwtResponse;
import com.health.talan.message.response.ResponseMessage;
import com.health.talan.repositories.RoleRepository;
import com.health.talan.repositories.UserRepository;
import com.health.talan.security.jwt.JwtProvider;

@Service
public class AuthService {

    private static Long IDCURRENTUSER ;
    private static User CURRENTUSER ;

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

    @Autowired
    ChallengeService challengeService;



    public AuthService() {
        super();
    }


    public static Long getIDCURRENTUSER() {
        return IDCURRENTUSER;
    }


    public static void setIDCURRENTUSER(Long IDCURRENTUSER) {
        AuthService.IDCURRENTUSER = IDCURRENTUSER;
    }


    public static User getCURRENTUSER() {
        return CURRENTUSER;
    }


    public static void setCURRENTUSER(User cURRENTUSER) {
        CURRENTUSER = cURRENTUSER;
    }


    public ResponseEntity<JwtResponse> authenticateUser(LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userRepository.findByUsername(userDetails.getUsername()).get();
        currentUser.setConnected(true);
        userRepository.save(currentUser);
        AuthService.setCURRENTUSER(currentUser);
        AuthService.setIDCURRENTUSER(currentUser.getId());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities(), currentUser.getId()));
    }

    public ResponseEntity<ResponseMessage> registerUser(SignUpForm signUpRequest) throws IOException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByMail(signUpRequest.getMail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        // Creating user's account
        User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getMail(),
                signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getBirthDate(),
                signUpRequest.getAddress(), signUpRequest.getProfession(), signUpRequest.isProfessionnalisme(),null);
        user.setScore(0);
        user.setConnected(false);
        File resource = new ClassPathResource("user-profile.jpg").getFile();
        byte[] file = Files.readAllBytes(resource.toPath());
        user.setLogo(challengeService.compressBytes(file));
        Optional<User> recommendedBy = Optional.ofNullable(userRepository.findByUsername(signUpRequest.getRecommander())).orElse(null);
        if(recommendedBy.isPresent()==true) {
            user.setRecommander(recommendedBy.get());
            recommendedBy.get().setScore(recommendedBy.get().getScore()+1);
        }

        Set strRoles = signUpRequest.getRole();
        Set roles = new HashSet<>();

        strRoles.forEach(role -> {
            if (role.equals("ADMINCOMUNITY")) {

                Role adminRole = roleRepository.findByName(RoleName.ADMINCOMUNITY)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(adminRole);
            } else {
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
