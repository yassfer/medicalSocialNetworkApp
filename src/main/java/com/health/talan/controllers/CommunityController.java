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
import org.springframework.http.MediaType;

import com.health.talan.services.serviceInterfaces.CommunityService;


import com.health.talan.entities.*;
import com.health.talan.repositories.*;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;

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

	//@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/getAll")
	public List<Community> ViewCommunitiesPage() throws IOException {
		return CommunityService.GetAllCommunities();

	}

	// GET by ID
	//@PreAuthorize("hasAuthority('USER')")
	@GetMapping(path = "/getById/{id}")
	public Community getCommunityById(@PathVariable Long id) throws IOException {
		return CommunityService.getCommunityById(id);

	}

	//@PreAuthorize("hasAuthority('USER')")
	@GetMapping(path = "/getFollowedCommunities/{id}")
	List<Community> getfollowedCommunity(@PathVariable ("id") Long id) throws IOException {
		return CommunityService.getfollowedCommunity(id);
	}

	//@PreAuthorize("hasAuthority('USER')")
	@GetMapping(path = "/getUnFollowedCommunities/{id}")
	List<Community> getUnfollowedCommunity(@PathVariable ("id") Long id) throws IOException {
		return CommunityService.getUnfollowedCommunity(id);
	}

	//@PreAuthorize("hasAuthority('USER')")
	@DeleteMapping("/delete/{idCommunity}")
	public void deleteCommunity(@PathVariable Long idCommunity) {
		CommunityService.deleteCommunity(idCommunity);
	}

	// UPDATE
	//@PreAuthorize("hasAuthority('USER')")
	@PutMapping("/update/{idCommunity}")
	public Long UpdateCommunity(@PathVariable Long idCommunity, @RequestBody Community community) {
		return CommunityService.UpdateCommunity(idCommunity, community);
	}

	// Add user in community
	//@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/Adduser/{idCommunity}/{idUser}")
	public Long AddUserToCommunity(@PathVariable Long idCommunity, @PathVariable Long idUser) {

		return CommunityService.AddUserToCommunity(idCommunity,idUser);
		// System.out.println("User ajouté !");
	}

	// Remove user from community
	//@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/Removeuser/{idCommunity}/{idUser}")
	public Long RemoveUsertoCommunity(@PathVariable Long idCommunity, @PathVariable Long idUser) {

		return CommunityService.RemoveUserToCommunity(idCommunity,idUser);
	}
	// System.out.println("User ajouté !"); }



	//@PreAuthorize("hasAuthority('USER')")
	@PostMapping(value="Create/{idUser}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public Community CreateCommunity(@PathVariable Long idUser, @RequestParam("imageFile") MultipartFile file) throws Exception {
		System.out.println(file);
		return CommunityService.addCommunityWithPiece(idUser, file);
		// System.out.println("Community créée !");

	}


	//@PreAuthorize("hasAuthority('USER')")
	@PutMapping("save/{id}")
	public void saveCommunity(@PathVariable("id") Long id, @RequestBody Community Community) {
		CommunityService.saveCommunity(id, Community);
	}

	//@PreAuthorize("hasAuthority('USER')")
	@GetMapping("getByAdmin/{id}")
	public List<Community> getByAdmin(@PathVariable ("id") Long id) throws IOException {
		System.out.println(id);
		return CommunityService.getByAdmin(id);
	}

	// upload image
	@PreAuthorize("hasAuthority('USER')")
	@PutMapping(value = "/uploadImage/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public void uplaodImage(@PathVariable("id") String id, @RequestParam("imageFile") MultipartFile file)
			throws IOException {
		CommunityService.uplaodImage(id, file);
	}

}
