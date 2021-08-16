package com.health.talan.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CommunityNotFoundException extends RuntimeException{
private static final long serialVersionUID = 1L;
	
	public CommunityNotFoundException (String message) {
		super(message);
	}

}
