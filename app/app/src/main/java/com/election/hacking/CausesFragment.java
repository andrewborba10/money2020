package com.election.hacking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.election.hacking.model.Organization;

public class CausesFragment extends Fragment {
    public static final String FRAGMENT_TAG = "causes";

    private OrganizationAdapter organizationAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_causes, container, false);
        organizationAdapter =((MainActivity)getActivity()).getOrganizationAdapter();
        ListView availableCausesList = (ListView) rootView.findViewById(R.id.availableCausesList);
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

        return rootView;
    }
}
