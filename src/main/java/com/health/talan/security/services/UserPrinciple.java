package com.health.talan.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.health.talan.entities.User;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String firstName;

	private String username;

	private String mail;

	@JsonIgnore
	private String password;

	private Collection authorities;

	public UserPrinciple(Long id, String firstName, String username, String mail, String password, Collection authorities) {
		this.id = id;
		this.firstName = firstName;
		this.username = username;
		this.mail = mail;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrinciple build(User user) {
		List authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserPrinciple(user.getId(), user.getFirstName(), user.getUsername(), user.getMail(), user.getPassword(),
				authorities);
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMail() {
		return mail;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserPrinciple user = (UserPrinciple) o;
		return Objects.equals(id, user.id);
	}
}