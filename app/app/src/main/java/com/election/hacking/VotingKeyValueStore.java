package com.election.hacking;

import android.app.Activity;

import com.election.hacking.model.ElectionUserPair;
import com.election.hacking.model.GetVoteResponse;
import com.election.hacking.model.Vote;

import java.util.HashMap;
import java.util.Map;

import static com.election.hacking.ServiceConstants.TOKEN;

public class VotingKeyValueStore {
    private static VotingKeyValueStore sVotingKeyValueStore;

    public static synchronized VotingKeyValueStore getInstance() {
        if (sVotingKeyValueStore == null) {
            sVotingKeyValueStore = new VotingKeyValueStore();
        }
        return sVotingKeyValueStore;
    }

    private final Map<ElectionUserPair, Integer> mKeyValueStore;

    public VotingKeyValueStore() {
        mKeyValueStore = new HashMap<>();
        ServiceClient
                .getInstance()
                .getVotes(TOKEN, new ServiceClient.LogErrorCallback<GetVoteResponse>() {
                    @Override
                    public void onSuccess(final GetVoteResponse result) {
                        for (final Vote vote : result.getVotes()) {
                            update(vote.getToken(), vote.getElectionId(), vote.getPoliticianId());
                        }
                    }
                });
    }

    public synchronized void vote(final Activity activity, final String userToken, final int electionId, final int politicianId) {
        ServiceClient
                .getInstance()
                .vote(userToken, electionId, politicianId, new ServiceClient.LogErrorCallback<Void>() {
                    @Override
                    public void onSuccess(final Void result) {
                        update(userToken, electionId, politicianId);

                        activity.finish();
                    }
                });
    }

    private void update(final String userToken, final int electionId, final int politicianId) {
        final ElectionUserPair key = new ElectionUserPair(userToken, electionId);
        mKeyValueStore.put(key, politicianId);
    }

    public synchronized Integer votedFor(final String userToken, final int electionId) {
        final ElectionUserPair electionUserPair = new ElectionUserPair(userToken, electionId);
        return mKeyValueStore.get(electionUserPair);
    }
}
