package com.election.hacking;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ElectionAdapter electionAdapter;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView electionsButton = (TextView) findViewById(R.id.electionsButton);
        TextView causesButton = (TextView) findViewById(R.id.causesButton);
        TextView aboutButton = (TextView) findViewById(R.id.aboutButton);

        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

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
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveFragment(new AboutFragment(), AboutFragment.FRAGMENT_TAG);
            }
        });

        setActiveFragment(new ElectionsFragment(), ElectionsFragment.FRAGMENT_TAG);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setActiveFragment(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment, tag);
        ft.commit();
        drawerLayout.closeDrawers();
    }

    public ElectionAdapter getElectionAdapter() {
        return electionAdapter;
    }
}
