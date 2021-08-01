package com.health.talan.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@ManyToMany(mappedBy= "communitiesParticipate", cascade = CascadeType.ALL)
	private Set<User> participants;
}
