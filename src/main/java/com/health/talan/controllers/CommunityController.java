package com.health.talan.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.health.talan.services.CommunityService;

import javassist.NotFoundException;

import com.health.talan.entities.*;
import com.health.talan.exception.CommunityNotFoundException;
import com.health.talan.repositories.*;

import java.util.*;

@RestController
@RequestMapping
public class CommunityController {
	// display list of communities
	@Autowired
	//private CommunityRepository CommunityRepository; 
	private CommunityService CommunityService;
	
	@GetMapping(path = "/Communities")
	 
	public List<Community> ViewCommunitiesPage() {
		return CommunityService.GetAllCommunities();
	
	}
	
	//GET by ID
	@GetMapping(path = "/Communities/{id}")
	public Optional<Community> getCommunityById(@PathVariable Long id){
		return 	CommunityService.getCommunityById(id);

	}
	@DeleteMapping("/Communities/{idCommunity}")
	public ResponseEntity<Map<String,Boolean>> deleteCommunity(@PathVariable Long idCommunity){
		return CommunityService.deleteCommunity(idCommunity);
	}
	
	//UPDATE
		@PutMapping("/Communities/{idCommunity}")
		public void UpdateCommunity(@PathVariable Long idCommunity,@RequestBody Community community) {
				CommunityService.UpdateCommunity(idCommunity,community);
				System.out.println("Community modifié !"); }
		
		
	
		// Add user in community
		@PutMapping("/Communities/{idUser}/{idCommunity}")
		public void AddUserToCommunity(@PathVariable Long idUser, @PathVariable Long idCommunity) {
			
				CommunityService.AddUserToCommunity( idUser,idCommunity);
				System.out.println("User ajouté !"); }
		
		
		//Add community
		@PostMapping("/Communities")
		public void CreateCommunity(@RequestBody Community Community) {
				CommunityService.CreateCommunity(Community);
				System.out.println("Community créée !");

		}		
		}

		
	
	
	
	


