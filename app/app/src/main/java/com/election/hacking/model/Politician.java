package com.election.hacking.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

import static com.election.hacking.ServiceConstants.KEY_POLITICIAN_PARTY;
import static com.election.hacking.ServiceConstants.KEY_POLITICIAN_NAME;
import static com.election.hacking.ServiceConstants.KEY_POLITICIAN_ID;

public class Politician implements Serializable {
    @SerializedName(KEY_POLITICIAN_ID)
    private int mPersonId;

    @SerializedName(KEY_POLITICIAN_NAME)
    private String mName;

    @SerializedName(KEY_POLITICIAN_PARTY)
    private String mParty;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mPersonId", mPersonId)
                .append("mName", mName)
                .append("mParty", mParty)
                .toString();
    }

    public String getName() {
        return mName;
    }

    public String getParty() {
        return mParty;
    }
}
