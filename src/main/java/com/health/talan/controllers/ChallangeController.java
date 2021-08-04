package com.health.talan.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.health.talan.entities.Challenge;
import com.health.talan.repositories.ChallangeRepository;
import com.health.talan.repositories.UserRepository;
import com.health.talan.services.ChallangeService;


@RestController
@RequestMapping("challange/")
public class ChallangeController {

	@Autowired
	ChallangeService challangeService;
	@Autowired
	ChallangeRepository challangeRepository;
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("add/{id}")
	public void addChallenge (@PathVariable Long id,@RequestBody Challenge challange) {
		challangeService.addChallenge(id, challange);
	};
	
	@PutMapping(value = "sendFile/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public void addFile(@PathVariable("id") Long id, @RequestPart("image") MultipartFile file)
			throws Exception {
		challangeService.addFile(id, file);
	}

	@DeleteMapping("delete/{id}")
	public void deleteChallange (@PathVariable Long id) {
		challangeService.deleteChallange(id);
	}
	
	@PutMapping("update/{id}")
	public void updateChallange (@PathVariable Long id,@RequestBody Challenge challange) {
		challangeService.updateChallange(id, challange);
	}
	
	@GetMapping("getAll")
	public List<Challenge> displayAll(){
		return challangeService.displayAll();
	}
	
	@GetMapping("getById/{id}")
	public Challenge displayById(@PathVariable Long id) {
		return challangeService.displayById(id);
	}
}
