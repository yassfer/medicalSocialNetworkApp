package com.health.talan.services.serviceInterfaces;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.health.talan.entities.Community;
import com.health.talan.entities.User;

public interface CommunityService {
	List<Community> GetAllCommunities() throws IOException;
	Community getCommunityById(@PathVariable Long idCommunity) throws IOException;
	void deleteCommunity(@PathVariable Long idCommunity);
	Long UpdateCommunity(Long idCommunity,Community community);
	Long AddUserToCommunity(@PathVariable Long idUser, @PathVariable Long idCommunity);
	public Long CreateCommunity(@PathVariable Long id ,@RequestBody Community Community) ;
	List<Community> getByAdmin(String idAdmin) throws IOException;
	void uplaodImage(String id, MultipartFile file) throws IOException;
	public Long RemoveUserToCommunity( Long idUser, Long idCommunity);
	List<Community> getfollowedCommunity(Long iduser) throws IOException;
	public List<Community> getUnfollowedCommunity(Long iduser) throws IOException;
	
	

}
