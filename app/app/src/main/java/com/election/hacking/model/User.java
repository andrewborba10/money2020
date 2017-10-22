package com.election.hacking.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static com.election.hacking.ServiceConstants.KEY_USER_PLEDGED_ORGANIZATION;
import static com.election.hacking.ServiceConstants.KEY_USER_TOTAL_DONATED;

public class User implements Serializable {
    @SerializedName(KEY_USER_PLEDGED_ORGANIZATION)
    private Organization mPledgedOrganization;

    @SerializedName(KEY_USER_TOTAL_DONATED)
    private int mTotalDonated;

    public Organization getPledgedOrganization() {
        return mPledgedOrganization;
    }

    public int getTotalDonated() {
        return mTotalDonated;
    }
}
