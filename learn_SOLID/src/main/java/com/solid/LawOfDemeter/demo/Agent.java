package com.solid.LawOfDemeter.demo;

public class Agent {

    private Artist artist;
    private Company company;
    private Fans fans;

    //fans meeting
    public void meeting(){
        System.out.println("Artist(Name:"+ artist.getName() +") meeting with fans(Name:"+ fans.getName() +")");
    }

    //business talk
    public void businessNegotiations(){
        System.out.println("Artist(Name:"+ artist.getName() +") talking with company(Name:"+ company.getName() +")");
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Fans getFans() {
        return fans;
    }

    public void setFans(Fans fans) {
        this.fans = fans;
    }
}
