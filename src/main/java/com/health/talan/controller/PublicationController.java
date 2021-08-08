package com.health.talan.controller;

import com.health.talan.entities.Publication;
import com.health.talan.service.PublicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/publication")
public class PublicationController {

    private final PublicationServiceImpl publicationServiceImpl;


    @Autowired
    public PublicationController(PublicationServiceImpl publicationServiceImpl) {

        this.publicationServiceImpl = publicationServiceImpl;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Publication>> getAllPublications() {

        List<Publication> publications = publicationServiceImpl.getAllPublications();
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Publication>> getPublicationById(@PathVariable("id") Long id) {
        Optional<Publication> publication = publicationServiceImpl.getPublicationById(id);
        if (publication.isPresent()) {
            return new ResponseEntity<>(publication, HttpStatus.OK);
        }
        throw new IllegalStateException("publication not found");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllPublicationsByUserId(@PathVariable("userId") Long userId) {

        Optional<List<Publication>> publications = publicationServiceImpl.getPublicationByUserId(userId);
        if (publications.isPresent()) {
            return new ResponseEntity<>(publications, HttpStatus.OK);
        }
        return new ResponseEntity<>("that user have no publications", HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Publication> publishPublication(@RequestBody Publication publication) {

        Publication newPublication = publicationServiceImpl.publishPublication(publication);
        return new ResponseEntity<>(newPublication, HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePublication(@PathVariable("id") Long id, @RequestBody Publication publication) {

        Optional<Publication> pub = publicationServiceImpl.getPublicationById(id);
        if (pub.isPresent()) {
            publicationServiceImpl.publishPublication(publication);
            return new ResponseEntity<>(pub, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Publication doesn't exist", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublication(@PathVariable("id") Long id) {

        String message = publicationServiceImpl.deletePublication(id);
        if (message.equals("Deleted")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
