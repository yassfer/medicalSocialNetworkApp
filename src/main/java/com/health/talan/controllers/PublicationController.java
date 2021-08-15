package com.health.talan.controllers;

import com.health.talan.entities.PieceJoint;
import com.health.talan.entities.Publication;
import com.health.talan.services.ChallengeService;
import com.health.talan.services.PublicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("api/publication")
public class PublicationController {

	private final PublicationServiceImpl publicationServiceImpl;
	private final ChallengeService challengeService;

	@Autowired
	public PublicationController(PublicationServiceImpl publicationServiceImpl, ChallengeService challengeService) {

		this.publicationServiceImpl = publicationServiceImpl;
		this.challengeService = challengeService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Publication>> getAllPublications() {

		List<Publication> publications = publicationServiceImpl.getAllPublications();
		return new ResponseEntity<>(publications, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPublicationById(@PathVariable("id") Long id) {
		Optional<Publication> publication = publicationServiceImpl.getPublicationById(id);
		if (publication.isPresent()) {
			return new ResponseEntity<>(publication, HttpStatus.OK);
		}

		return new ResponseEntity<>("publication not found", HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getAllPublicationsByUserId(@PathVariable("userId") Long userId) {

		Optional<List<Publication>> publications = publicationServiceImpl.getPublicationByUserId(userId);
		if (publications.isPresent()) {
			for (Publication publication : publications.get()) {
				for (PieceJoint pieceJoint : publication.getPieceJoints()) {
					pieceJoint.setData(challengeService.decompressBytes(pieceJoint.getData()));
				}
			}
			return new ResponseEntity<>(publications, HttpStatus.OK);
		}
		return new ResponseEntity<>("that user have no publications", HttpStatus.OK);
	}

	@PostMapping("/{userId}")
	public ResponseEntity<Publication> publishPublication(@RequestBody Publication publication,
			@PathVariable("userId") Long userId) {

		Publication newPublication = publicationServiceImpl.publishPublication(publication, userId);
		return new ResponseEntity<>(newPublication, HttpStatus.OK);
	}

	@PatchMapping("/{id}/user/{userId}")
	public ResponseEntity<?> updatePublication(@PathVariable("id") Long id, @PathVariable("userId") Long userId,
			@RequestBody Publication publication) {

		Optional<Publication> pub = publicationServiceImpl.getPublicationById(id);
		if (pub.isPresent()) {
			publicationServiceImpl.publishPublication(publication, userId);
			return new ResponseEntity<>(pub, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Publication doesn't exist", HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePublication(@PathVariable("id") Long id) {

		String message = publicationServiceImpl.deletePublication(id);
		if (message.equals("Publication Deleted")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
}
