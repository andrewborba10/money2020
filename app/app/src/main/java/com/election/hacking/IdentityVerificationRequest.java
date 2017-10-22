package com.election.hacking;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.election.hacking.ServiceConstants.KEY_DATE_OF_BIRTH;
import static com.election.hacking.ServiceConstants.KEY_LAST_NAME;
import static com.election.hacking.ServiceConstants.KEY_SSN;

public class IdentityVerificationRequest implements Serializable {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("mm/dd/yyyy", Locale.US);

    @SerializedName(KEY_SSN)
    public final long mSsn;

    @SerializedName(KEY_DATE_OF_BIRTH)
    public final String mDateOfBirth;

    @SerializedName(KEY_LAST_NAME)
    public final String mLastName;

    public IdentityVerificationRequest(final Builder builder) {
        mSsn = builder.mSsn;
        mDateOfBirth = DATE_FORMAT.format(builder.mDateOfBirth);
        mLastName = builder.mLastName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mSsn", mSsn)
                .append("mDateOfBirth", mDateOfBirth)
                .append("mLastName", mLastName)
                .toString();
    }

    public static class Builder {
        private String mLastName;
        private Date mDateOfBirth;
        private long mSsn;

        public Builder withSsn(final long ssn) {
            mSsn = ssn;
            return this;
        }

        public Builder withDateOfBirth(final Date date) {
            mDateOfBirth = date;
            return this;
        }

        public Builder withLastName(final String lastName) {
            mLastName = lastName;
            return this;
        }

        public IdentityVerificationRequest build() {
            return new IdentityVerificationRequest(this);
        }
    }
}
