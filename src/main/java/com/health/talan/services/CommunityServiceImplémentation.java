package com.health.talan.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.health.talan.services.serviceInterfaces.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.health.talan.entities.Community;
import com.health.talan.entities.User;
import com.health.talan.Exception.CommunityNotFoundException;
import com.health.talan.repositories.*;

@Service
public class CommunityServiceImplémentation implements CommunityService {

	@Autowired
	private CommunityRepository CommunityRepository;

	@Autowired
	private UserRepository UserRepository;


	@Autowired
	private AuthService authService;


	@Autowired
	public CommunityServiceImplémentation(CommunityRepository CommunityRepository, UserRepository userRepository,
										  AuthService authService) {
		this.CommunityRepository = CommunityRepository;
		this.UserRepository = userRepository;
		this.authService = authService;
	}

	@Override
	public List<Community> GetAllCommunities() throws IOException {
		List<Community> Communities = (List<Community>) CommunityRepository.findAll();
		for (Community Community : Communities) {
			Community.setImage(decompressBytes(Community.getImage()));

		}
		return Communities;
	}

	public Community addCommunityWithPiece(Long idAdmin, MultipartFile file) throws IOException {
		Community Community = new Community();
		User user = UserRepository.findById(idAdmin).get();
		Community.setImage(compressBytes(file.getBytes()));
		Community.setAdminCom(user);
		CommunityRepository.save(Community);
		return Community;
	}



	// get community by idadmin
	public List<Community> getByAdmin(Long id) throws IOException {
		User user = UserRepository.findById(id).get();
		List<Community> Communities = (List<Community>) CommunityRepository.findByAdminCommunity(user);

		for (Community Community : Communities) {
			Community.setImage(decompressBytes(Community.getImage()));
		}
		return Communities;
	}

	// get by id
	public Community getCommunityById(Long idCommunity) throws IOException {
		Community Community = CommunityRepository.findById(idCommunity).get();
		Community.setImage(decompressBytes(Community.getImage()));
		return Community;
	}








	// get followed communties / user
	public List<Community> getfollowedCommunity(Long id) throws IOException {
		List<Community> followed = new ArrayList<>();
		User user = UserRepository.findById(id).get();
		Set<Community> communitiesparticipates = user.getCommunitiesParticipate();
		for (Community community : communitiesparticipates) {
			for (User participant : community.getParticipants()) {
				if (user.equals(participant)&& !(community.getAdminCom().equals(user))) {
					community.setImage(decompressBytes(community.getImage()));
					followed.add(community);
				}
			}
		}
		return followed;
	}

	public List<Community> getUnfollowedCommunity(Long id) throws IOException {
		List<Community> unfollowed = new ArrayList<>();
		List<Community> Communities = (List<Community>) CommunityRepository.findAll();
		User user = UserRepository.findById(id).get();;
		Set<Community> communitiesparticipates = user.getCommunitiesParticipate();

		for (Community community : Communities) {
			if (!(communitiesparticipates.contains(community)) && !(community.getAdminCom().equals(user))) {
				community.setImage(decompressBytes(community.getImage()));
				unfollowed.add(community);
			}
		}
		System.out.println("nb::::: " + unfollowed.size());

		return unfollowed;
	}

	// DELETE
	public void deleteCommunity(Long idCommunity) {
		CommunityRepository.deleteCommunityById(idCommunity);
	}

	// UPDATE
	public Long UpdateCommunity(Long idCommunity, Community community) {
		Community existingCommunity = CommunityRepository.findById(idCommunity)
				.orElseThrow(() -> new CommunityNotFoundException("Community does not exist with id :" + idCommunity));
		existingCommunity.setDomaine(community.getDomaine());
		existingCommunity.setNom(community.getNom());
		existingCommunity.setDescription(community.getDescription());
		existingCommunity.setType(community.getType());
		Community updatedCommunity = CommunityRepository.save(existingCommunity);
		return updatedCommunity.getId();
	}

	// Add user to community
	public Long AddUserToCommunity(Long idCommunity, Long idUser) {
		Community community = CommunityRepository.findById(idCommunity).get();
		Set<User> communityparticipants = community.getParticipants();
		System.out.println("*********");
		User user =  UserRepository.findById(idUser).get();
		communityparticipants.add(user);
		community.setParticipants(communityparticipants);
		Community updatedCommunity = CommunityRepository.save(community);
		return updatedCommunity.getId();
	}


	// Remove user from community
	public Long RemoveUserToCommunity(Long idCommunity, Long idUser) {
		Community community = CommunityRepository.findById(idCommunity).get();
		Set<User> communityparticipants = community.getParticipants();
		User user = UserRepository.findById(idUser).get();
		communityparticipants.remove(user);
		community.setParticipants(communityparticipants);
		// CommunityRepository.findById(idCommunity).get().setParticipants(community.getParticipants());
		Community updatedCommunity = CommunityRepository.save(community);
		return updatedCommunity.getId();
	}

	// Add Community
	/*public Long CreateCommunity(Long id, Community Community) {
		User user = UserRepository.findById(id).get();
		Community.setAdminCom(user);
		CommunityRepository.save(Community);
		return Community.getId();
	}*/

	// Save Community
	public void saveCommunity(Long id, Community Community) {
		Community CommunityN = CommunityRepository.findById(id).get();
		CommunityN.setNom(Community.getNom());
		CommunityN.setDescription(Community.getDescription());
		CommunityN.setDomaine(Community.getDomaine());
		CommunityN.setType(Community.getType());
		CommunityRepository.save(CommunityN);
	}

	// upload image
	public void uplaodImage(String id, MultipartFile file) throws IOException {
		long idcommunity = Long.parseLong(id);
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		Community Community = CommunityRepository.findById(idcommunity).get();
		Community.setImage(compressBytes(file.getBytes()));
		CommunityRepository.save(Community);
	}

	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

}
