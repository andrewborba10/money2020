package com.election.hacking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.election.hacking.model.Election;
import com.election.hacking.model.Politician;

import java.util.List;

public class ElectionActivity extends AppCompatActivity {

    public static final String KEY_ELECTION = "election";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_election);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Election Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView electionTitle = (TextView) findViewById(R.id.electionTitle);
        TextView electionDescription = (TextView) findViewById(R.id.electionDescription);
        TextView electionStartDate = (TextView) findViewById(R.id.electionStartDate);
        TextView electionEndDate = (TextView) findViewById(R.id.electionEndDate);
        LinearLayout candidateListLayout = (LinearLayout) findViewById(R.id.candidateListLayout);

        final Election election = (Election) getIntent().getSerializableExtra(KEY_ELECTION);
        if (election != null) {
            electionTitle.setText(election.getTitle());
            electionDescription.setText(election.getDescription());
            electionStartDate.setText(election.getDateOpen());
            electionEndDate.setText(election.getDateClosed());
            candidateListLayout.removeAllViews();

            List<Politician> candidates = election.getPoliticians();
            if (candidates != null) {
                for (int i = 0; i < candidates.size(); i++){
                    final Politician candidate = candidates.get(i);
                    View candidateView = LayoutInflater.from(this).inflate(R.layout.item_election_candidate_no_bg, null);
                    TextView candidateName = (TextView) candidateView.findViewById(R.id.candidateName);
                    TextView candidateParty = (TextView) candidateView.findViewById(R.id.candidateParty);
                    ImageView candidateImage = (ImageView) candidateView.findViewById(R.id.candidateImage);

                    candidateImage.setBackgroundResource(ElectionUtil.getDrawableForCandidateParty(candidate));
                    candidateName.setText(candidate.getName());
                    candidateParty.setText(candidate.getParty());
                    candidateView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(ElectionActivity.this, PoliticianActivity.class);
                            intent.putExtra(PoliticianActivity.KEY_POLITICIAN, candidate);
                            intent.putExtra(PoliticianActivity.KEY_ELECTION_ID, election.getElectionId());
                            startActivity(intent);
                        }
                    });
                    candidateListLayout.addView(candidateView);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
