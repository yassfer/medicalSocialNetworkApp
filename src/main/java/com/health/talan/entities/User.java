package com.health.talan.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")

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

	@Lob
	@Column(name = "logo")
	private byte[] logo;

	@Column(name = "profession")
	private String profession;

	@Column(name = "professionnalisme")
	private boolean professionnalisme;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "recommander", referencedColumnName = "id")
	private User recommander;

	@Column(name= "score")
	private int score;

	@Column(name= "connected")
	private boolean connected;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Interest> interests = new HashSet<>();

	@JsonIgnore
	@OneToOne
	private PieceJoint pieceJustifs = new PieceJoint();

	@JsonIgnore
	@ManyToMany
	@JoinTable( name = "relationship",
			joinColumns = @JoinColumn( name = "idMe" ),
			inverseJoinColumns = @JoinColumn( name = "idFriend" ))
	private Set<User> friends;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "adminCom", fetch = FetchType.EAGER)
	private Set<Community> myCommunities;
	
	 
	@JsonIgnore
	@ManyToMany(mappedBy="participants" , cascade = CascadeType.ALL)
	private Set<Community> communitiesParticipate;

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
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<PublicationCommunity> publicationCommunity = new HashSet<>();


	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Liking> likes = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Comment> comments = new HashSet<>();

	private boolean verified = false;

	private boolean type = false;

	public User() {
		super();
	}

	public User(String firstName, String lastName, String mail, String username, String password, Date birthDate,
				String address, String profession, boolean professionnalisme, User recommander, boolean type) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.username = username;
		this.password = password;
		this.birthDate = birthDate;
		this.address = address;
		this.profession = profession;
		this.professionnalisme = professionnalisme;
		this.recommander= recommander;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public boolean isProfessionnalisme() {
		return professionnalisme;
	}

	public void setProfessionnalisme(boolean professionnalisme) {
		this.professionnalisme = professionnalisme;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Interest> getInterests() {
		return interests;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public void setInterests(Set<Interest> interests) {
		this.interests = interests;
	}

	public PieceJoint getPieceJustifs() {
		return pieceJustifs;
	}

	public void setPieceJustifs(PieceJoint pieceJustifs) {
		this.pieceJustifs = pieceJustifs;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	public boolean getType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public Set<Community> getMyCommunities() {
		return myCommunities;
	}

	public void setMyCommunities(Set<Community> myCommunities) {
		this.myCommunities = myCommunities;
	}

	public Set<Community> getCommunitiesParticipate() {
		return communitiesParticipate;
	}

	public void setCommunitiesParticipate(Set<Community> communitiesParticipate) {
		this.communitiesParticipate = communitiesParticipate;
	}

	public Set<Entreprise> getEntreprises() {
		return entreprises;
	}

	public void setEntreprises(Set<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}

	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Invitation> getInvitationsSend() {
		return invitationsSend;
	}

	public void setInvitationsSend(Set<Invitation> invitationsSend) {
		this.invitationsSend = invitationsSend;
	}

	public Set<Invitation> getInvitationsReceive() {
		return invitationsReceive;
	}

	public void setInvitationsReceive(Set<Invitation> invitationsReceive) {
		this.invitationsReceive = invitationsReceive;
	}

	public Set<Publicity> getPublicities() {
		return publicities;
	}

	public void setPublicities(Set<Publicity> publicities) {
		this.publicities = publicities;
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	public Set<Challenge> getMyChallenge() {
		return myChallenge;
	}

	public void setMyChallenge(Set<Challenge> myChallenge) {
		this.myChallenge = myChallenge;
	}

	public Set<PublicationChallenge> getPublicationChallenges() {
		return publicationChallenges;
	}

	public void setPublicationChallenges(Set<PublicationChallenge> publicationChallenges) {
		this.publicationChallenges = publicationChallenges;
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

	public User getRecommander() {
		return recommander;
	}

	public void setRecommander(User recommander) {
		this.recommander = recommander;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Set<PublicationCommunity> getPublicationCommunity() {
		return publicationCommunity;
	}

	public void setPublicationCommunity(Set<PublicationCommunity> publicationCommunity) {
		this.publicationCommunity = publicationCommunity;
	}

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", mail='" + mail + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", birthDate=" + birthDate +
				", address='" + address + '\'' +
				", logo=" + Arrays.toString(logo) +
				", profession='" + profession + '\'' +
				", professionnalisme=" + professionnalisme +
				", recommander=" + recommander +
				", score=" + score +
				", connected=" + connected +
				", roles=" + roles +
				", interests=" + interests +
				", pieceJustifs=" + pieceJustifs +
				", friends=" + friends +
				", myCommunities=" + myCommunities +
				", communitiesParticipate=" + communitiesParticipate +
				", entreprises=" + entreprises +
				", participants=" + participants +
				", events=" + events +
				", invitationsSend=" + invitationsSend +
				", invitationsReceive=" + invitationsReceive +
				", publicities=" + publicities +
				", publications=" + publications +
				", myChallenge=" + myChallenge +
				", publicationChallenges=" + publicationChallenges +
				", publicationCommunity=" + publicationCommunity +
				", likes=" + likes +
				", comments=" + comments +
				", verified=" + verified +
				", type=" + type +
				'}';
	}
}
