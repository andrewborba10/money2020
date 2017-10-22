package com.election.hacking;

import java.io.Serializable;

public class Candidate implements Serializable {
    private int id;
    private String name;
    private String party;

    public Candidate(int id, String name, String party) {
        this.id = id;
        this.name = name;
        this.party = party;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }
}
