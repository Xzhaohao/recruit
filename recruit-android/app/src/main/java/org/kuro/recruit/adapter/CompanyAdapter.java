package org.kuro.recruit.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.kuro.recruit.R;
import org.kuro.recruit.databinding.CompanyItemBinding;
import org.kuro.recruit.model.entity.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    private List<Company> mList = new ArrayList<>();
    private final Context mContext;

    public CompanyAdapter(Context context) {
        this.mContext = context;
    }

    public CompanyAdapter(Context context, List<Company> list) {
        this.mList = list;
        this.mContext = context;
    }

    public void setList(List<Company> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public CompanyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CompanyItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.company_item, parent, false);
        return new ViewHolder(itemBinding);
    }

    @SuppressLint("InflateParams")
    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.ViewHolder holder, int position) {
        Company company = mList.get(position);
        holder.itemBinding.setCompany(company);
        String desc = company.getCity() + " | " + company.getServiceType() + " | " + company.getPopulation();
        holder.itemBinding.companyIntro.setText(desc);

        for (int i = 0; i < company.getTagArr().length; i++) {
            TextView tag = (TextView) LayoutInflater.from(mContext).inflate(R.layout.company_item_tag, null);
            tag.setText(company.getTagArr()[i]);
            if (i < company.getTagArr().length - 1) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMarginEnd(10);
                tag.setLayoutParams(params);
            }
            holder.itemBinding.tags.addView(tag);
        }

        String job = getRandomJob();
        int i = (int) (Math.random() * 30 + 5);
        SpannableString jobHots = new SpannableString("热招：" + job + " 等" + i + "个岗位");
        jobHots.setSpan(new ForegroundColorSpan(Color.parseColor("#3B73F6")),
                3, job.length() + 3,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.itemBinding.companyJobs.setText(jobHots);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final CompanyItemBinding itemBinding;

        public ViewHolder(CompanyItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }


    public static String getRandomJob() {
        String[] jobRandArr = {
                "Web前端开发", "保安", "清洁工", "行政",
        };
        int size = jobRandArr.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return jobRandArr[index];
    }
}
