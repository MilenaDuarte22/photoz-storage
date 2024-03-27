package com.jetbrains.milena.photoz.storage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;

public class Photo {
    private  String contentType;
    private String id;
    @NotEmpty
    private String fileName;

    //saving in memory for now
    @JsonIgnore
    private byte[] photoData;
    public Photo() {
    }

    //raw data


    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
