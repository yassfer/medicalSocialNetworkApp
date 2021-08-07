package com.health.talan.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@ManyToOne
	@JoinColumn(name = "id_adminCom")
	private User adminCom;
	
	@ManyToMany
	@JoinTable(name ="user_communities_participate",
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
	@Override
	public String toString() {
		return "Community [id=" + id + ", nom=" + nom + ", image=" + Arrays.toString(image) + ", domaine=" + domaine
				+ ", adminCom=" + adminCom + ", participants=" + participants + "]";
	}
	public Community(Long id, String nom, byte[] image, String domaine, User adminCom, Set<User> participants) {
		super();
		this.id= id;
		this.nom = nom;
		this.image = image;
		this.domaine = domaine;
		this.adminCom = adminCom;
		this.participants = participants;
	}
	public Community() {
		super();
	}
	
	
	
}
