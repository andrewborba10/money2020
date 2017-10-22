package com.election.hacking;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.election.hacking.model.GetRewardsResponse;
import com.election.hacking.model.GetUserInformationResponse;
import com.election.hacking.model.Organization;
import com.election.hacking.model.User;

import static com.election.hacking.ServiceConstants.TOKEN;

public class CausesFragment extends Fragment {
    public static final String FRAGMENT_TAG = "causes";
    public static final String KEY_ORGANIZATION = "organization";
    public static final int REQUEST_CODE = 324;
    public static final String KEY_TOTAL_DONATIONS = "totalDonations";
    private static final String TAG = "CausesFragment";

    private OrganizationAdapter organizationAdapter;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_causes, container, false);

        organizationAdapter = ((MainActivity)getActivity()).getOrganizationAdapter();

        final ListView availableCausesList = (ListView) rootView.findViewById(R.id.availableCausesList);
        availableCausesList.setEmptyView(rootView.findViewById(R.id.causesListEmptyView));
        availableCausesList.setAdapter(organizationAdapter);
        availableCausesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Organization organization = (Organization) organizationAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), OrganizationActivity.class);
                intent.putExtra(OrganizationActivity.KEY_ORGANIZATION, organization);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        ServiceClient
                .getInstance()
                .getUserInformation(TOKEN, new ServiceClient.Callback<GetUserInformationResponse>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(final GetUserInformationResponse result) {
                        final TextView donationsView = (TextView) rootView.findViewById(R.id.myDonationsToDate);
                        final User user = result.getUser();
                        if (user == null) {
                            return;
                        }

                        donationsView.setText("$" + String.valueOf(user.getTotalDonated()));
                        setupSelectedCause(user.getPledgedOrganization());
                    }

                    @Override
                    public void onError(final Exception e) {
                        Log.e(TAG, "Failed to load user information", e);
                    }
                });

        ServiceClient
                .getInstance()
                .getRewards(TOKEN, new ServiceClient.LogErrorCallback<GetRewardsResponse>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(final GetRewardsResponse result) {
                        final TextView rewardsView = (TextView) rootView.findViewById(R.id.rewardsBalance);
                        rewardsView.setText("$" + String.valueOf(result.getBalance()));
                    }
                });

        return rootView;
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (requestCode != REQUEST_CODE) {
            return;
        }

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        setupSelectedCause((Organization) data.getSerializableExtra(KEY_ORGANIZATION));
    }

    private void setupSelectedCause(final Organization pledgedOrganization) {
        final View viewById = getActivity().findViewById(R.id.no_cause_selected_text);
        if (pledgedOrganization == null) {
            viewById.setVisibility(View.VISIBLE);
        } else {
            viewById.setVisibility(View.GONE);
            final ViewGroup selectedCauseHolder = (ViewGroup) getActivity().findViewById(R.id.selectedCauseHolder);
            final View view = OrganizationAdapter.bindView(getActivity(), null, pledgedOrganization);
            selectedCauseHolder.removeAllViews();
            selectedCauseHolder.addView(view);
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Thanks for pledging your cash back rewards to " + pledgedOrganization.getOrganizationTitle() + "!", Snackbar.LENGTH_LONG).show();
        }
    }
}
