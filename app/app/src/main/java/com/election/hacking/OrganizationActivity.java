package com.election.hacking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.election.hacking.model.Organization;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrganizationActivity extends AppCompatActivity {
    public static final String KEY_ORGANIZATION = "organization";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_organization);

        ImageView organizationImage = (ImageView) findViewById(R.id.organizationImage);
        TextView organizationName = (TextView) findViewById(R.id.organizationName);
        TextView organizationDescription = (TextView) findViewById(R.id.organizationDescription);
        TextView totalDonationsToDate = (TextView) findViewById(R.id.totalDonationsToDate);
        TextView organizationTags = (TextView) findViewById(R.id.organizationTags);
        TextView selectCauseButton = (TextView) findViewById(R.id.selectCauseButton);

        Organization organization = (Organization) getIntent().getSerializableExtra(KEY_ORGANIZATION);
        if (organization != null) {
            if (organizationImage != null) {
                Picasso
                        .with(this)
                        .load(organization.getOrganizationImageUrl())
                        .into(organizationImage);
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
            public void onClick(View v) {
                // TODO
            }
        });
    }
}
