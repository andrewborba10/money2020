package com.election.hacking;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

import static com.election.hacking.ServiceConstants.KEY_TOKEN;

public class IdentityVerificationResponse implements Serializable {
    @SerializedName(KEY_TOKEN)
    private String mToken;

    public String getToken() {
        return mToken;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mToken", mToken)
                .toString();
    }
}
