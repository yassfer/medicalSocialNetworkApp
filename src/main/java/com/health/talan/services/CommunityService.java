package com.health.talan.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.health.talan.entities.Community;

public interface CommunityService {
	List<Community> GetAllCommunities();
	Optional<Community> getCommunityById(@PathVariable Long idCommunity);
	ResponseEntity<Map<String,Boolean>> deleteCommunity(@PathVariable Long idCommunity);
	ResponseEntity<Community> UpdateCommunity(Long idCommunity,Community community);
	ResponseEntity<Community> AddUserToCommunity(@PathVariable Long idUser, @PathVariable Long idCommunity);
	void CreateCommunity(Community Community);

}
