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
import com.health.talan.entities.Role;
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
	private byte[] image;


	@Column(name = "profession")
	private String profession;


	@Column(name = "professionnalisme")
	private boolean professionnalisme;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Interest> interests;


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<PieceJustif> pieceJustifs;


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Amis> amis;


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "adminCom", fetch = FetchType.EAGER)
	private Set<Community> myCommunities;


	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Community> communitiesParticipate;


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Entreprise> entreprises;


	@OneToMany(mappedBy = "user")
	private Set<Participant> participants;


	@OneToMany(mappedBy = "adminEvent")
	private Set<Event> events;


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.EAGER)
	private Set<Invitation> invitationsSend;


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver", fetch = FetchType.EAGER)
	private Set<Invitation> invitationsReceive;


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Publicity> publicities;


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private List<Publication> publications;


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "adminChallenge", fetch = FetchType.EAGER)
	private Set<Challenge> myChallenge;


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<PublicationChallenge> publicationChallenges;


	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Liking> likes = new HashSet<>();


	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Comment> comments = new HashSet<>();


	public void addPublication(Publication publication) {

		publications.add(publication);
		publication.setUser(this);

	}

	public void addLike(Liking like) {

		likes.add(like);
		like.setUser(this);

	}

	public void addComment(Comment comment) {

		comments.add(comment);
		comment.setUser(this);

	}



	public User() {
		super();
	}


	public User(String firstName, String lastName, String mail, String username, String password,
				Date birthDate, String address, byte[] image, String profession, boolean professionnalisme,
				Set<Role> roles, Set<Interest> interests, Set<PieceJustif> pieceJustifs, Set<Amis> amis,
				Set<Community> myCommunities, Set<Community> communitiesParticipate, Set<Entreprise> entreprises,
				Set<Participant> participants, Set<Event> events, Set<Invitation> invitationsSend,
				Set<Invitation> invitationsReceive, Set<Publicity> publicities, List<Publication> publications,
				Set<Challenge> myChallenge, Set<PublicationChallenge> publicationChallenge, Set<Liking> likes,
				Set<Comment> comments) {

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
		this.roles = roles;
		this.interests = interests;
		this.pieceJustifs = pieceJustifs;
		this.amis = amis;
		this.myCommunities = myCommunities;
		this.communitiesParticipate = communitiesParticipate;
		this.entreprises = entreprises;
		this.participants = participants;
		this.events = events;
		this.invitationsSend = invitationsSend;
		this.invitationsReceive = invitationsReceive;
		this.publicities = publicities;
		this.publications = publications;
		this.myChallenge = myChallenge;
		this.publicationChallenges = publicationChallenge;
		this.likes = likes;
		this.comments = comments;
	}
}


