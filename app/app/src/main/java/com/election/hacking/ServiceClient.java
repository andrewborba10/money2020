package com.election.hacking;

import android.os.AsyncTask;
import android.support.annotation.MainThread;

import com.google.gson.Gson;
import com.url.utils.Request;
import com.url.utils.Response;
import com.url.utils.UrlClient;

import static com.election.hacking.ServiceConstants.KEY_DATE_OF_BIRTH;
import static com.election.hacking.ServiceConstants.KEY_LAST_NAME;
import static com.election.hacking.ServiceConstants.KEY_SSN;


public class ServiceClient {

    private static String TAG = "ServiceClient";
    private static ServiceClient sServiceClient;

    public static synchronized ServiceClient getInstance() {
        if (sServiceClient == null) {
            sServiceClient = new ServiceClient();
        }
        return sServiceClient;
    }

    private final Gson mGson = new Gson();

    public void verifyIdentity(final IdentityVerificationRequest request,
                               final Callback<IdentityVerificationResponse> callback) {
        final Request urlRequest = UrlClient
                .create()
                .post("http://10.101.1.208:3000/verify")
                .param(KEY_SSN, request.mSsn)
                .param(KEY_DATE_OF_BIRTH, request.mDateOfBirth)
                .param(KEY_LAST_NAME, request.mLastName)
                .ensureSuccess();

        new ServiceCallTask<>(urlRequest, IdentityVerificationResponse.class, callback)
                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getButton(final Callback<GetButtonResponse> callback) {
        final Request urlRequest = UrlClient
                .create()
                .get("http://10.101.1.208:3000/button")
                .ensureSuccess();

        new ServiceCallTask<>(urlRequest, GetButtonResponse.class, callback)
                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getElections(final Callback<GetElectionsResponse> callback) {
        final Request urlRequest = UrlClient
                .create()
                .get("http://10.101.1.208:3000/elections")
                .ensureSuccess();

        new ServiceCallTask<>(urlRequest, GetElectionsResponse.class, callback)
                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getOrganizations(final Callback<GetOrganizationsResponse> callback) {
        final Request urlRequest = UrlClient
                .create()
                .get("http://10.101.1.208:3000/organizations")
                .ensureSuccess();

        new ServiceCallTask<>(urlRequest, GetOrganizationsResponse.class, callback)
                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void getOrganizations(final int politicianId,
                                 final Callback<GetOrganizationsResponse> callback) {
        final Request urlRequest = UrlClient
                .create()
                .get("http://10.101.1.208:3000/organizations/" + politicianId)
                .ensureSuccess();

        new ServiceCallTask<>(urlRequest, GetOrganizationsResponse.class, callback)
                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class ServiceCallTask<T> extends AsyncTask<Object, Void, T> {

        private final Request mRequest;
        private final Class<T> mResponseClass;
        private final Callback<T> mCallback;

        private ServiceCallTask(final Request request,
                                final Class<T> responseClass,
                                final Callback<T> callback) {
            mRequest = request;
            mResponseClass = responseClass;
            mCallback = callback;
        }

        private Response<String> getResponse() {
            try {
                return mRequest.asString();
            } catch (final Exception e) {
                mCallback.onError(e);
            }
            return null;
        }

        @Override
        protected T doInBackground(final Object... params) {
            final Response<String> response = getResponse();
            if (response == null) {
                throw new RuntimeException("Couldn't retrieve response");
            }

            return mGson.fromJson(response.getBody(), mResponseClass);
        }

        @MainThread
        protected void onPostExecute(final T result) {
            mCallback.onSuccess(result);
        }
    }

    public interface Callback<T> {
        void onSuccess(final T result);
        void onError(final Exception e);
    }
}
