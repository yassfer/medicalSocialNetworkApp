package com.health.talan.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.health.talan.config.FileUploadUtil;
import com.health.talan.entities.Challenge;
import com.health.talan.entities.User;
import com.health.talan.repositories.ChallangeRepository;
import com.health.talan.repositories.UserRepository;

@Service
public class ChallangeService implements IChallangeService{

	@Autowired
	ChallangeRepository challangeRepository;

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addChallenge(Long id, Challenge challange) {
		User user = userRepository.findById(id).get();
		challange.setAdminChallenge(user);
		challangeRepository.save(challange);
	}

	public void addFile(Long id, MultipartFile file)
			throws Exception {

		Challenge challange = challangeRepository.findById(id).get();
		
		String fileNameRepo = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir = "uploadYass/";
		System.out.println("image name =" + fileNameRepo);
		try {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploadYass/")
					.path(fileNameRepo).toUriString();
			System.out.println("file url =====>" + fileDownloadUri);
			challange.setPieceJoint(fileDownloadUri.getBytes());
			challangeRepository.save(challange);
			FileUploadUtil.saveFile(uploadDir, fileNameRepo, file);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void deleteChallange(Long id) {
		challangeRepository.deleteChallengeById(id);
	}

	@Override
	public void updateChallange(Long id, Challenge challange) {
		Challenge oldChallange = challangeRepository.findById(id).get();
		oldChallange.setNom(challange.getNom());
		oldChallange.setObjectif(challange.getObjectif());
		//oldChallange.setPieceJoint(challange.getPieceJoint());
		challangeRepository.save(oldChallange);
	}

	@Override
	public List<Challenge> displayAll() {
		return (List<Challenge>) challangeRepository.findAll();
	}

	@Override
	public Challenge displayById(Long id) {
		return challangeRepository.findById(id).get();
	}
	
}
