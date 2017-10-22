package com.election.hacking;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.election.hacking.model.GetElectionsResponse;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ElectionAdapter electionAdapter;
    private OrganizationAdapter organizationAdapter;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    public static void start(final Context context) {
        final Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TASK | FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

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

        ServiceClient
                .getInstance()
                .getElections(new ServiceClient.Callback<GetElectionsResponse>() {
                    @Override
                    public void onSuccess(final GetElectionsResponse result) {
                        electionAdapter = new ElectionAdapter(MainActivity.this, result.getElections());
                        setActiveFragment(new ElectionsFragment(), ElectionsFragment.FRAGMENT_TAG);
                    }

                    @Override
                    public void onError(final Exception e) {
                        Log.e(TAG, "Failed to load election data");
                    }
                });

        List<String> tags = new ArrayList<>();
        tags.add("ASDF");
        tags.add("ASDFA");
        tags.add("ASDFD");
        tags.add("ASDFF");
        tags.add("ASDFW");

        List<Organization> organizations = new ArrayList<>();
        organizations.add(new Organization(tags, "asdfasdf", "organization name", "this is a description", "$0.01", "$10.01"));
        organizations.add(new Organization(tags, "asdfasdf", "organization name", "this is a description", "$0.01", "$10.01"));
        organizations.add(new Organization(tags, "asdfasdf", "organization name", "this is a description", "$0.01", "$10.01"));
        organizations.add(new Organization(tags, "asdfasdf", "organization name", "this is a description", "$0.01", "$10.01"));
        organizations.add(new Organization(tags, "asdfasdf", "organization name", "this is a description", "$0.01", "$10.01"));
        organizations.add(new Organization(tags, "asdfasdf", "organization name", "this is a description", "$0.01", "$10.01"));
        organizations.add(new Organization(tags, "asdfasdf", "organization name", "this is a description", "$0.01", "$10.01"));

        organizationAdapter = new OrganizationAdapter(this, organizations);

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

    public OrganizationAdapter getOrganizationAdapter() {
        return organizationAdapter;
    }
}
