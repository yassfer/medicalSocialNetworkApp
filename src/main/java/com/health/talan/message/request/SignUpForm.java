package com.health.talan.message.request;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.*;

public class SignUpForm {
	@NotBlank
	@Size(min = 3, max = 50)
	private String firstName;

	@NotBlank
	@Size(min = 3, max = 50)
	private String lastName;

	@NotBlank
	@Size(min = 3, max = 50)
	private String username;

	@NotBlank
	@Size(max = 60)
	@Email
	private String mail;

	private Set role;

	private boolean type = false;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	private Date birthDate;
	private String address;
	private String profession;
	private boolean professionnalisme;
	private String recommander;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Set getRole() {
		return role;
	}

	public void setRole(Set role) {
		this.role = role;
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

	public boolean getType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getRecommander() {
		return recommander;
	}

	public void setRecommander(String recommander) {
		this.recommander = recommander;
	}
}