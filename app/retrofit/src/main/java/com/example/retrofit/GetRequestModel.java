package com.example.retrofit;

import androidx.annotation.NonNull;

public class GetRequestModel {

    private String name;
    private String realName;
    private String team;
    private String firstappearance;
    private String createdby;
    private String publisher;
    private String imageurl;
    private String job;

    public GetRequestModel(String name, String realName, String team, String firstappearance,
                           String createdby, String publisher, String imageurl, String job) {
        this.name = name;
        this.realName = realName;
        this.team = team;
        this.firstappearance = firstappearance;
        this.createdby = createdby;
        this.publisher = publisher;
        this.imageurl = imageurl;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getFirstappearance() {
        return firstappearance;
    }

    public void setFirstappearance(String firstappearance) {
        this.firstappearance = firstappearance;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @NonNull
    @Override public String toString() {
        return "GetRequestModel{" +
                "name='" + name + '\'' +
                ", realName='" + realName + '\'' +
                ", team='" + team + '\'' +
                ", firstappearance='" + firstappearance + '\'' +
                ", createdby='" + createdby + '\'' +
                ", publisher='" + publisher + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}