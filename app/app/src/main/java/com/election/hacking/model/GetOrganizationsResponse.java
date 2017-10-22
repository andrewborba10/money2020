package com.election.hacking.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

import static com.election.hacking.ServiceConstants.KEY_ORGANIZATIONS;

public class GetOrganizationsResponse implements Serializable {
    @SerializedName(KEY_ORGANIZATIONS)
    private List<Organization> mOrganizations;

    public List<Organization> getOrganizations() {
        return mOrganizations;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mOrganizations", mOrganizations)
                .toString();
    }
}
