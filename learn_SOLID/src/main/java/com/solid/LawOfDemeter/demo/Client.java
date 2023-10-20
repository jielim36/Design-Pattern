package com.solid.LawOfDemeter.demo;

public class Client {
    public static void main(String[] args) {
        Agent agent = new Agent();
        Artist artist = new Artist("Jielim");
        Fans fans = new Fans("Tom");
        Company company = new Company("ABC Company");
        agent.setArtist(artist);
        agent.setFans(fans);
        agent.setCompany(company);

        agent.meeting();
        agent.businessNegotiations();
    }
}
