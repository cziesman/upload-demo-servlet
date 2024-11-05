package com.redhat;

import java.io.Serializable;

public class UploadedFile implements Serializable {

    private String name;

    private String mime;

    private long length;

    private String data;

    public String getData() {

        return data;
    }

    public void setData(String data) {

        this.data = data;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public long getLength() {

        return length;
    }

    public void setLength(long length) {

        this.length = length;
    }

    public String getMime() {

        return mime;
    }

    public void setMime(String mime) {

        this.mime = mime;
    }

    @Override
    public String toString() {

        return "UploadedFile{" +
                "name='" + name + '\'' +
                ", mime='" + mime + '\'' +
                ", length=" + length +
                ", data='" + data + '\'' +
                '}';
    }

}