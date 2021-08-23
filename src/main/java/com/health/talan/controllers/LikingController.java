package com.health.talan.controllers;

import com.health.talan.entities.Liking;
import com.health.talan.services.LikingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("api/like")
public class LikingController {

	private final LikingServiceImpl likingServiceImpl;

	@Autowired
	public LikingController(LikingServiceImpl likingServiceImpl) {

		this.likingServiceImpl = likingServiceImpl;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Liking>> getAllLikes() {

		List<Liking> likes = likingServiceImpl.getAllLikes();
		return new ResponseEntity<>(likes, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getLikeById(@PathVariable("id") Long id) {
		Optional<Liking> like = likingServiceImpl.getLikingById(id);
		if (like.isPresent()) {
			return new ResponseEntity<>(like, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("There's no like with that id", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/user/{userId}/publication/{publicationId}")
	public ResponseEntity<?> getLikeByUserIdAndPublicationId(@PathVariable("userId") Long user_Id,
			@PathVariable("publicationId") Long publication_Id) {

		Optional<Liking> like = likingServiceImpl.findLikeByUserAndPublication(user_Id, publication_Id);
		if (like.isPresent()) {
			return new ResponseEntity<>(like, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("There's no like with those userId and publicationId", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{userId}/{pubId}")
	public ResponseEntity<Liking> addLike(@PathVariable("userId") Long userId, @PathVariable("pubId") Long pubId) {

		Liking newLike = likingServiceImpl.saveLike(userId, pubId);
		return new ResponseEntity<>(newLike, HttpStatus.OK);
	}

	//Create Like For Publication Challenge
	@GetMapping("likeChallenge/{userId}/{pubId}")
	public ResponseEntity<Liking> addLikePubChallenge(@PathVariable("userId") Long userId, @PathVariable("pubId") Long pubId) {

		Liking newLike = likingServiceImpl.saveLikeChallenge(userId, pubId);
		return new ResponseEntity<>(newLike, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{userId}/publication/{publicationId}")
	public ResponseEntity<?> deleteLikeByUserAndPublication(@PathVariable("userId") Long userId,
			@PathVariable("publicationId") Long publicationId) {

		String message = likingServiceImpl.deleteLikeByUserAndPublication(userId, publicationId);
		if (message.equals("like Deleted")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.CONFLICT);
		}
	}



	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLike(@PathVariable("id") Long id) {

		String message = likingServiceImpl.deleteLiking(id);
		if (message.equals("like Deleted")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.CONFLICT);
		}
	}
}
