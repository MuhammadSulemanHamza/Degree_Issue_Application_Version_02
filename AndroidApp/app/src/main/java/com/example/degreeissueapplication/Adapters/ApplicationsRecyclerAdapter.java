package com.example.degreeissueapplication.Adapters;


import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeissueapplication.ApplicationViewActivity;
import com.example.degreeissueapplication.DegreeIssuanceFormActivity;
import com.example.degreeissueapplication.MainActivity;
import com.example.degreeissueapplication.Model.DegreeIssueModel;
import com.example.degreeissueapplication.Model.UserModel;
import com.example.degreeissueapplication.R;

import java.util.List;

public class ApplicationsRecyclerAdapter extends RecyclerView.Adapter<ApplicationsRecyclerAdapter.ApplicationViewHolder> {

    private List<DegreeIssueModel> listApps;
    private onApplicationListner onApplicationListner;

    public ApplicationsRecyclerAdapter(List<DegreeIssueModel> listApps, onApplicationListner onApplicationListner) {
        this.listApps = listApps;
        this.onApplicationListner = onApplicationListner;
    }

    @Override
    public ApplicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_application_recycler, parent, false);

        return new ApplicationViewHolder(itemView, onApplicationListner);
    }

    @Override
    public void onBindViewHolder(ApplicationViewHolder holder, int position) {
        holder.textViewDegree.setText(listApps.get(position).getDegree());
        holder.textViewDepartment.setText(listApps.get(position).getDepartment());
        holder.textViewStatus.setText(listApps.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        Log.v(ApplicationsRecyclerAdapter.class.getSimpleName(),""+listApps.size());
        return listApps.size();
    }


    /**
     * ViewHolder class
     */
    public class ApplicationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public AppCompatTextView textViewDegree;
        public AppCompatTextView textViewDepartment;
        public AppCompatTextView textViewStatus;
        onApplicationListner applicationListner;

        public ApplicationViewHolder(View view, onApplicationListner applicationListner) {
            super(view);
            textViewDegree = (AppCompatTextView) view.findViewById(R.id.textViewDegree);
            textViewDepartment = (AppCompatTextView) view.findViewById(R.id.textViewDepartment);
            textViewStatus = (AppCompatTextView) view.findViewById(R.id.textViewStatus);
            this.applicationListner = applicationListner;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent i = (Intent) applicationListner.onApplicationClick(getAdapterPosition());

            i.setClass(v.getContext(), ApplicationViewActivity.class);

            v.getContext().startActivity(i);

            //notifyDataSetChanged();
        }
    }

    public interface onApplicationListner{
        Intent onApplicationClick(int position);
    }


}