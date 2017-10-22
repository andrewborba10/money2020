package com.election.hacking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.election.hacking.model.Organization;
import com.squareup.picasso.Picasso;

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
        final Organization organization = organizations.get(position);
        return bindView(context, convertView, organization);
    }

    @NonNull
    public static View bindView(final Context context,
                                View convertView,
                                final Organization organization) {
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

        if (holder.organizationImage != null) {
            setImageContainerColor(context, R.color.white, convertView);
            Picasso
                    .with(context)
                    .load(organization.getOrganizationImageUrl())
                    .fit()
                    .into(holder.organizationImage);
        } else {
            setImageContainerColor(context, R.color.gray, convertView);
        }
        holder.organizationName.setText(organization.getOrganizationTitle());
        holder.organizationDescription.setText(organization.getOrganizationDescription());

        OrganizationTagAdapter tagAdapter = new OrganizationTagAdapter(context, organization.getOrganizationTags());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.tagsRecycler.setLayoutManager(layoutManager);
        holder.tagsRecycler.setAdapter(tagAdapter);

        return convertView;
    }

    private static void setImageContainerColor(final Context context, final int color, final View view) {
        final int colorRes = ContextCompat.getColor(context, color);
        view.findViewById(R.id.organizationImageContainer).setBackgroundColor(colorRes);
    }

    private static class ViewHolder {
        ImageView organizationImage;
        TextView organizationName;
        TextView organizationDescription;
        RecyclerView tagsRecycler;
    }
}
