package com.health.talan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.health.talan.services.CommunityService;


import com.health.talan.entities.*;
import com.health.talan.repositories.*;

import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("communities/")
public class CommunityController {
	// display list of communities
	@Autowired
	private CommunityRepository CommunityRepository;
	@Autowired
	private CommunityService CommunityService;

	@GetMapping("/getAll")
	public List<Community> ViewCommunitiesPage() throws IOException {
		return CommunityService.GetAllCommunities();

	}

	// GET by ID
	@GetMapping(path = "/getById/{id}")
	public Community getCommunityById(@PathVariable Long id) throws IOException {
		return CommunityService.getCommunityById(id);

	}

	@GetMapping(path = "/getFollowedCommunities/{iduser}")
	List<Community> getfollowedCommunity(@PathVariable Long iduser) throws IOException {
		return CommunityService.getfollowedCommunity(iduser);
	}

	@GetMapping(path = "/getUnFollowedCommunities/{iduser}")
	List<Community> getUnfollowedCommunity(@PathVariable Long iduser) throws IOException {
		return CommunityService.getUnfollowedCommunity(iduser);
	}

	@DeleteMapping("/delete/{idCommunity}")
	public void deleteCommunity(@PathVariable Long idCommunity) {
		CommunityService.deleteCommunity(idCommunity);
	}

	// UPDATE
	@PutMapping("/update/{idCommunity}")
	public Long UpdateCommunity(@PathVariable Long idCommunity, @RequestBody Community community) {
		return CommunityService.UpdateCommunity(idCommunity, community);
	}

	// Add user in community
	@GetMapping("/Adduser/{idUser}/{idCommunity}")
	public Long AddUserToCommunity(@PathVariable Long idUser, @PathVariable Long idCommunity) {

		return CommunityService.AddUserToCommunity(idUser, idCommunity);
		// System.out.println("User ajouté !");
	}

	// Remove user from community
	@GetMapping("/Removeuser/{idUser}/{idCommunity}")
	public Long RemoveUsertoCommunity(@PathVariable Long idUser, @PathVariable Long idCommunity) {

		return CommunityService.RemoveUserToCommunity(idUser, idCommunity);
	}
	// System.out.println("User ajouté !"); }

	// Add community
	@PostMapping("Create/{idUser}")
	public Long CreateCommunity(@PathVariable Long idUser, @RequestBody Community Community) {
		return CommunityService.CreateCommunity(idUser, Community);
		// System.out.println("Community créée !");

	}

	@GetMapping("getByAdmin/{id}")
	public List<Community> getByAdmin(@PathVariable String id) throws IOException {
		return CommunityService.getByAdmin(id);
	}

	// upload image
	@PutMapping(value = "/uploadImage/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public void uplaodImage(@PathVariable("id") String id, @RequestParam("imageFile") MultipartFile file)
			throws IOException {
		CommunityService.uplaodImage(id, file);
	}

}
