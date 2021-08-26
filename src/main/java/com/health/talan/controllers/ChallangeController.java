package com.health.talan.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.health.talan.entities.User;
import com.health.talan.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.health.talan.entities.Challenge;
import com.health.talan.repositories.ChallangeRepository;
import com.health.talan.repositories.UserRepository;
import com.health.talan.services.ChallengeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("challange/")
public class ChallangeController {

	@Autowired
	ChallengeService challangeService;
	@Autowired
	ChallangeRepository challangeRepository;
	@Autowired
	UserRepository userRepository;

	//New
	//@PreAuthorize("hasAuthority('USER')")
	@PostMapping(value = "addChallenge/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public Challenge uplaodImage(@PathVariable("id") Long id, @RequestParam("imageFile") MultipartFile file)
			throws Exception {
		return challangeService.uplaodImage(id, file);

	}

	@PutMapping("save/{id}")
	public void saveChallenge(@PathVariable Long id, @RequestBody Challenge challange) {
		challangeService.saveChallenge(id, challange);
	}

	//FIn new
	//@PreAuthorize("hasAuthority('USER')")
	@DeleteMapping("delete/{id}")
	public void deleteChallange(@PathVariable Long id) {
		challangeService.deleteChallange(id);
	}

	//@PreAuthorize("hasAuthority('USER')")
	@GetMapping("getAll")
	public List<Challenge> displayAll() throws IOException {
		return challangeService.getAll();
	}

	//@PreAuthorize("hasAuthority('USER')")
	@GetMapping("getMyChallenges/{id}")
	public List<Challenge> displayByAdmin(@PathVariable Long id) throws IOException {
		return challangeService.getByAdmin(id);
	}

	//@PreAuthorize("hasAuthority('USER')")
	@GetMapping("getById/{id}")
	public Challenge displayById(@PathVariable Long id) {
		return challangeService.displayById(id);
	}
	
	@GetMapping("getAllByDate")
	public List<Challenge> getAllByDate() throws IOException{
		return challangeService.getAllByDate();
	}
}
