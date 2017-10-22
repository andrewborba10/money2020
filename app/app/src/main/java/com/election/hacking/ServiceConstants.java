package com.election.hacking;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ServiceConstants {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("mm/dd/yyyy", Locale.US);
    public static Date parseDate(final String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (final ParseException e) {
            throw new RuntimeException("Failed to deserialize time: " + date, e);
        }
    }

    // Verify API
    public static final String KEY_SSN = "ssn";
    public static final String KEY_DATE_OF_BIRTH = "dateOfBirth";
    public static final String KEY_LAST_NAME = "lastName";
    public static final String KEY_TOKEN = "token";

    // Button API
    public static final String KEY_URL = "url";

    // Elections API
    public static final String KEY_ELECTIONS = "elections";

    // Organizations API
    public static final String KEY_ORGANIZATIONS = "organizations";

    // Politician
    public static final String KEY_POLITICIAN_ID = "personId";
    public static final String KEY_POLITICIAN_NAME = "name";
    public static final String KEY_POLITICIAN_PARTY = "party";
    public static final String KEY_POLITICIAN_IMAGE_URL = "imageUrl";

    // Election
    public static final String KEY_ELECTION_ID = "electionId";
    public static final String KEY_POLITICIANS = "politicians";
    public static final String KEY_ELECTION_TITLE = "title";
    public static final String KEY_ELECTION_DESCRIPTION = "description";
    public static final String KEY_ELECTION_DATE_OPEN = "dateOpen";
    public static final String KEY_ELECTION_DATE_CLOSED = "dateClosed";
    public static final String KEY_ELECTION_IS_OPEN = "isOpen";

    // Organization
    public static final String KEY_ORGANIZATION_ID = "organizationId";
    public static final String KEY_ORGANIZATION_TITLE = "title";
    public static final String KEY_ORGANIZATION_DESCRIPTION = "description";
    public static final String KEY_ORGANIZATION_TOTAL_DONATIONS = "totalDonations";
    public static final String KEY_ORGANIZATION_IMAGE_URL = "imageUrl";
}
