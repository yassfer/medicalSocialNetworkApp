package com.health.talan.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "comment")
@Getter
@Setter
@ToString
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;


	@Column(name = "content")
	private String content;


	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;



	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "id_publication")
	private Publication publication;




	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	private User user;

	public Comment() {
		super();
	}


	public Comment(String content, Publication publication) {
		this.content = content;
		this.publication = publication;
	}
}
