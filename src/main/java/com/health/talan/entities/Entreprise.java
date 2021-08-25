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
	private byte[] image;
	@Column(name = "product")
	private String product;
	@Column(name = "expiration")
	private Date expiration;
	@Column(name = "code")
	private String code;
	@Column(name = "reduction")
	private Long reduction;
	@Column(name = "datecreation")
	private Date datecreation;


	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	public Entreprise() {
		super();
	}


	@Override
	public String toString() {
		return "Entreprise [id=" + id + ", nom=" + nom + ", address=" + address + ", image=" + Arrays.toString(image)
				+ ", product=" + product + ", expiration=" + expiration + ", code=" + code + ", reduction="
				+ reduction + ", datecreation=" + datecreation + ", user=" + user + "]";
	}


	public Entreprise(Long id, String nom, String address, byte[] image, String product, Date expiration,
					  String Code, Long reduction, Date datecreation, User user) {
		super();
		this.id = id;
		this.nom = nom;
		this.address = address;
		this.image = image;
		this.product = product;
		this.expiration = expiration;
		this.code = Code;
		this.reduction = reduction;
		this.datecreation = datecreation;
		this.user = user;
	}


	public Long getReduction() {
		return reduction;
	}


	public void setReduction(Long reduction) {
		this.reduction = reduction;
	}


	public Date getDatecreation() {
		return datecreation;
	}


	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
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

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
