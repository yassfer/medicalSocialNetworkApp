package com.health.talan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "publicationCommunity")
public class PublicationCommunity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateCreation")
    private Date dateCreation = new Date();


    @Column(name = "content")
    private String content;


    @JsonIgnoreProperties(value = {"publicationCommunity", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @OneToMany(mappedBy = "publicationCommunity", fetch = FetchType.EAGER)
    @Column(name = "pieceJoint")
    private Set<PieceJoint> pieceJoints = new HashSet<>();


    @JsonIgnoreProperties(value = {"publicationCommunity", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @OneToMany(mappedBy = "publicationCommunity")
    private Set<Liking> likes = new HashSet<>();



    @JsonIgnoreProperties(value = {"publicationCommunity", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @OneToMany(mappedBy = "publicationCommunity")
    private Set<Comment> comments = new HashSet<>();



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Community")
    private Community community;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;


    public PublicationCommunity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<PieceJoint> getPieceJoints() {
        return pieceJoints;
    }

    public void setPieceJoints(Set<PieceJoint> pieceJoints) {
        this.pieceJoints = pieceJoints;
    }

    public Set<Liking> getLikes() {
        return likes;
    }

    public void setLikes(Set<Liking> likes) {
        this.likes = likes;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PublicationCommunity{" +
                "id=" + id +
                ", dateCreation=" + dateCreation +
                ", content='" + content + '\'' +
                ", pieceJoints=" + pieceJoints +
                ", likes=" + likes +
                ", comments=" + comments +
                ", community=" + community +
                ", user=" + user +
                '}';
    }
}
