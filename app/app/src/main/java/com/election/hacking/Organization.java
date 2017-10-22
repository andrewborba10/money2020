package com.election.hacking;

import java.util.List;

public class Organization {
    private List<String> tags;
    private String imageUrl;
    private String name;
    private String description;
    private String userDonations;
    private String globalDonations;

    public Organization(List<String> tags, String imageUrl, String name, String description, String userDonations, String globalDonations) {
        this.tags = tags;
        this.imageUrl = imageUrl;
        this.name = name;
        this.description = description;
        this.userDonations = userDonations;
        this.globalDonations = globalDonations;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUserDonations() {
        return userDonations;
    }

    public String getGlobalDonations() {
        return globalDonations;
    }
}
