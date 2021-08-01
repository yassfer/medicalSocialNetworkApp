package com.health.talan.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PublicationChallengePk implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long idUser;
	private Long idChallenge;

	public PublicationChallengePk() {
		super();
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdChallenge() {
		return idChallenge;
	}

	public void setIdChallenge(Long idChallenge) {
		this.idChallenge = idChallenge;
	}

	@Override
	public String toString() {
		return "PublicationChallengePk [idUser=" + idUser + ", idChallenge=" + idChallenge + "]";
	}

}
