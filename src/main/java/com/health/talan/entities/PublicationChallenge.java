package com.health.talan.entities;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "publicationChallenge")
public class PublicationChallenge implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private PublicationChallengePk publicationChallengePk;
	private String text;
	private byte[] image;
	private boolean approuved;
	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name = "idChallenge", referencedColumnName = "id", insertable = false, updatable = false)
	private Challenge challenge;

	public PublicationChallenge() {
		super();
	}

	public PublicationChallengePk getPublicationChallengePk() {
		return publicationChallengePk;
	}

	public void setPublicationChallengePk(PublicationChallengePk publicationChallengePk) {
		this.publicationChallengePk = publicationChallengePk;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public boolean isApprouved() {
		return approuved;
	}

	public void setApprouved(boolean approuved) {
		this.approuved = approuved;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	@Override
	public String toString() {
		return "PublicationChallenge [publicationChallengePk=" + publicationChallengePk + ", text=" + text + ", image="
				+ Arrays.toString(image) + ", approuved=" + approuved + ", user=" + user + ", challenge=" + challenge
				+ "]";
	}

}
