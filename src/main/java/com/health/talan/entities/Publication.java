package com.health.talan.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	private Date dateCreation = new Date();


	@Column(name = "content")
	private String content;


	@OneToMany(mappedBy = "publication", fetch = FetchType.EAGER)
	@Column(name = "pieceJoint")
	private Set<PieceJoint> pieceJoints = new HashSet<>();


	@JsonIgnoreProperties(value = {"publication", "handler","hibernateLazyInitializer"}, allowSetters = true)
	@OneToMany(mappedBy = "publication", fetch = FetchType.EAGER)
	private Set<Liking> likes = new HashSet<>();



	@JsonIgnoreProperties(value = {"publication", "handler","hibernateLazyInitializer"}, allowSetters = true)
	@OneToMany(mappedBy = "publication", fetch = FetchType.EAGER)
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

}
