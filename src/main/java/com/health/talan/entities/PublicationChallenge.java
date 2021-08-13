package com.health.talan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "publicationChallenge")
public class PublicationChallenge implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateCreation")
	private Date dateCreation = new Date();


	@Column(name = "content")
	private String content;


	@JsonIgnoreProperties(value = {"publicationChallenge", "handler","hibernateLazyInitializer"}, allowSetters = true)
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "publicationChallenge")
	@Column(name = "pieceJoint")
	private Set<PieceJoint> pieceJoints = new HashSet<>();


	@JsonIgnoreProperties(value = {"publicationChallenge", "handler","hibernateLazyInitializer"}, allowSetters = true)
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "publicationChallenge")
	private Set<Liking> likes = new HashSet<>();



	private boolean approuved = false;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idUser")
	private User user;


	@JsonIgnoreProperties(value = {"publicationChallenge", "handler","hibernateLazyInitializer"}, allowSetters = true)
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "challengeId")
	private Challenge challenge;



	public PublicationChallenge() {
		super();
	}

	public PublicationChallenge(String content) {

		this.content = content;
	}


	public PublicationChallenge(String content, Set<PieceJoint> pieceJoints, User user, Challenge challenge, boolean approuved) {
		this.content = content;
		this.pieceJoints = pieceJoints;
		this.user = user;
		this.challenge = challenge;
		this.approuved = approuved;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<PieceJoint> getPieceJoints() {
		return pieceJoints;
	}

	public void setPieceJoints(Set<PieceJoint> pieceJoints) {
		this.pieceJoints = pieceJoints;
	}

	public Set<Liking> getLikes() {
		return likes;
	}

	public void setLikes(Set<Liking> likes) {
		this.likes = likes;
	}

	public boolean isApprouved() {
		return approuved;
	}

	public void setApprouved(boolean approuved) {
		this.approuved = approuved;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	@Override
	public String toString() {
		return "PublicationChallenge [id=" + id + ", dateCreation=" + dateCreation + ", content=" + content
				+ ", pieceJoints=" + pieceJoints + ", likes=" + likes + ", approuved=" + approuved + ", user=" + user
				+ ", challenge=" + challenge + "]";
	}
	
	
}
