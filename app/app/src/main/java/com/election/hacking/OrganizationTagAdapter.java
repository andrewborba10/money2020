package com.election.hacking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class OrganizationTagAdapter extends RecyclerView.Adapter<OrganizationTagAdapter.ViewHolder> {
    private List<String> tags;
    private Context context;

    public OrganizationTagAdapter(Context context, List<String> tags) {
        this.tags = tags;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_organization_tag, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String tag = tags.get(i);

        viewHolder.organizationTag.setText(tag);
    }

    @Override
    public int getItemCount() {
        return (null != tags ? tags.size() : 0);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView organizationTag;

        public ViewHolder(View view) {
            super(view);
            this.organizationTag = (TextView) view.findViewById(R.id.organizationTag);
        }
    }
}