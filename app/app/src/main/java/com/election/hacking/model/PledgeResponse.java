package com.election.hacking.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

import static com.election.hacking.ServiceConstants.KEY_ORGANIZATION;

public class PledgeResponse implements Serializable {
    @SerializedName(KEY_ORGANIZATION)
    private Organization mOrganization;

    public Organization getOrganization() {
        return mOrganization;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mOrganization", mOrganization)
                .toString();
    }
}
