package com.health.talan.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "amis")
public class Amis implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "myFriend")
	private User myFriend;
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	public Amis() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getMyFriend() {
		return myFriend;
	}

	public void setMyFriend(User myFriend) {
		this.myFriend = myFriend;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Amis [id=" + id + ", myFriend=" + myFriend + ", user=" + user + "]";
	}

}
