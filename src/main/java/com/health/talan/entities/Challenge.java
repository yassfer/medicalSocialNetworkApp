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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@ManyToOne
	@JoinColumn(name = "id_adminChallenge")
	private User adminChallenge;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "challenge", fetch = FetchType.EAGER)
	private Set<PublicationChallenge> PublicationChallenge;

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

	public Set<PublicationChallenge> getPublicationChallange() {
		return PublicationChallenge;
	}

	public void setPublicationChallange(Set<PublicationChallenge> publicationChallange) {
		PublicationChallenge = publicationChallange;
	}

	@Override
	public String toString() {
		return "Challenge [id=" + id + ", nom=" + nom + ", objectif=" + objectif + ", pieceJoint="
				+ Arrays.toString(new Set[]{pieceJoints}) + ", adminChallenge=" + adminChallenge + ", PublicationChallange="
				+ PublicationChallenge + "]";
	}

}
