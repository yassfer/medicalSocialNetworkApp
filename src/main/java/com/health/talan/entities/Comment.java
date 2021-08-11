package com.health.talan.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "comment")

public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;


	@Column(name = "content")
	private String content;


	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date = new Date();



	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "id_publication")
	private Publication publication;




	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	private User user;

	public Comment() {
		super();
	}


	public Comment(String content, Publication publication) {
		this.content = content;
		this.publication = publication;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", date=" + date + ", publication=" + publication
				+ ", user=" + user + "]";
	}
	
	
}
