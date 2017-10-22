package com.election.hacking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.election.hacking.model.Developer;

import java.util.List;

public class AboutFragment extends Fragment {
    public static final String FRAGMENT_TAG = "about";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

        LinearLayout developerListLayout = (LinearLayout) rootView.findViewById(R.id.developerListLayout);

//        List<Developer> candidates = election.getPoliticians();

        return rootView;
    }
}
