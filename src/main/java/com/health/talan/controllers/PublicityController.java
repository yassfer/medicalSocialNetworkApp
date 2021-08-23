package com.health.talan.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.health.talan.entities.Publicity;
import com.health.talan.services.PublicityService;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("publicity/")
public class PublicityController {

    @Autowired
    private PublicityService publicityService;

    @PostMapping("add/{id}")
    public Long addPublicity(@PathVariable Long id, @RequestBody Publicity publicity) {
        return this.publicityService.addPublicity(id, publicity);
    };

    @DeleteMapping("delete/{id}")
    public void deletePublicity(@PathVariable Long id) {
        this.publicityService.deletePublicity(id);
    }

    @PutMapping("update/{id}")
    public void updatePublicity(@PathVariable Long id, @RequestBody Publicity publicity) {
        this.publicityService.updatePublicity(id, publicity);
    }

    @PutMapping(value = "sendFile/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public void uplaodImage(@PathVariable("id") String id, @RequestParam("imageFile") MultipartFile file)
            throws Exception {
        publicityService.uplaodImage(id, file);
    }

    @GetMapping("getAll")
    public List<Publicity> displayAll() throws IOException {
        return publicityService.getAll();
    }

    @GetMapping("getById/{id}")
    public Publicity displayById(@PathVariable Long id) {
        return publicityService.displayById(id);
    }

}
