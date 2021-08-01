package com.health.talan.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "participant")
public class Participant implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ParticipantPk participantPk;
	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name = "idEvent", referencedColumnName = "id", insertable = false, updatable = false)
	private Event event;

	public Participant() {
		super();
	}

	public ParticipantPk getParticipantPk() {
		return participantPk;
	}

	public void setParticipantPk(ParticipantPk participantPk) {
		this.participantPk = participantPk;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Participant [participantPk=" + participantPk + ", user=" + user + ", event=" + event + "]";
	}

}
