package com.election.hacking.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

import static com.election.hacking.ServiceConstants.KEY_VOTES;

public class GetVoteResponse implements Serializable {
    @SerializedName(KEY_VOTES)
    private List<Vote> mVotes;

    public List<Vote> getVotes() {
        return mVotes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mVotes", mVotes)
                .toString();
    }
}
