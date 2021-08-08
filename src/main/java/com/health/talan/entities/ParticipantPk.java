package com.health.talan.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ParticipantPk implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idUser;
	private Long idEvent;

	public ParticipantPk() {
		super();
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Long idEvent) {
		this.idEvent = idEvent;
	}

	@Override
	public String toString() {
		return "ParticipantPk [idUser=" + idUser + ", idEvent=" + idEvent + "]";
	}

}
