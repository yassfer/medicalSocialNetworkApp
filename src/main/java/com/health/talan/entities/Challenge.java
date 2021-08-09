package com.health.talan.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "challenge")
public class Challenge implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;


	@Column(name = "nom")
	private String nom;


	@Column(name = "objectif")
	private String objectif;


	@OneToMany(fetch = FetchType.EAGER, mappedBy = "publicationChallenge")
	@Column(name = "pieceJoint")
	private Set<PieceJoint> pieceJoints = new HashSet<>();



	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_adminChallenge")
	private User adminChallenge;



	@OneToMany(cascade = CascadeType.ALL, mappedBy = "challenge", fetch = FetchType.EAGER)
	private Set<PublicationChallenge> PublicationChallenge = new HashSet<>();



	public Challenge(String nom, String objectif) {

		this.nom = nom;
		this.objectif = objectif;
	}



	public Challenge() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public Set<PieceJoint> getPieceJoints() {
		return pieceJoints;
	}

	public void setPieceJoints(Set<PieceJoint> pieceJoints) {
		this.pieceJoints = pieceJoints;
	}

	public User getAdminChallenge() {
		return adminChallenge;
	}

	public void setAdminChallenge(User adminChallenge) {
		this.adminChallenge = adminChallenge;
	}

	public Set<PublicationChallenge> getPublicationChallenge() {
		return PublicationChallenge;
	}

	public void setPublicationChallenge(Set<PublicationChallenge> publicationChallenge) {
		PublicationChallenge = publicationChallenge;
	}

	@Override
	public String toString() {
		return "Challenge [id=" + id + ", nom=" + nom + ", objectif=" + objectif + ", pieceJoint="
				+ Arrays.toString(new Set[]{pieceJoints}) + ", adminChallenge=" + adminChallenge + ", PublicationChallenge="
				+ PublicationChallenge + "]";
	}

}
