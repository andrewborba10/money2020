package com.election.hacking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate(1, "person 1", "democratic"));
        candidates.add(new Candidate(1, "person 2", "republican"));

        List<Election> elections = new ArrayList<>();
        elections.add(new Election(0, "title", "description", "10/10/1010", "02/02/2020", candidates));

        ElectionAdapter electionAdapter = new ElectionAdapter(this, elections);

        FeatureCoverFlow coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        coverFlow.setAdapter(electionAdapter);
    }
}
