package com.election.hacking;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

import static com.election.hacking.ServiceConstants.KEY_ELECTIONS;

public class GetElectionsResponse implements Serializable {
    @SerializedName(KEY_ELECTIONS)
    private List<Election> mElections;

    public List<Election> getElections() {
        return mElections;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mElections", mElections)
                .toString();
    }
}
