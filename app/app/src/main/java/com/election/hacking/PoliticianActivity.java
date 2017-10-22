package com.election.hacking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.election.hacking.model.Organization;
import com.election.hacking.model.Politician;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PoliticianActivity extends AppCompatActivity {
    public static final String KEY_POLITICIAN = "politician";

    private OrganizationAdapter organizationAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_politician);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Candidate Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView politicianIcon = (ImageView) findViewById(R.id.politicianIcon);
        ImageView politicianImage = (ImageView) findViewById(R.id.politicianImage);
        TextView politicianName = (TextView) findViewById(R.id.politicianName);
        TextView politicianParty = (TextView) findViewById(R.id.politicianParty);
        ListView relatedCausesList = (ListView) findViewById(R.id.relatedCausesList);

        Politician politician = (Politician) getIntent().getSerializableExtra(KEY_POLITICIAN);
        if (politician != null) {
            if (politician.getImageUrl() != null) {
                politicianIcon.setVisibility(View.GONE);
                Picasso
                        .with(this)
                        .load(politician.getImageUrl())
                        .fit()
                        .into(politicianImage);
            } else {
                politicianIcon.setVisibility(View.VISIBLE);
            }

            politicianName.setText(politician.getName());
            politicianParty.setText(politician.getParty());
        }

        organizationAdapter = new OrganizationAdapter(this, new ArrayList<Organization>()); // TODO
        relatedCausesList.setAdapter(organizationAdapter);
        relatedCausesList.setEmptyView(findViewById(R.id.causesListEmptyView));
        relatedCausesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Organization organization = (Organization) organizationAdapter.getItem(position);
                Intent intent = new Intent(PoliticianActivity.this, OrganizationActivity.class);
                intent.putExtra(OrganizationActivity.KEY_ORGANIZATION, organization);
                startActivity(intent);
            }
        });
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
