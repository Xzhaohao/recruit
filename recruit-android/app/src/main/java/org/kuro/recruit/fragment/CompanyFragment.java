package org.kuro.recruit.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.kuro.recruit.R;
import org.kuro.recruit.adapter.CompanyAdapter;
import org.kuro.recruit.api.Callback;
import org.kuro.recruit.api.Http;
import org.kuro.recruit.base.BaseFragment;
import org.kuro.recruit.config.ApiConfig;
import org.kuro.recruit.databinding.FragmentCompanyBinding;
import org.kuro.recruit.model.res.CompanyRes;
import org.kuro.recruit.model.res.PageCompany;
import org.kuro.recruit.ui.SearchActivity;

import java.util.HashMap;

public class CompanyFragment extends BaseFragment {

    private FragmentCompanyBinding companyBinding;
    private RecyclerView mRecycler;

    public CompanyFragment() {
    }

    public static CompanyFragment newInstance() {
        return new CompanyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        companyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_company, container, false);

        mRecycler = companyBinding.companyRecycler;
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(layoutManager);

        return companyBinding.getRoot();
    }

    @Override
    protected void initView() {
        companyBinding.searchInput.setOnClickListener(v -> navigateTo(SearchActivity.class));
    }

    @Override
    protected void initData() {
        HashMap<String, Object> params = new HashMap<>();
        fetchCompanyList(params);
    }


    // 获取企业列表
    @SuppressLint("NotifyDataSetChanged")
    private void fetchCompanyList(HashMap<String, Object> params) {
        Http.config(ApiConfig.COMPANY, params).get(requireActivity(), new Callback() {
            @Override
            public void onSuccess(String res) {
                requireActivity().runOnUiThread(() -> {
                    CompanyRes result = new Gson().fromJson(res, CompanyRes.class);
                    if (result.getStatus()) {
                        PageCompany data = result.getData();

                        if (data.getTotal() > 0) {
                            companyBinding.empty.setVisibility(View.GONE);
                            companyBinding.content.setVisibility(View.VISIBLE);
                        }

                        CompanyAdapter adapter = new CompanyAdapter(requireActivity(), data.getRows());
                        mRecycler.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } else {
                        showToastSync(result.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }
}