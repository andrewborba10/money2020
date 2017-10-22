package com.election.hacking;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.election.hacking.model.Organization;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.election.hacking.ServiceConstants.TOKEN;

public class OrganizationActivity extends AppCompatActivity {
    public static final String KEY_ORGANIZATION = "organization";

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
            if (organizationImage != null) {
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
                        .pledge(TOKEN, organization.getOrganizationId(), new ServiceClient.Callback<Void>() {
                            @Override
                            public void onSuccess(final Void result) {
                                Toast.makeText(
                                        OrganizationActivity.this,
                                        "You pledged your cash back rewards to " + organization.getOrganizationTitle() + "!",
                                        Toast.LENGTH_LONG)
                                .show();
                            }

                            @Override
                            public void onError(final Exception e) {
                                Toast.makeText(
                                        OrganizationActivity.this,
                                        "Failed to pledge your cash back rewards",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
            }
        });
    }
}
