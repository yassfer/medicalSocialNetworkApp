package com.health.talan.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "publication")
public class Publication implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateCreation")
	private Date dateCreation = new Date(System.currentTimeMillis());


	@Temporal(TemporalType.TIME)
	@Column(name = "time")
	private Date time = new Date(System.currentTimeMillis());


	@Column(name = "content")
	private String content;


	@JsonIgnoreProperties(value = {"publication", "handler","hibernateLazyInitializer"}, allowSetters = true)
	@OneToMany(mappedBy = "publication", fetch = FetchType.EAGER)
	@Column(name = "pieceJoint")
	private Set<PieceJoint> pieceJoints = new HashSet<>();


	@JsonIgnoreProperties(value = {"publication", "handler","hibernateLazyInitializer"}, allowSetters = true)
	@OneToMany(mappedBy = "publication")
	private Set<Liking> likes = new HashSet<>();



	@JsonIgnoreProperties(value = {"publication", "handler","hibernateLazyInitializer"}, allowSetters = true)
	@OneToMany(mappedBy = "publication")
	private Set<Comment> comments = new HashSet<>();



	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User user;


	public Publication() {
		super();
	}


	public Publication(String content, Set<PieceJoint> pieceJoints) {
		this.content = content;
		this.pieceJoints = pieceJoints;
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


	public Set<Comment> getComments() {
		return comments;
	}


	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Publication [id=" + id + ", dateCreation=" + dateCreation + ", content=" + content + ", pieceJoints="
				+ pieceJoints + ", likes=" + likes + ", comments=" + comments + ", user=" + user + "]";
	}
	
	

}
