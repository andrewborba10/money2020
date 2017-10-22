package com.election.hacking;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.election.hacking.model.Organization;

import java.util.List;

public class OrganizationAdapter extends BaseAdapter {

    private Context context;
    private List<Organization> organizations;

    public OrganizationAdapter(final Context context, final List<Organization> organizations) {
        this.context = context;
        this.organizations = organizations;
    }

    @Override
    public int getCount() {
        return organizations.size();
    }

    @Override
    public Object getItem(int position) {
        return organizations.get(position);
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
            convertView = inflater.inflate(R.layout.item_organization, null);

            holder = new ViewHolder();
            holder.organizationImage = (ImageView) convertView.findViewById(R.id.organizationImage);
            holder.organizationName = (TextView) convertView.findViewById(R.id.organizationName);
            holder.organizationDescription = (TextView) convertView.findViewById(R.id.organizationDescription);
            holder.tagsRecycler = (RecyclerView) convertView.findViewById(R.id.tagsRecycler);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // TODO organization image
        holder.organizationName.setText(organizations.get(position).getOrganizationTitle());
        holder.organizationDescription.setText(organizations.get(position).getOrganizationDescription());

        OrganizationTagAdapter tagAdapter = new OrganizationTagAdapter(context, organizations.get(position).getOrganizationTags());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.tagsRecycler.setLayoutManager(layoutManager);
        holder.tagsRecycler.setAdapter(tagAdapter);

        return convertView;
    }

    private class ViewHolder {
        ImageView organizationImage;
        TextView organizationName;
        TextView organizationDescription;
        RecyclerView tagsRecycler;
    }
}
