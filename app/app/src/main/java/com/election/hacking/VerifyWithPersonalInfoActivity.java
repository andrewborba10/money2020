package com.election.hacking;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.election.hacking.model.IdentityVerificationRequest;
import com.election.hacking.model.IdentityVerificationResponse;

public class VerifyWithPersonalInfoActivity extends Activity {
    private static final String TAG = "VerifyWithPersonalInfoActivity";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_with_personal_info);
        configureVerifyButton();
    }

    private void configureVerifyButton() {
        final View viewById = findViewById(R.id.verifyButton);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                verify();
            }
        });
    }

    private void verify() {
        final String ssn = getTextFromEditText(R.id.ssn);
        final String dateOfBirth = getTextFromEditText(R.id.dateOfBirth);
        final String lastName = getTextFromEditText(R.id.lastName);
        ServiceClient
                .getInstance()
                .verifyIdentity(new IdentityVerificationRequest.Builder()
                        .withSsn(Long.parseLong(ssn))
                        .withDateOfBirth(dateOfBirth)
                        .withLastName(lastName)
                        .build(), new ServiceClient.Callback<IdentityVerificationResponse>() {
                    @Override
                    public void onSuccess(final IdentityVerificationResponse result) {
                        MainActivity.start(VerifyWithPersonalInfoActivity.this);
                    }

                    @Override
                    public void onError(final Exception e) {
                        Log.e(TAG, "Failed to verify user: " + e);
                    }
                });
    }

    private String getTextFromEditText(@IdRes final int id) {
        final EditText viewById = (EditText) findViewById(id);
        return viewById.getText().toString();
    }


}
