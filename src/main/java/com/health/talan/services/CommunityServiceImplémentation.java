package com.health.talan.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.health.talan.entities.Community;
import com.health.talan.entities.User;
import com.health.talan.exception.CommunityNotFoundException;
import com.health.talan.exception.EmptyInputException;
import com.health.talan.repositories.*;

@Service
public class CommunityServiceImplémentation implements CommunityService{

	@Autowired
	private CommunityRepository CommunityRepository;
	
	@Autowired
	private UserRepository UserRepository;
	
	@Override
	public List<Community> GetAllCommunities() {
		return CommunityRepository.findAll();
		
	}
	
	//get by id
	public Optional<Community> getCommunityById(@PathVariable Long idCommunity){
		return CommunityRepository.findById(idCommunity);
		}
	
	//DELETE
		public ResponseEntity<Map<String,Boolean>> deleteCommunity(@PathVariable Long idCommunity){
			Community community=CommunityRepository.findById(idCommunity).orElseThrow(() -> new CommunityNotFoundException("Community does not exist :"+idCommunity));
			CommunityRepository.delete(community);
			Map<String,Boolean> response=new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		
		//UPDATE
		public ResponseEntity<Community> UpdateCommunity(Long idCommunity,Community community) {
			Community existingCommunity= CommunityRepository.findById(idCommunity).orElseThrow(() -> new CommunityNotFoundException("Community does not exist with id :"+idCommunity));
			existingCommunity.setAdminCom(community.getAdminCom());
			existingCommunity.setDomaine(community.getDomaine());
			existingCommunity.setImage(community.getImage());
			existingCommunity.setNom(community.getNom());
			existingCommunity.setParticipants(community.getParticipants());
			Community updatedCommunity= CommunityRepository.save(existingCommunity);
			return ResponseEntity.ok(updatedCommunity);}
	
	//Add user to community 
		public ResponseEntity<Community> AddUserToCommunity(@PathVariable Long idUser, @PathVariable Long idCommunity) {
			Community community = CommunityRepository.findById(idCommunity).get();
			Set<User> communityparticipants =community.getParticipants();			
			communityparticipants.add(UserRepository.findById(idUser).get());
			community.setParticipants(communityparticipants);
			CommunityRepository.findById(idCommunity).get().setParticipants(community.getParticipants());
			Community updatedCommunity= CommunityRepository.save(community);
			return ResponseEntity.ok(updatedCommunity);}
			 
			 //User user= UserRepository.findById(idUser).orElseThrow(() -> new CommunityNotFoundException("User does not exist :"+idUser));
			//community.getParticipants().add(user);
		
		
		//Add Community 
		public void CreateCommunity(Community Community) {
			if(Community.getId().equals(null) || Community.getDomaine().equals(null) || Community.getAdminCom().equals(null) || Community.getNom().equals(null)) {
				throw new EmptyInputException("601");}
			CommunityRepository.save(Community);
		}
		}
			
	


