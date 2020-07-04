package com.refknowledgebase.refknowledgebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.model.Working_Hour_Model;
import com.refknowledgebase.refknowledgebase.myinterface.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class WorkingHourAdapter extends RecyclerView.Adapter<WorkingHourAdapter.ViewHolder>{

    Context mContext;
    List<Working_Hour_Model> workingHourModelList;

    public WorkingHourAdapter(Context _context){
        mContext = _context;
        workingHourModelList = new ArrayList<>();

    }
    @NonNull
    @Override
    public WorkingHourAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_working_hour, parent, false);
        return new WorkingHourAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkingHourAdapter.ViewHolder holder, int position) {
        holder.tv_workinghour_title.setText(workingHourModelList.get(position).getDay());
        holder.tv_workinghour_con.setText(workingHourModelList.get(position).getFrom() + " ~ " + workingHourModelList.get(position).getTo());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_workinghour_title, tv_workinghour_con;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_workinghour_title = itemView.findViewById(R.id.tv_hour_title);
            this.tv_workinghour_con = itemView.findViewById(R.id.tv_hour_con);
        }
    }

    public void addAll_workingHour(List<Working_Hour_Model> workingHourModelList) {
        for (Working_Hour_Model result: workingHourModelList){
            add_workingHour(result);
        }
    }

    private void add_workingHour(Working_Hour_Model result) {
        workingHourModelList.add(result);
    }
}
