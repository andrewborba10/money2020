package com.election.hacking;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ElectionAdapter electionAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView electionsButton = (TextView) findViewById(R.id.electionsButton);
        TextView causesButton = (TextView) findViewById(R.id.causesButton);
        TextView aboutButton = (TextView) findViewById(R.id.aboutButton);

        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate(1, "person 1", "democratic"));
        candidates.add(new Candidate(1, "person 2", "republican"));

        List<Election> elections = new ArrayList<>();
        elections.add(new Election(0, "title1", "description", "10/10/1010", "02/02/2020", candidates));
        elections.add(new Election(0, "title1", "description", "10/10/1010", "02/02/2020", candidates));

        electionAdapter = new ElectionAdapter(this, elections);

        electionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveFragment(new ElectionsFragment(), ElectionsFragment.FRAGMENT_TAG);
            }
        });
        causesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveFragment(new CausesFragment(), CausesFragment.FRAGMENT_TAG);
            }
        });

        setActiveFragment(new ElectionsFragment(), ElectionsFragment.FRAGMENT_TAG);
    }


    public void setActiveFragment(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment, tag);
        ft.commit();
    }

    public ElectionAdapter getElectionAdapter() {
        return electionAdapter;
    }
}
