package com.election.hacking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.election.hacking.model.GetUserInformationResponse;
import com.election.hacking.model.Organization;
import com.election.hacking.model.User;

import static com.election.hacking.ServiceConstants.TOKEN;

public class CausesFragment extends Fragment {
    public static final String FRAGMENT_TAG = "causes";
    public static final String KEY_TOTAL_DONATIONS = "totalDonations";
    private static final String TAG = "CausesFragment";

    private OrganizationAdapter organizationAdapter;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_causes, container, false);

        organizationAdapter =((MainActivity)getActivity()).getOrganizationAdapter();

        final ListView availableCausesList = (ListView) rootView.findViewById(R.id.availableCausesList);
        availableCausesList.setEmptyView(rootView.findViewById(R.id.causesListEmptyView));
        availableCausesList.setAdapter(organizationAdapter);
        availableCausesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Organization organization = (Organization) organizationAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), OrganizationActivity.class);
                intent.putExtra(OrganizationActivity.KEY_ORGANIZATION, organization);
                startActivity(intent);
            }
        });

        ServiceClient
                .getInstance()
                .getUserInformation(TOKEN, new ServiceClient.Callback<GetUserInformationResponse>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(final GetUserInformationResponse result) {
                        final TextView viewById = (TextView) rootView.findViewById(R.id.myDonationsToDate);
                        final User user = result.getUser();
                        if (user != null) {
                            viewById.setText("$" + String.valueOf(user.getTotalDonated()));
                        }
                    }

                    @Override
                    public void onError(final Exception e) {
                        Log.e(TAG, "Failed to load user information", e);
                    }
                });

        return rootView;
    }
}
