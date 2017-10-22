package com.election.hacking;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.election.hacking.model.IdentityVerificationRequest;
import com.election.hacking.model.IdentityVerificationResponse;

import java.text.ParseException;

public class VerifyWithPersonalInfoActivity extends AppCompatActivity {
    private static final String TAG = "VerifyWPersInfActivity";

    private TextView verifyButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_with_personal_info);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        verifyButton = (TextView) findViewById(R.id.verifyButton);

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
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

    private void setVerifyButtonEnabled(boolean enabled) {
        verifyButton.setEnabled(enabled);
        verifyButton.setBackgroundResource(enabled ? android.R.color.white : R.color.gray);
        verifyButton.setText(enabled ? R.string.login : R.string.loading);
    }

    private void verify() {
        setVerifyButtonEnabled(false);

        final String ssn = getTextFromEditText(R.id.ssn);
        final String dateOfBirth = getTextFromEditText(R.id.dateOfBirth);
        final String lastName = getTextFromEditText(R.id.lastName);

        if (ssn.isEmpty() || dateOfBirth.isEmpty() || lastName.isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), "Please fill in all fields.", Snackbar.LENGTH_SHORT).show();
            setVerifyButtonEnabled(true);
        } else {
            try {
                long ssnLong = Long.parseLong(ssn);

                ServiceClient
                        .getInstance()
                        .verifyIdentity(new IdentityVerificationRequest.Builder()
                                .withSsn(ssnLong)
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
                                setVerifyButtonEnabled(true);
                            }
                        });
            } catch (NumberFormatException e) {
                setVerifyButtonEnabled(true);
                Snackbar.make(findViewById(android.R.id.content), "Please enter only numbers into SSN field.", Snackbar.LENGTH_SHORT).show();
            } catch (ParseException e) {
                setVerifyButtonEnabled(true);
                Snackbar.make(findViewById(android.R.id.content), "Please date of birth in the format 'mm/dd/yyyy'.", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    private String getTextFromEditText(@IdRes final int id) {
        EditText viewById = (EditText) findViewById(id);
        Editable text = viewById.getText();
        return text != null ? text.toString().trim() : "";
    }
}
