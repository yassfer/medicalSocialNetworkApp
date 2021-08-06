package com.health.talan.services;

import java.io.IOException;
import java.util.List;

import com.health.talan.entities.Challenge;


public interface IChallangeService {

	public Long addChallenge (Long id, Challenge challange);
	public void deleteChallange (Long id);
	public void updateChallange (Long id, Challenge challange);
	public List<Challenge> getAll() throws IOException;
	public Challenge displayById(Long id);
}
