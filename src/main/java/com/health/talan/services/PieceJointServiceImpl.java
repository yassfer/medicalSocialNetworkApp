package com.health.talan.services;

import com.health.talan.entities.*;
import com.health.talan.repositories.PieceJointRepo;
import com.health.talan.services.serviceInterfaces.PieceJointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class PieceJointServiceImpl implements PieceJointService {

	 	private PieceJointRepo pieceJointRepo;
	    private PublicationServiceImpl publicationServiceImpl;
		private UserServiceImpl userServiceImpl;
	    private PublicationChallengeServiceImpl publicationChallengeServiceImpl;
		private PublicationCommunityServiceImpl publicationCommunityServiceImpl;


	    @Autowired
	    public PieceJointServiceImpl(PieceJointRepo pieceJointRepo, PublicationServiceImpl publicationServiceImpl,
									 PublicationCommunityServiceImpl publicationCommunityServiceImpl,
									 PublicationChallengeServiceImpl publicationChallengeServiceImpl,
									 UserServiceImpl userServiceImpl){
	        this.pieceJointRepo = pieceJointRepo;
	        this.publicationServiceImpl = publicationServiceImpl;
	        this.publicationCommunityServiceImpl = publicationCommunityServiceImpl;
	        this.publicationChallengeServiceImpl = publicationChallengeServiceImpl;
	        this.userServiceImpl = userServiceImpl;
	    }


	    @Override
	    public PieceJoint store(MultipartFile pieceJoint, Long publicationId) throws IOException {
	        String PieceJointName = StringUtils.cleanPath(pieceJoint.getOriginalFilename());
	        PieceJoint pieceJoint1 = new PieceJoint(PieceJointName, pieceJoint.getContentType(),
	                compressBytes(pieceJoint.getBytes()), (int) pieceJoint.getSize());
	        Optional<Publication> pub = publicationServiceImpl.getPublicationById(publicationId);
	        pieceJoint1.setPublication(pub.get());

	        return pieceJointRepo.save(pieceJoint1);
	    }


	@Override
	public PieceJoint storePieceJustif(MultipartFile pieceJoint, String userMail) throws IOException {
		String PieceJointName = StringUtils.cleanPath(pieceJoint.getOriginalFilename());
		PieceJoint pieceJoint1 = new PieceJoint(PieceJointName, pieceJoint.getContentType(),
				compressBytes(pieceJoint.getBytes()), (int) pieceJoint.getSize());
		Optional<User> user = userServiceImpl.getUserByMail(userMail);
		pieceJoint1.setUser(user.get());
		user.get().setPieceJustifs(pieceJoint1);

		return pieceJointRepo.save(pieceJoint1);
	}



	    @Override
	    public PieceJoint store2(MultipartFile pieceJoint) throws IOException {
	        String PieceJointName = StringUtils.cleanPath(pieceJoint.getOriginalFilename());
	        PieceJoint pieceJoint1 = new PieceJoint(PieceJointName, pieceJoint.getContentType(),
	                compressBytes(pieceJoint.getBytes()), (int) pieceJoint.getSize());
	        return pieceJointRepo.save(pieceJoint1);
	    }

	    @Override
	    public PieceJoint updatePieceJoint(PieceJoint pieceJoint, Long pubId){
	        Optional<Publication> pub = publicationServiceImpl.getPublicationById(pubId);
	        pieceJoint.setPublication(pub.get());
	        //pieceJoint.setData(challengeService.decompressBytes(pieceJoint.getData()));
	        return pieceJointRepo.save(pieceJoint);
	    }

	    @Override
	    public PieceJoint updatePieceJointChallenge(PieceJoint pieceJoint, Long pubChallengeId){
	        Optional<PublicationChallenge> pubChallenge = publicationChallengeServiceImpl.getPublicationChallengeById(pubChallengeId);
	        pieceJoint.setPublicationChallenge(pubChallenge.get());
	        //pieceJoint.setData(challengeService.decompressBytes(pieceJoint.getData()));
	        return pieceJointRepo.save(pieceJoint);
	    }

	@Override
	public PieceJoint updatePieceJointCommunity(PieceJoint pieceJoint, Long pubCommunityId){
		Optional<PublicationCommunity> pubCommunity = publicationCommunityServiceImpl.getPublicationById(pubCommunityId);
		pieceJoint.setPublicationCommunity(pubCommunity.get());
		//pieceJoint.setData(challengeService.decompressBytes(pieceJoint.getData()));
		return pieceJointRepo.save(pieceJoint);
	}

	    @Override
	    public PieceJoint updatePieceJoint2(PieceJoint pieceJoint){
	        //pieceJoint.setData(challengeService.decompressBytes(pieceJoint.getData()));
	        return pieceJointRepo.save(pieceJoint);
	    }



	    @Override
	    public Optional<PieceJoint> getPieceJoint(Long id) {
	        Optional<PieceJoint> pieceJoint = Optional.ofNullable(pieceJointRepo.findById(id)).orElse(null);
	        //pieceJoint.get().setData(decompressBytes(pieceJoint.get().getData()));
	        return pieceJoint;
	    }


	    @Override
	    public Stream<PieceJoint> getAllPieceJoints() {
	        return pieceJointRepo.findAll().stream();
	    }

	    @Override
	    public Optional<List<PieceJoint>> getAllPieceJointsByPubId(Long publicationId){
	        return pieceJointRepo.findByPublicationId(publicationId);
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
