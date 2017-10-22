package com.election.hacking.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

import static com.election.hacking.ServiceConstants.KEY_BALANCE;

public class GetRewardsResponse implements Serializable {
    @SerializedName(KEY_BALANCE)
    private long mBalance;

    public long getBalance() {
        return mBalance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mBalance", mBalance)
                .toString();
    }
}
