package com.health.talan.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "liking")

public class Liking implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date = new Date();



	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "publication")
	private Publication publication;


	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "publicationChallenge")
	private PublicationChallenge publicationChallenge;


	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "publicationCommunity")
	private PublicationCommunity publicationCommunity;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	private User user;


	public Liking() {
		super();
	}


	public Liking(Publication publication) {
		this.publication = publication;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Publication getPublication() {
		return publication;
	}


	public void setPublication(Publication publication) {
		this.publication = publication;
	}


	public PublicationChallenge getPublicationChallenge() {
		return publicationChallenge;
	}


	public void setPublicationChallenge(PublicationChallenge publicationChallenge) {
		this.publicationChallenge = publicationChallenge;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public PublicationCommunity getPublicationCommunity() {
		return publicationCommunity;
	}

	public void setPublicationCommunity(PublicationCommunity publicationCommunity) {
		this.publicationCommunity = publicationCommunity;
	}

	@Override
	public String toString() {
		return "Liking{" +
				"id=" + id +
				", date=" + date +
				", publication=" + publication +
				", publicationChallenge=" + publicationChallenge +
				", publicationCommunity=" + publicationCommunity +
				", user=" + user +
				'}';
	}
}
