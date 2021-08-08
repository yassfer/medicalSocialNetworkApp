package com.health.talan.Response;


public class ResponsePieceJoint {

    private Long id;
    private String name;
    private int size;
    private String url;
    private String contentType;


    public ResponsePieceJoint(){}

    public ResponsePieceJoint(String name, String pieceJointDownloadUri, String contentType, int length) {
        this.name = name;
        this.url = pieceJointDownloadUri;
        this.contentType = contentType;
        this.size = length;
    }


    public ResponsePieceJoint(Long id, String name, String pieceJointDownloadUri, String contentType, int length) {
        this.id = id;
        this.name = name;
        this.size = length;
        this.url = pieceJointDownloadUri;
        this.contentType = contentType;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
