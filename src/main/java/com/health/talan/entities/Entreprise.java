package com.health.talan.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "entreprise")
public class Entreprise implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nom")
	private String nom;
	@Column(name = "address")
	private String address;
	@Column(name = "image")
	private byte [] image;
	@Column(name = "product")
	private String product;

	@Column(name = "code")
	private String code;
	@Column(name = "expiration")
	private int expiration;

	@Column(name = "dateCreation")
	private Date dateCreation;



	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	public Entreprise() {
		super();
	}


	@Override
	public String toString() {
		return "Entreprise [id=" + id + ", nom=" + nom + ", address=" + address + ", image=" + image
				+ ", product=" + product + ", expiration=" + expiration + ", code=" + code + ", dateCreation=" + dateCreation +  "]";
	}


	public Entreprise(Long id, String nom, String address,  byte [] image, String product, int expiration,
					  String Code, Date dateCreation) {
		super();
		this.id = id;
		this.nom = nom;
		this.address = address;
		this.image = image;
		this.product = product;
		this.code = Code;
		this.expiration = expiration;
		this.dateCreation=dateCreation;

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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public int getExpiration() {
		return expiration;
	}


	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}








}