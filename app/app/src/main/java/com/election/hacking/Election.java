package com.election.hacking;

import java.util.List;

public class Election {
    private int id;
    private String title;
    private String description;
    private String dateOpen;
    private String dateClosed;
    private List<Candidate> candidates;

    public Election(int id, String title, String description, String dateOpen, String dateClosed, List<Candidate> candidates) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateOpen = dateOpen;
        this.dateClosed = dateClosed;
        this.candidates = candidates;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDateOpen() {
        return dateOpen;
    }

    public String getDateClosed() {
        return dateClosed;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }
}
