package com.health.talan.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "liking")

public class Liking implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;


	@Column(name = "date")
	private Date date;


	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "publication")
	private Publication publication;


	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "publicationChallenge")
	private PublicationChallenge publicationChallenge;


	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	private User user;


	public Liking() {
		super();
	}


	public Liking(Date date, Publication publication, User user) {
		this.date = date;
		this.publication = publication;
		this.user = user;
	}
}
