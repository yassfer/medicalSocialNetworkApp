package com.health.talan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "pieceJoint")
@Getter
@Setter
@ToString

public class PieceJoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    private String name;


    private int size;


    private String contentType;


    private String url;


    @JsonIgnore
    @Lob
    private byte[] data;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "publication_pieceJoint")
    private Publication publication;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "publicationChallenge_pieceJoint")
    private PublicationChallenge publicationChallenge;


    public PieceJoint() {
        super();
    }

    public PieceJoint(String name, String contentType, byte[] data, int size) {
        this.name = name;
        this.contentType = contentType;
        this.data = data;
        this.size = size;
    }

    public PieceJoint(String name, String contentType, byte[] data, int size, String url) {
        this.name = name;
        this.contentType = contentType;
        this.data = data;
        this.size = size;
        this.url = url;
    }



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public PublicationChallenge getPublicationChallenge() {
        return publicationChallenge;
    }

    public void setPublicationChallenge(PublicationChallenge publicationChallenge) {
        this.publicationChallenge = publicationChallenge;
    }
}
