package com.health.talan.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.health.talan.entities.Fitbotty;
import com.health.talan.repositories.FitbottyRepository;

@Service
public class FitbottyService {

    @Autowired
    FitbottyRepository fitbottyRepository;
    @Autowired
    ChallengeService challengeService;

    public Fitbotty getById(Long id) {
        return fitbottyRepository.findById(id).get();
    }

    public void addFitBitty(Fitbotty fitbotty) {
        Fitbotty newFit = new Fitbotty();
        newFit.setTitle(fitbotty.getTitle());
        fitbottyRepository.save(newFit);
    }

    public void saveVideo(Long id, MultipartFile file) throws IOException {
        Fitbotty fitbotty = fitbottyRepository.findById(id).get();
        fitbotty.setVideo(challengeService.compressBytes(file.getBytes()));
        fitbottyRepository.save(fitbotty);
    }

    public Fitbotty getByTitle(String title) {
        Fitbotty fitbotty = fitbottyRepository.findByTitle(title);
        fitbotty.setVideo(challengeService.decompressBytes(fitbotty.getVideo()));
        return fitbotty;
    }
}
