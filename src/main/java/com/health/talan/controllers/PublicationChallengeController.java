package com.health.talan.controllers;

import com.health.talan.entities.PieceJoint;
import com.health.talan.entities.Publication;
import com.health.talan.entities.PublicationChallenge;
import com.health.talan.services.ChallengeService;
import com.health.talan.services.PublicationChallengeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("api/publicationChallenge")
public class PublicationChallengeController {

	private final PublicationChallengeServiceImpl publicationChallengeServiceImpl;
	private final ChallengeService challengeService;

	@Autowired
	public PublicationChallengeController(ChallengeService challengeService, PublicationChallengeServiceImpl publicationChallengeServiceImpl) {

		this.publicationChallengeServiceImpl = publicationChallengeServiceImpl;
		this.challengeService = challengeService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<PublicationChallenge>> getAllChallengePublications() {

		List<PublicationChallenge> ChallengePublications = publicationChallengeServiceImpl
				.getAllChallengePublications();
		return new ResponseEntity<>(ChallengePublications, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<PublicationChallenge>> getPublicationChallengeById(@PathVariable("id") Long id) {
		Optional<PublicationChallenge> ChallengePublication = publicationChallengeServiceImpl
				.getPublicationChallengeById(id);
		if (ChallengePublication.isPresent()) {
			return new ResponseEntity<>(ChallengePublication, HttpStatus.OK);
		}
		throw new IllegalStateException("ChallengePublication not found");
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getAllPublicationsByUserId(@PathVariable("userId") Long userId) {

		Optional<List<PublicationChallenge>> Challengepublications = publicationChallengeServiceImpl
				.getPublicationChallengeByUserId(userId);
		if (Challengepublications.isPresent()) {
			return new ResponseEntity<>(Challengepublications, HttpStatus.OK);
		}
		return new ResponseEntity<>("that user have no challenges", HttpStatus.OK);
	}

	@GetMapping("/challenge/{challengeId}")
	public ResponseEntity<?> getAllPublicationsByChallengeId(@PathVariable("challengeId") Long challengeId) {

		Optional<List<PublicationChallenge>> Challengepublications = publicationChallengeServiceImpl
				.getPublicationChallengeByChallengeId(challengeId);
		if (Challengepublications.isPresent()) {
			return new ResponseEntity<>(Challengepublications, HttpStatus.OK);
		}
		return new ResponseEntity<>("that challenge have no challengePublications", HttpStatus.OK);
	}

	@GetMapping("/approuved/challenge/{challengeId}")
	public ResponseEntity<?> getAllApprouvedChallengePublications(@PathVariable("challengeId") Long challengeId) {

		Optional<List<PublicationChallenge>> Challengepublications = publicationChallengeServiceImpl
				.getPublicationChallengeByApprouved(true, challengeId);
		if (Challengepublications.isPresent()) {
			for (PublicationChallenge publication : Challengepublications.get()) {
				for (PieceJoint pieceJoint : publication.getPieceJoints()) {
					pieceJoint.setData(challengeService.decompressBytes(pieceJoint.getData()));
				}
			}
			return new ResponseEntity<>(Challengepublications, HttpStatus.OK);
		}
		return new ResponseEntity<>("that challenge have no challengePublications", HttpStatus.OK);
	}

	@PostMapping("/{challengeId}/{userId}")
	public ResponseEntity<PublicationChallenge> AddPublicationChallenge(
			@RequestBody PublicationChallenge publicationChallenge, @PathVariable("userId") Long userId,
			@PathVariable("challengeId") Long challengeId) {

		PublicationChallenge newPublicationChallenge = publicationChallengeServiceImpl
				.addPublicationChallenge(publicationChallenge, challengeId, userId);
		return new ResponseEntity<>(newPublicationChallenge, HttpStatus.OK);
	}

	@GetMapping("/approuve/{challengePublicationId}/{userId}")
	public ResponseEntity<?> approuvePublicationChallenge(
			@PathVariable("challengePublicationId") Long challengePublicationId, @PathVariable("userId") Long userId) {

		Optional<PublicationChallenge> pub = publicationChallengeServiceImpl
				.getPublicationChallengeById(challengePublicationId);

		if (pub.isPresent()) {
			if (userId.equals(pub.get().getUser())) {
				return new ResponseEntity<>("Only the owner of the challenge can approuve", HttpStatus.OK);
			} else {
				pub.get().setApprouved(true);
				if(pub.get().getChallenge().getAdminChallenge().getType()){
					pub.get().getUser().setScore(pub.get().getUser().getScore()+1);
				}

				publicationChallengeServiceImpl.updatePublicationChallenge(pub.get());
				return new ResponseEntity<>(pub, HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<>("Challenge publication doesn't exist", HttpStatus.BAD_REQUEST);
		}

	}

	@PatchMapping("/{id}/user/{userId}/challenge/{challengeId}")
	public ResponseEntity<?> updatePublicationChallenge(@PathVariable("id") Long id,
			@PathVariable("userId") Long userId, @PathVariable("challengeId") Long challengeId,
			@RequestBody PublicationChallenge publicationChallenge) {

		Optional<PublicationChallenge> pub = publicationChallengeServiceImpl.getPublicationChallengeById(id);
		if (pub.isPresent()) {
			publicationChallengeServiceImpl.addPublicationChallenge(publicationChallenge, challengeId, userId);
			return new ResponseEntity<>(pub, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Challenge doesn't exist", HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/user/{userId}")
	public ResponseEntity<?> deleteChallengePublicationsByUserId(@PathVariable("userId") Long userId) {

		String message = publicationChallengeServiceImpl.deleteChallengePublicationsByUserId(userId);
		if (message.equals("Challenge Publication Deleted")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/challenge/{challengeId}")
	public ResponseEntity<?> deleteChallengePublicationsByChallengeId(@PathVariable("challengeId") Long challengeId) {

		String message = publicationChallengeServiceImpl.deleteChallengePublicationsByChallengeId(challengeId);
		if (message.equals("Challenge Publication Deleted")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/user/{userId}/challenge/{challengeId}")
	public ResponseEntity<?> deletePublicationChallengeByUserAndChallenge(@PathVariable("userId") Long userId,
			@PathVariable("challengeId") Long challengeId) {

		String message = publicationChallengeServiceImpl.deletePublicationChallengeByChallengeAndUser(userId,
				challengeId);
		if (message.equals("Challenge Publication Deleted")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	// refuse or simple delete
	@DeleteMapping("/{challengePublicationId}")
	public ResponseEntity<?> deletePublicationChallenge(@PathVariable("challengePublicationId") Long id) {

		String message = publicationChallengeServiceImpl.deletePublicationChallenge(id);
		if (message.equals("Challenge Publication Deleted")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/notApprouved/{challengePublicationId}")
    public ResponseEntity<?> getPublicationChallengeByApprouved(@PathVariable("challengePublicationId") Long challengePublicationId){
        List<PublicationChallenge> pub = publicationChallengeServiceImpl.getPublicationChallengeByApprouved(false,challengePublicationId).get();
		for (PublicationChallenge publication : pub) {
			for (PieceJoint pieceJoint : publication.getPieceJoints()) {
				pieceJoint.setData(challengeService.decompressBytes(pieceJoint.getData()));
			}
		}
        return new ResponseEntity<>(pub, HttpStatus.OK);
    }
	
	@GetMapping("/getAllByDate")
	public List<PublicationChallenge> getAllByDate(){
		return publicationChallengeServiceImpl.getAllByDate();
	}
}
