package com.election.hacking.model;

import com.google.gson.annotations.SerializedName;

import static com.election.hacking.ServiceConstants.KEY_VOTE_ELECTION_ID;
import static com.election.hacking.ServiceConstants.KEY_VOTE_POLITICIAN_ID;
import static com.election.hacking.ServiceConstants.KEY_VOTE_USER_ID;

public class Vote {
    @SerializedName(KEY_VOTE_USER_ID)
    private int mUserId;

    @SerializedName(KEY_VOTE_ELECTION_ID)
    private int mElectionId;

    @SerializedName(KEY_VOTE_POLITICIAN_ID)
    private int mPoliticianId;

    public int getUserId() {
        return mUserId;
    }

    public int getElectionId() {
        return mElectionId;
    }

    public int getPoliticianId() {
        return mPoliticianId;
    }
}
