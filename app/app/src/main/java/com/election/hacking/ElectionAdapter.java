package com.election.hacking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.election.hacking.model.Election;
import com.election.hacking.model.Politician;

import java.util.List;

public class ElectionAdapter extends BaseAdapter {

    private Context context;
    private List<Election> elections;

    public ElectionAdapter(final Context context, final List<Election> elections) {
        this.context = context;
        this.elections = elections;
    }

    @Override
    public int getCount() {
        return elections.size();
    }

    @Override
    public Object getItem(int position) {
        return elections.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_election, null);

            holder = new ViewHolder();
            holder.electionTitle = (TextView) convertView.findViewById(R.id.electionTitle);
            holder.electionDescription = (TextView) convertView.findViewById(R.id.electionDescription);
            holder.electionStartDate = (TextView) convertView.findViewById(R.id.electionStartDate);
            holder.electionEndDate = (TextView) convertView.findViewById(R.id.electionEndDate);
            holder.candidateListLayout = (LinearLayout) convertView.findViewById(R.id.candidateListLayout);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.electionTitle.setText(elections.get(position).getTitle());
        holder.electionDescription.setText(elections.get(position).getDescription());
        holder.electionStartDate.setText(elections.get(position).getDateOpen());
        holder.electionEndDate.setText(elections.get(position).getDateClosed());
        holder.candidateListLayout.removeAllViews();

        List<Politician> candidates = elections.get(position).getPoliticians();
        if (candidates != null) {
            for (int i = 0; i < candidates.size(); i++){
                Politician candidate = candidates.get(i);
                View candidateView = LayoutInflater.from(context).inflate(R.layout.item_election_candidate, null);
                TextView candidateName = (TextView) candidateView.findViewById(R.id.candidateName);
                TextView candidateParty = (TextView) candidateView.findViewById(R.id.candidateParty);
                ImageView candidateImage = (ImageView) candidateView.findViewById(R.id.candidateImage);

                candidateImage.setBackgroundResource(ElectionUtil.getDrawableForCandidateParty(candidate));
                candidateName.setText(candidate.getName());
                candidateParty.setText(candidate.getParty());
                holder.candidateListLayout.addView(candidateView);
            }
        }

        return convertView;
    }

    public List<Election> getElections() {
        return elections;
    }

    private class ViewHolder {
        TextView electionTitle;
        TextView electionDescription;
        TextView electionStartDate;
        TextView electionEndDate;
        LinearLayout candidateListLayout;
    }
}
