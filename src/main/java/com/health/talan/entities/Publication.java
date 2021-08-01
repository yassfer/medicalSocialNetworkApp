package com.health.talan.entities;

import java.io.Serializable;
import java.util.Arrays;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "publication")
public class Publication implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "dateCreaction")
	@Temporal(TemporalType.DATE)
	private Date dateCreaction;
	@Column(name = "content")
	private String content;
	@Column(name = "pieceJoint")
	private byte[] pieceJoint;
	@OneToMany(mappedBy = "publication")
	private Set<Liking> likes;
	@OneToMany(mappedBy = "publication")
	private Set<Comment> comments;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	public Publication() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCreaction() {
		return dateCreaction;
	}

	public void setDateCreaction(Date dateCreaction) {
		this.dateCreaction = dateCreaction;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public byte[] getPieceJoint() {
		return pieceJoint;
	}

	public void setPieceJoint(byte[] pieceJoint) {
		this.pieceJoint = pieceJoint;
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
		return "Publication [id=" + id + ", dateCreaction=" + dateCreaction + ", content=" + content + ", pieceJoint="
				+ Arrays.toString(pieceJoint) + ", likes=" + likes + ", comments=" + comments + ", user=" + user + "]";
	}

}
