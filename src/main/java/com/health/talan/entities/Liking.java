package com.health.talan.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date = new Date();



	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "publication")
	private Publication publication;


	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "publicationChallenge")
	private PublicationChallenge publicationChallenge;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	private User user;


	public Liking() {
		super();
	}


	public Liking(Publication publication) {
		this.publication = publication;
	}
}
