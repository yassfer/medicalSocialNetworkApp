package com.health.talan.services.serviceInterfaces;

import java.io.IOException;
import java.util.List;

import com.health.talan.entities.Challenge;


public interface IChallangeService {

	public void deleteChallange (Long id);
	public List<Challenge> getAll() throws IOException;
	public Challenge displayById(Long id);
	public List<Challenge> getByAdmin(Long id) throws IOException;
}
