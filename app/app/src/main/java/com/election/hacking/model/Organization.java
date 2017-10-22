package com.election.hacking.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

import static com.election.hacking.ServiceConstants.KEY_ORGANIZATION_DESCRIPTION;
import static com.election.hacking.ServiceConstants.KEY_ORGANIZATION_ID;
import static com.election.hacking.ServiceConstants.KEY_ORGANIZATION_IMAGE_URL;
import static com.election.hacking.ServiceConstants.KEY_ORGANIZATION_TAGS;
import static com.election.hacking.ServiceConstants.KEY_ORGANIZATION_TITLE;
import static com.election.hacking.ServiceConstants.KEY_ORGANIZATION_TOTAL_DONATIONS;

public class Organization implements Serializable {

    @SerializedName(KEY_ORGANIZATION_ID)
    private String mOrganizationId;

    @SerializedName(KEY_ORGANIZATION_TITLE)
    private String mOrganizationTitle;

    @SerializedName(KEY_ORGANIZATION_DESCRIPTION)
    private String mOrganizationDescription;

    @SerializedName(KEY_ORGANIZATION_TOTAL_DONATIONS)
    private String mOrganizationTotalDonations;

    @SerializedName(KEY_ORGANIZATION_IMAGE_URL)
    private String mOrganizationImageUrl;

    @SerializedName(KEY_ORGANIZATION_TAGS)
    private List<String> mOrganizationTags;

    public String getOrganizationId() {
        return mOrganizationId;
    }

    public String getOrganizationTitle() {
        return mOrganizationTitle;
    }

    public String getOrganizationDescription() {
        return mOrganizationDescription;
    }

    public String getTotalDonations() {
        return mOrganizationImageUrl;
    }

    public String getOrganizationImageUrl() {
        return mOrganizationImageUrl;
    }

    public List<String> getOrganizationTags() {
        return mOrganizationTags;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mOrganizationId", mOrganizationId)
                .append("mOrganizationTitle", mOrganizationTitle)
                .append("mOrganizationDescription", mOrganizationDescription)
                .append("mOrganizationTotalDonations", mOrganizationTotalDonations)
                .append("mOrganizationImageUrl", mOrganizationImageUrl)
                .append("mOrganizationTags", mOrganizationTags)
                .toString();
    }
}
