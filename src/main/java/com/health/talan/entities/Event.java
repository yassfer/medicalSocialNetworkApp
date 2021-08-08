package com.health.talan.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "event")
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "subject")
	private String subject;
	@Column(name = "dateDebut")
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Column(name = "dateFin")
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	@OneToMany(mappedBy = "event")
	private Set<Participant> participants;
	@ManyToOne
	@JoinColumn(name = "id_adminEvent")
	private User adminEvent;

	public Event() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	public User getAdminEvent() {
		return adminEvent;
	}

	public void setAdminEvent(User adminEvent) {
		this.adminEvent = adminEvent;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", subject=" + subject + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", participants=" + participants + ", adminEvent=" + adminEvent + "]";
	}

}
