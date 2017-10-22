package com.election.hacking;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class VerifyWithCapitalOneWebActivity extends Activity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final WebView view = new WebView(this);
        view.setWebViewClient(new InterceptSuccessToMainActivityClient());
        view.loadUrl(getIntent().getDataString());
        setContentView(view);
    }
}
