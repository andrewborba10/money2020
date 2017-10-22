package com.election.hacking;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InterceptSuccessToMainActivityClient extends WebViewClient {
    private static final String TAG = "VerifyWithCapitalOneWebViewClient";

    @Override
    public void onPageStarted(final WebView view, final String url, final Bitmap favicon) {
        try {
            final Uri uri = Uri.parse(url);
            final String scheme = uri.getScheme();
            if ("election-hacking".equals(scheme)) {
                Log.d(TAG, "Sending intent for URL: " + uri);

                MainActivity.start(view.getContext());

                ((Activity) view.getContext()).finish();
            }
        } catch(final ActivityNotFoundException e) {
            Log.e(TAG, "Could not load url" + url);
        }
    }
}
