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


    private Long size;


    private String contentType;


    @Lob
    private byte[] data;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "publication_pieceJoint")
    private Publication publication;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "publicationChallenge_pieceJoint")
    private PublicationChallenge publicationChallenge;


    public PieceJoint() {
        super();
    }

    public PieceJoint(String name, String contentType, byte[] data, Long size) {
        this.name = name;
        this.contentType = contentType;
        this.data = data;
        this.size = size;
    }



}
