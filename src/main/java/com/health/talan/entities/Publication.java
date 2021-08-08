package com.health.talan.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "publication")
@Getter
@Setter
@ToString

public class Publication implements Serializable {

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


	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "publication")
	@Column(name = "pieceJoint")
	private Set<PieceJoint> pieceJoints = new HashSet<>();


	@OneToMany(mappedBy = "publication", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Set<Liking> likes = new HashSet<>();


	@OneToMany(mappedBy = "publication", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Set<Comment> comments;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User user;


	public void addLike(Liking like) {

		likes.add(like);
		like.setPublication(this);

	}

	public void addComment(Comment comment) {

		comments.add(comment);
		comment.setPublication(this);

	}

	public Publication() {
		super();
	}


	public Publication(Date dateCreation, String content, Set<PieceJoint> pieceJoints,
					   Set<Liking> likes, Set<Comment> comments, User user) {
		this.dateCreation = dateCreation;
		this.content = content;
		this.pieceJoints = pieceJoints;
		this.likes = likes;
		this.comments = comments;
		this.user = user;
	}

}
