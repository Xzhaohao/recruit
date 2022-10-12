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
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import org.kuro.recruit.R;
import org.kuro.recruit.adapter.CompanyAdapter;
import org.kuro.recruit.api.Callback;
import org.kuro.recruit.api.Http;
import org.kuro.recruit.base.BaseFragment;
import org.kuro.recruit.config.ApiConfig;
import org.kuro.recruit.databinding.FragmentCompanyBinding;
import org.kuro.recruit.model.entity.Company;
import org.kuro.recruit.model.res.CompanyRes;
import org.kuro.recruit.model.res.PageCompany;
import org.kuro.recruit.ui.SearchActivity;

import java.util.HashMap;
import java.util.List;

public class CompanyFragment extends BaseFragment {

    private FragmentCompanyBinding companyBinding;
    private RecyclerView mRecycler;
    private SmartRefreshLayout mRefresh;
    private int pageNum = 1;
    private List<Company> list;

    public CompanyFragment() {
    }

    public static CompanyFragment newInstance() {
        return new CompanyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        companyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_company, container, false);

        mRecycler = companyBinding.companyRecycler;

        mRefresh = companyBinding.refresh;
        mRefresh.setOnRefreshListener(refreshLayout -> {
            // refreshLayout.finishRefresh(2000);
            pageNum = 1;
            fetchCompanyList(true);
        });
        mRefresh.setOnLoadMoreListener(refreshLayout -> {
            // refreshLayout.finishLoadMore(2000);
            pageNum++;
            fetchCompanyList(false);
        });

        return companyBinding.getRoot();
    }

    @Override
    protected void initView() {
        companyBinding.searchInput.setOnClickListener(v -> navigateTo(SearchActivity.class));
    }

    @Override
    protected void initData() {
        fetchCompanyList(true);
    }


    // 获取企业列表
    @SuppressLint("NotifyDataSetChanged")
    private void fetchCompanyList(boolean isRefresh) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", pageNum);
        params.put("limit", 10);
        Http.config(ApiConfig.COMPANY, params).get(requireActivity(), new Callback() {
            @Override
            public void onSuccess(String res) {
                requireActivity().runOnUiThread(() -> {
                    CompanyRes result = new Gson().fromJson(res, CompanyRes.class);
                    if (result.getStatus()) {
                        if (isRefresh) {
                            mRefresh.finishRefresh(true);
                        } else {
                            mRefresh.finishLoadMore(true);
                        }

                        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        mRecycler.setLayoutManager(layoutManager);

                        PageCompany data = result.getData();

                        if (data.getTotal() > 0) {
                            companyBinding.empty.setVisibility(View.GONE);
                            companyBinding.refresh.setVisibility(View.VISIBLE);
                        }


                        List<Company> rows = data.getRows();
                        if (rows != null && rows.size() > 0) {
                            if (isRefresh) {
                                list = rows;
                            } else {
                                list.addAll(rows);
                            }
                            CompanyAdapter adapter = new CompanyAdapter(requireActivity(), list);
                            mRecycler.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
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