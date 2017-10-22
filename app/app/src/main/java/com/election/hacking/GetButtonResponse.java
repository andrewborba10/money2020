package com.election.hacking;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.election.hacking.ServiceConstants.KEY_URL;

public class GetButtonResponse {
    @SerializedName(KEY_URL)
    private String mUrl;

    public String getUrl() {
        return mUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mUrl", mUrl)
                .toString();
    }
}
