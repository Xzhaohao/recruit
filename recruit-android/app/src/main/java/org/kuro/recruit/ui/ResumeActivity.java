package org.kuro.recruit.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;

import org.kuro.recruit.R;
import org.kuro.recruit.adapter.EduExperienceAdapter;
import org.kuro.recruit.adapter.JobExpectAdapter;
import org.kuro.recruit.adapter.WorkHistoryAdapter;
import org.kuro.recruit.api.Callback;
import org.kuro.recruit.api.Http;
import org.kuro.recruit.base.BaseUIActivity;
import org.kuro.recruit.config.ApiConfig;
import org.kuro.recruit.databinding.ActivityResumeBinding;
import org.kuro.recruit.model.res.Account;
import org.kuro.recruit.model.res.ResumeRes;
import org.kuro.recruit.utils.LogUtil;

import java.util.HashMap;

public class ResumeActivity extends BaseUIActivity {

    private ActivityResumeBinding resumeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resumeBinding = DataBindingUtil.setContentView(this, R.layout.activity_resume);

        initView();
        fetchResumeInfo();
    }


    private void initView() {
        resumeBinding.resumeBack.setOnClickListener(v -> finish());
        resumeBinding.eduRow.setOnClickListener(v -> startActivity(new Intent(this, EduActivity.class)));

        LinearLayoutManager layoutVertical = new LinearLayoutManager(this);
        layoutVertical.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager layoutHorizontal = new LinearLayoutManager(this);
        layoutHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager layoutHorizontal2 = new LinearLayoutManager(this);
        layoutHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
        resumeBinding.workHistoryRecycler.setLayoutManager(layoutVertical);
        resumeBinding.jobExpectRecycler.setLayoutManager(layoutHorizontal);
        resumeBinding.resumeEduRecycler.setLayoutManager(layoutHorizontal2);
    }


    private void fetchResumeInfo() {
        Context context = this;
        HashMap<String, Object> params = new HashMap<>();
        Http.config(ApiConfig.ACCOUNT, params).post(this, new Callback() {
            @Override
            public void onSuccess(String res) {
                runOnUiThread(() -> {
                    ResumeRes result = new Gson().fromJson(res, ResumeRes.class);
                    if (result.getStatus()) {
                        Account account = result.getData();
                        resumeBinding.setAccount(account);
                        resumeBinding.workHistoryRecycler.setAdapter(new WorkHistoryAdapter(context, account.getWorkList()));
                        resumeBinding.resumeEduRecycler.setAdapter(new EduExperienceAdapter(context, account.getEduList()));
                        resumeBinding.jobExpectRecycler.setAdapter(new JobExpectAdapter(context, account.getExpects()));
                    } else {
                        showToastSync(result.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                LogUtil.e(e.getMessage());
            }
        });
    }
}