package com.health.talan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@ToString
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
	@ManyToOne(fetch = FetchType.EAGER)
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
}
