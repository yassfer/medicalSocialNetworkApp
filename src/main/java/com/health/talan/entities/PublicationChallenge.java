package com.health.talan.entities;

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
	private Date dateCreation;


	@Column(name = "content")
	private String content;


	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "publicationChallenge")
	@Column(name = "pieceJoint")
	private Set<PieceJoint> pieceJoints = new HashSet<>();


	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "publicationChallenge")
	private Set<Liking> likes = new HashSet<>();


	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "publicationChallenge")
	private Set<Comment> comments;



	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;


	@ManyToOne
	@JoinColumn(name = "idChallenge", referencedColumnName = "id", insertable = false, updatable = false)
	private Challenge challenge;


	private boolean approuved = false;


	public PublicationChallenge() {
		super();
	}

	public PublicationChallenge(String content, Set<PieceJoint> pieceJoints, Set<Liking> likes,
								Set<Comment> comments, User user, Challenge challenge, boolean approuved) {

		this.content = content;
		this.pieceJoints = pieceJoints;
		this.likes = likes;
		this.comments = comments;
		this.user = user;
		this.challenge = challenge;
		this.approuved = approuved;
	}


	public PublicationChallenge(String content, Set<PieceJoint> pieceJoints, User user, Challenge challenge, boolean approuved) {
		this.content = content;
		this.pieceJoints = pieceJoints;
		this.user = user;
		this.challenge = challenge;
		this.approuved = approuved;
	}
}
