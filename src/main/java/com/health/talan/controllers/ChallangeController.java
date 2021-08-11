package com.health.talan.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

	@PostMapping("add/{id}")
	public Long addChallenge(@PathVariable Long id, @RequestBody Challenge challange) {
		return challangeService.addChallenge(id, challange);
	};

	@PutMapping(value = "sendFile/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public void uplaodImage(@PathVariable("id") String id, @RequestParam("imageFile") MultipartFile file)
			throws Exception {
		challangeService.uplaodImage(id, file);
	}

	@DeleteMapping("delete/{id}")
	public void deleteChallange(@PathVariable Long id) {
		challangeService.deleteChallange(id);
	}

	@PutMapping("update/{id}")
	public void updateChallange(@PathVariable Long id, @RequestBody Challenge challange) {
		challangeService.updateChallange(id, challange);
	}

	@GetMapping("getAll")
	public List<Challenge> displayAll() throws IOException {
		return challangeService.getAll();
	}

	@GetMapping("getMyChallenges/{id}")
	public List<Challenge> displayByAdmin(@PathVariable String id) throws IOException {
		return challangeService.getByAdmin(id);
	}

	@GetMapping("getById/{id}")
	public Challenge displayById(@PathVariable Long id) {
		return challangeService.displayById(id);
	}
}
