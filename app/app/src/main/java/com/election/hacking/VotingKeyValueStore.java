package com.election.hacking;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class VotingKeyValueStore {
    private static VotingKeyValueStore sVotingKeyValueStore;

    public static synchronized VotingKeyValueStore getInstance() {
        if (sVotingKeyValueStore == null) {
            sVotingKeyValueStore = new VotingKeyValueStore();
        }
        return sVotingKeyValueStore;
    }

    private final Map<String, List<Pair<Integer, Integer>>> mKeyValueStore;

    public VotingKeyValueStore() {
        mKeyValueStore = new HashMap<>();
    }

    public synchronized void vote(final String userToken, final int electionId, final int politicianId) {
        if (!mKeyValueStore.containsKey(userToken)) {
            mKeyValueStore.put(userToken, new ArrayList<Pair<Integer, Integer>>());
        }

        mKeyValueStore.get(userToken).add(new Pair<>(electionId, politicianId));
    }

    public synchronized Integer votedFor(final String userToken, final int electionId) {
        if (!mKeyValueStore.containsKey(userToken)) {
            return null;
        }

        final List<Pair<Integer, Integer>> votes = mKeyValueStore.get(userToken);

        for (final Pair<Integer, Integer> vote : votes) {
            if (vote.first == electionId) {
                return vote.second;
            }
        }

        return null;
    }
}
