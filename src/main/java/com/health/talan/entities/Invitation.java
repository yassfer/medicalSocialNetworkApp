package com.health.talan.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "invitation")
public class Invitation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id_sender")
	private User sender;
	@ManyToOne
	@JoinColumn(name = "id_receiver")
	private User receiver;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	@Temporal(TemporalType.TIME)
	@Column(name = "time")
	private Date time;


	@Transient
	private String since;

	@Temporal(TemporalType.TIME)
	@Transient
	private Date timeChecked;
	public Invitation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}


	public String getSince() {
		return since;
	}

	public void setSince(String since) {
		this.since = since;
	}

	public Date getTimeChecked() {
		return timeChecked;
	}

	public void setTimeChecked(Date timeChecked) {
		this.timeChecked = timeChecked;
	}

	@Override
	public String toString() {
		return "Invitation [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", date=" + date + ", time="
				+ time + ", since=" + since + ", timeChecked=" + timeChecked + "]";
	}
}
