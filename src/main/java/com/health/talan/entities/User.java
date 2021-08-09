package com.health.talan.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;


	@Column(name = "firstName")
	private String firstName;


	@Column(name = "lastName")
	private String lastName;


	@Column(name = "mail")
	private String mail;


	@Column(name = "username")
	private String username;


	@Column(name = "password")
	private String password;


	@Temporal(TemporalType.DATE)
	@Column(name = "birthDate")
	private Date birthDate;


	@Column(name = "address")
	private String address;


	@Column(name = "image")
	private PieceJoint image;


	@Column(name = "profession")
	private String profession;


	@Column(name = "professionnalisme")
	private boolean professionnalisme;


	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Interest> interests = new HashSet<>();


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<PieceJustif> pieceJustifs = new HashSet<>();


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Amis> amis = new HashSet<>();


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "adminCom")
	private Set<Community> myCommunities = new HashSet<>();


	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Community> communitiesParticipate = new HashSet<>();


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Entreprise> entreprises = new HashSet<>();


	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Participant> participants = new HashSet<>();


	@JsonIgnore
	@OneToMany(mappedBy = "adminEvent")
	private Set<Event> events = new HashSet<>();


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
	private Set<Invitation> invitationsSend = new HashSet<>();


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
	private Set<Invitation> invitationsReceive = new HashSet<>();


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Publicity> publicities = new HashSet<>();


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Publication> publications = new ArrayList<>();


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "adminChallenge")
	private Set<Challenge> myChallenge = new HashSet<>();


	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<PublicationChallenge> publicationChallenges = new HashSet<>();


	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Liking> likes = new HashSet<>();


	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Comment> comments = new HashSet<>();


	public User() {
		super();
	}


	public User(String firstName, String lastName, String mail, String username, String password,
				Date birthDate, String address, PieceJoint image, String profession, boolean professionnalisme) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.username = username;
		this.password = password;
		this.birthDate = birthDate;
		this.address = address;
		this.image = image;
		this.profession = profession;
		this.professionnalisme = professionnalisme;
	}
}


