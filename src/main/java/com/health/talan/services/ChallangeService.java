package com.health.talan.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.health.talan.entities.Challenge;
import com.health.talan.entities.User;
import com.health.talan.repositories.ChallangeRepository;
import com.health.talan.repositories.UserRepository;

@Service
public class ChallangeService implements IChallangeService {

	@Autowired
	ChallangeRepository challangeRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public Long addChallenge(Long id, Challenge challange) {
		User user = userRepository.findById(id).get();
		challange.setAdminChallenge(user);
		challangeRepository.save(challange);
		return challange.getId();
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
		challangeRepository.save(oldChallange);
	}

	@Override
	public Challenge displayById(Long id) {
		return challangeRepository.findById(id).get();
	}

	// uplaodImage
	public void uplaodImage(String id, MultipartFile file) throws IOException {
		long idChallenge = Long.parseLong(id);
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		Challenge challange = challangeRepository.findById(idChallenge).get();
		challange.setPieceJoint(compressBytes(file.getBytes()));
		challangeRepository.save(challange);
	}

	// Display all
	@Override
	public List<Challenge> getAll() throws IOException {
		List<Challenge> challenges = (List<Challenge>) challangeRepository.findAll();
		for (Challenge challenge : challenges) {
			challenge.setPieceJoint(decompressBytes(challenge.getPieceJoint()));
		}
		return challenges;
	}

	// Display By Admin Challenge
	public List<Challenge> getByAdmin(String idAdmin) throws IOException {
		long id = Long.parseLong(idAdmin);
		User user = userRepository.findById(id).get();
		List<Challenge> challenges = (List<Challenge>) challangeRepository.findByAdminChallenge(user);
		for (Challenge challenge : challenges) {
			challenge.setPieceJoint(decompressBytes(challenge.getPieceJoint()));
		}
		return challenges;
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
