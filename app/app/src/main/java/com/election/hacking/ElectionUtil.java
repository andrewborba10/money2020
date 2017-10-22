package com.election.hacking;

import android.support.annotation.DrawableRes;

public class ElectionUtil {
    @DrawableRes
    public static int getDrawableForCandidateParty(Candidate candidate) {
        int candidateDrawableId = R.drawable.ic_person_gray;
        String party = candidate.getParty();
        if (party != null) {
            if (party.toLowerCase().equals("democratic")) {
                candidateDrawableId = R.drawable.ic_person_blue;
            } else if (party.toLowerCase().equals("republican")) {
                candidateDrawableId = R.drawable.ic_person_red;
            }
        }
        return candidateDrawableId;
    }
}