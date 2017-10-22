package com.election.hacking;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.election.hacking.model.GetButtonResponse;

public class LoginActivity extends Activity {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ServiceClient
                .getInstance()
                .getButton(new ServiceClient.Callback<GetButtonResponse>() {
                    @Override
                    public void onSuccess(final GetButtonResponse result) {
                        configureButtonWithUrl(result.getUrl());
                    }

                    @Override
                    public void onError(final Exception e) {
                        Log.e(TAG, "Failed to get button", e);
                    }
                });
        configureVerifyWithPersonalInfoButton();
    }

    private void configureVerifyWithPersonalInfoButton() {
        final View viewById = findViewById(R.id.verifyWithPersonalInformation);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                startActivity(new Intent(view.getContext(), VerifyWithPersonalInfoActivity.class));
            }
        });
    }

    private void configureButtonWithUrl(final String url) {
        final View viewById = findViewById(R.id.verifyWithCapitalOne);
        viewById.setTag(url);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                onClickVerifyWithCapitalOne(view);
            }
        });
    }

    public void onClickVerifyWithCapitalOne(final View view) {
        final String tag = (String) view.getTag();
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(tag));
        startActivity(intent);
    }
}
