package com.election.hacking.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

import static com.election.hacking.ServiceConstants.KEY_DEVELOPER_ID;
import static com.election.hacking.ServiceConstants.KEY_DEVELOPER_IMAGE_URL;
import static com.election.hacking.ServiceConstants.KEY_DEVELOPER_NAME;
import static com.election.hacking.ServiceConstants.KEY_DEVELOPER_PARTY;
import static com.election.hacking.ServiceConstants.KEY_POLITICIAN_ID;
import static com.election.hacking.ServiceConstants.KEY_POLITICIAN_IMAGE_URL;
import static com.election.hacking.ServiceConstants.KEY_POLITICIAN_NAME;
import static com.election.hacking.ServiceConstants.KEY_POLITICIAN_PARTY;

public class Developer implements Serializable {
    @SerializedName(KEY_DEVELOPER_ID)
    private int mDeveloperId;

    @SerializedName(KEY_DEVELOPER_NAME)
    private String mName;

    @SerializedName(KEY_DEVELOPER_PARTY)
    private String mParty;

    @SerializedName(KEY_DEVELOPER_IMAGE_URL)
    private String mImageUrl;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mPoliticianId", mDeveloperId)
                .append("mName", mName)
                .append("mParty", mParty)
                .append("mImageUrl", mImageUrl)
                .toString();
    }

    public int getDeveloperId() {
        return mDeveloperId;
    }

    public String getName() {
        return mName;
    }

    public String getParty() {
        return mParty;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
