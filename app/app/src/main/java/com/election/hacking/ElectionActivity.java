package com.election.hacking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ElectionActivity extends AppCompatActivity {

    public static final String KEY_ELECTION = "election";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_election);

        TextView electionTitle = (TextView) findViewById(R.id.electionTitle);
        TextView electionDescription = (TextView) findViewById(R.id.electionDescription);
        TextView electionStartDate = (TextView) findViewById(R.id.electionStartDate);
        TextView electionEndDate = (TextView) findViewById(R.id.electionEndDate);
        LinearLayout candidateListLayout = (LinearLayout) findViewById(R.id.candidateListLayout);

        Election election = (Election) getIntent().getSerializableExtra(KEY_ELECTION);
        if (election != null) {
            electionTitle.setText(election.getTitle());
            electionDescription.setText(election.getDescription());
            electionStartDate.setText(election.getDateOpen());
            electionEndDate.setText(election.getDateClosed());
            candidateListLayout.removeAllViews();

            List<Candidate> candidates = election.getCandidates();
            if (candidates != null) {
                for (int i = 0; i < candidates.size(); i++){
                    Candidate candidate = candidates.get(i);
                    View candidateView = LayoutInflater.from(this).inflate(R.layout.item_election_candidate_no_bg, null);
                    TextView candidateName = (TextView) candidateView.findViewById(R.id.candidateName);
                    TextView candidateParty = (TextView) candidateView.findViewById(R.id.candidateParty);
                    ImageView candidateImage = (ImageView) candidateView.findViewById(R.id.candidateImage);

                    candidateImage.setBackgroundResource(ElectionUtil.getDrawableForCandidateParty(candidate));
                    candidateName.setText(candidate.getName());
                    candidateParty.setText(candidate.getParty());
                    candidateListLayout.addView(candidateView);
                }
            }
        }
    }
}
