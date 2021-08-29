package com.health.talan.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.health.talan.entities.Fitbotty;
import com.health.talan.services.FitbottyService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/workoutBot")
public class FitbottyController {

    @Autowired
    FitbottyService fitbottyService;

    @GetMapping("/video/{id}")
    public Fitbotty getById(@PathVariable Long id) {
        return fitbottyService.getById(id);
    }

	/*@GetMapping("/videoP/{id}")
	public Fitbotty getById(@PathVariable Long id) {
		return fitbottyService.getById(id);
	}*/

    @PostMapping("/add")
    public void addFitBitty(@RequestBody Fitbotty fitbotty) {
        fitbottyService.addFitBitty(fitbotty);
    }

    @PutMapping("/upload/{id}")
    public void saveVideo(@PathVariable Long id, @RequestBody MultipartFile file) throws IOException {
        fitbottyService.saveVideo(id, file);
    }

    @GetMapping("/getFit/{title}")
    public Fitbotty getByTitle(@PathVariable String title) {
        return fitbottyService.getByTitle(title);
    }
}
