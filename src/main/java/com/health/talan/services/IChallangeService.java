package com.health.talan.services;

import java.util.List;

import com.health.talan.entities.Challenge;


public interface IChallangeService {

	public void addChallenge (Long id, Challenge challange);
	public void deleteChallange (Long id);
	public void updateChallange (Long id, Challenge challange);
	public List<Challenge> displayAll();
	public Challenge displayById(Long id);
}
