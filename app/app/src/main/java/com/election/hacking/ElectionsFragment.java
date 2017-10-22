package com.election.hacking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.election.hacking.model.Election;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class ElectionsFragment extends Fragment {
    public static final String FRAGMENT_TAG = "elections";

    private ElectionAdapter electionAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        electionAdapter = ((MainActivity) getActivity()).getElectionAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_elections, container, false);

        FeatureCoverFlow coverFlow = (FeatureCoverFlow) rootView.findViewById(R.id.coverflow);
        coverFlow.setAdapter(electionAdapter);
        coverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Address bug in CoverFlow library
                if (electionAdapter.getElections().size() == 1) {
                    position = 0;
                }

                Election election = (Election) electionAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), ElectionActivity.class);
                intent.putExtra(ElectionActivity.KEY_ELECTION, election);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
