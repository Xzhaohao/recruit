package org.kuro.recruit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.kuro.recruit.R;
import org.kuro.recruit.model.entity.JobExpect;

import java.util.List;

public class JobExpectAdapter extends RecyclerView.Adapter<JobExpectAdapter.ViewHolder> {

    private final Context mContext;
    private final List<JobExpect> mList;

    public JobExpectAdapter(Context context, List<JobExpect> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.com_edu_job_item, parent, false);
        return new JobExpectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JobExpect expect = mList.get(position);

        String title = expect.getPosition() + " " + expect.getSalary();
        holder.title.setText(title);
        holder.content.setText(expect.getCity());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.com_edu_job_title);
            content = itemView.findViewById(R.id.com_edu_job_content);
            time = itemView.findViewById(R.id.com_edu_job_time);
        }
    }
}
