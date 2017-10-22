package com.election.hacking;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

import static com.election.hacking.ServiceConstants.KEY_ELECTION_DATE_CLOSED;
import static com.election.hacking.ServiceConstants.KEY_ELECTION_DATE_OPEN;
import static com.election.hacking.ServiceConstants.KEY_ELECTION_DESCRIPTION;
import static com.election.hacking.ServiceConstants.KEY_ELECTION_ID;
import static com.election.hacking.ServiceConstants.KEY_ELECTION_IS_OPEN;
import static com.election.hacking.ServiceConstants.KEY_ELECTION_TITLE;
import static com.election.hacking.ServiceConstants.KEY_POLITICIANS;

public class Election implements Serializable {

    @SerializedName(KEY_ELECTION_ID)
    private int mElectionId;

    @SerializedName(KEY_POLITICIANS)
    private List<Politician> mPoliticians;

    @SerializedName(KEY_ELECTION_TITLE)
    private String mTitle;

    @SerializedName(KEY_ELECTION_DESCRIPTION)
    private String mDescription;

    @SerializedName(KEY_ELECTION_DATE_OPEN)
    private String mDateOpen;

    @SerializedName(KEY_ELECTION_DATE_CLOSED)
    private String mDateClosed;

    @SerializedName(KEY_ELECTION_IS_OPEN)
    private boolean mIsOpen;

    public int getElectionId() {
        return mElectionId;
    }

    public List<Politician> getPoliticians() {
        return mPoliticians;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getDateOpen() {
        return mDateOpen;
    }

    public String getDateClosed() {
        return mDateClosed;
    }

    public boolean getIsOpen() {
        return mIsOpen;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mElectionId", mElectionId)
                .append("mPoliticians", mPoliticians)
                .append("mTitle", mTitle)
                .append("mDescription", mDescription)
                .append("mDateOpen", mDateOpen)
                .append("mDateClosed", mDateClosed)
                .append("mIsOpen", mIsOpen)
                .toString();
    }
}
