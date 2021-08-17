package com.health.talan.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name= "community")
public class Community implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "image")
	private byte[] image;
	
	@Column(name = "domaine")
	private String domaine;
	
	@Column(name="type")
    private String type;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "id_adminCom")
	private User adminCom;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="user_Participation_in_community",
	joinColumns = @JoinColumn(name = "community_id"), 
	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> participants;

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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

	public User getAdminCom() {
		return adminCom;
	}

	public void setAdminCom(User adminCom) {
		this.adminCom = adminCom;
	}

	public Set<User> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Community() {
		super();
	}

	
	
	@Override
	public String toString() {
		return "Community [id=" + id + ", nom=" + nom + ", image=" + Arrays.toString(image) + ", domaine=" + domaine
				+ ", type=" + type + ", description=" + description + ", adminCom=" + adminCom + ", participants="
				+ participants + "]";
	}

	public Community(Long id, String nom, byte[] image, String domaine, String type, String description, User adminCom,
			Set<User> participants) {
		super();
		this.id = id;
		this.nom = nom;
		this.image = image;
		this.domaine = domaine;
		this.type = type;
		this.description = description;
		this.adminCom = adminCom;
		this.participants = participants;
	}	
}
