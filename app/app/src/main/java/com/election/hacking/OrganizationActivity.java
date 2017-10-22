package com.election.hacking;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.election.hacking.model.Organization;
import com.election.hacking.model.PledgeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.election.hacking.CausesFragment.REQUEST_CODE;
import static com.election.hacking.ServiceConstants.TOKEN;

public class OrganizationActivity extends AppCompatActivity {
    public static final String KEY_ORGANIZATION = "organization";
    private static final String TAG = "OrganizationActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_organization);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Cause Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FrameLayout organizationImageContainer = (FrameLayout) findViewById(R.id.organizationImageContainer);
        ImageView organizationImage = (ImageView) findViewById(R.id.organizationImage);
        ImageView organizationHeartIcon = (ImageView) findViewById(R.id.organizationHeartIcon);
        TextView organizationName = (TextView) findViewById(R.id.organizationName);
        TextView organizationDescription = (TextView) findViewById(R.id.organizationDescription);
        TextView totalDonationsToDate = (TextView) findViewById(R.id.totalDonationsToDate);
        TextView organizationTags = (TextView) findViewById(R.id.organizationTags);
        TextView selectCauseButton = (TextView) findViewById(R.id.selectCauseButton);

        final Organization organization = (Organization) getIntent().getSerializableExtra(KEY_ORGANIZATION);
        if (organization != null) {
            if (organization.getOrganizationImageUrl() != null) {
                organizationImageContainer.setBackgroundColor(Color.WHITE);
                organizationHeartIcon.setVisibility(View.GONE);
                Picasso
                        .with(this)
                        .load(organization.getOrganizationImageUrl())
                        .fit()
                        .into(organizationImage);
            } else {
                organizationImageContainer.setBackgroundColor(Color.TRANSPARENT);
                organizationHeartIcon.setVisibility(View.VISIBLE);
            }

            organizationName.setText(organization.getOrganizationTitle());
            organizationDescription.setText(organization.getOrganizationDescription());
            totalDonationsToDate.setText(organization.getTotalDonations());

            List<String> tags = organization.getOrganizationTags();
            if (tags != null && tags.size() > 0) {
                organizationTags.setText(TextUtils.join(", ", tags));
            } else {
                organizationTags.setText(R.string.no_tags_available);
            }
        }

        selectCauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ServiceClient
                        .getInstance()
                        .pledge(TOKEN, organization.getOrganizationId(), new ServiceClient.Callback<PledgeResponse>() {
                            @Override
                            public void onSuccess(final PledgeResponse result) {
                                final Intent intent = new Intent();
                                intent.putExtra(CausesFragment.KEY_ORGANIZATION, result.getOrganization());
                                setResult(REQUEST_CODE, intent);
                                finish();
                            }

                            @Override
                            public void onError(final Exception e) {
                                Log.e(TAG, "Failed to pledge", e);
                            }
                        });
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
