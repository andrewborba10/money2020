package com.election.hacking.model;

import android.util.Pair;

public class ElectionUserPair extends Pair<String, Integer> {

    public ElectionUserPair(final String first, final Integer second) {
        super(first, second);
    }

    public String getUser() {
        return first;
    }

    public Integer getElectionId() {
        return second;
    }
}
