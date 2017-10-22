package com.election.hacking.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static com.election.hacking.ServiceConstants.KEY_USER;

public class GetUserInformationResponse implements Serializable {
    @SerializedName(KEY_USER)
    private User mUser;

    public User getUser() {
        return mUser;
    }
}
