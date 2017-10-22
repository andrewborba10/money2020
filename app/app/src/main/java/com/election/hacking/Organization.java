package com.election.hacking;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

import static com.election.hacking.ServiceConstants.KEY_ORGANIZATION_DESCRIPTION;
import static com.election.hacking.ServiceConstants.KEY_ORGANIZATION_ID;
import static com.election.hacking.ServiceConstants.KEY_ORGANIZATION_TITLE;

public class Organization implements Serializable {

    @SerializedName(KEY_ORGANIZATION_ID)
    private String mOrganizationId;

    @SerializedName(KEY_ORGANIZATION_TITLE)
    private String mOrganizationTitle;

    @SerializedName(KEY_ORGANIZATION_DESCRIPTION)
    private String mOrganizationDescription;

    public String getOrganizationId() {
        return mOrganizationId;
    }

    public String getOrganizationTitle() {
        return mOrganizationTitle;
    }

    public String getOrganizationDescription() {
        return mOrganizationDescription;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mOrganizationId", mOrganizationId)
                .append("mOrganizationTitle", mOrganizationTitle)
                .append("mOrganizationDescription", mOrganizationDescription)
                .toString();
    }
}
