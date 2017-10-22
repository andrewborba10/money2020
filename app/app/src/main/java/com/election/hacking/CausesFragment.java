package com.election.hacking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CausesFragment extends Fragment {
    public static final String FRAGMENT_TAG = "causes";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_causes, container, false);

        ListView availableCausesList = (ListView) rootView.findViewById(R.id.availableCausesList);
        availableCausesList.setEmptyView(rootView.findViewById(R.id.causesListEmptyView));
        availableCausesList.setAdapter(((MainActivity)getActivity()).getOrganizationAdapter());

        return rootView;
    }
}
