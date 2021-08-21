package com.health.talan.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("add")
	public Long addChallenge(@RequestBody Challenge challange) {
		return challangeService.addChallenge(challange);
	}

	@PreAuthorize("hasAuthority('USER')")
	@PutMapping(value = "sendFile/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public void uplaodImage(@PathVariable("id") String id, @RequestParam("imageFile") MultipartFile file)
			throws Exception {
		challangeService.uplaodImage(id, file);
	}

	@PreAuthorize("hasAuthority('USER')")
	@DeleteMapping("delete/{id}")
	public void deleteChallange(@PathVariable Long id) {
		challangeService.deleteChallange(id);
	}

	@PreAuthorize("hasAuthority('USER')")
	@PutMapping("update/{id}")
	public void updateChallange(@PathVariable Long id, @RequestBody Challenge challange) {
		challangeService.updateChallange(id, challange);
	}

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("getAll")
	public List<Challenge> displayAll() throws IOException {
		return challangeService.getAll();
	}

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("getMyChallenges")
	public List<Challenge> displayByAdmin() throws IOException {
		return challangeService.getByAdmin();
	}

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("getById/{id}")
	public Challenge displayById(@PathVariable Long id) {
		return challangeService.displayById(id);
	}
}
