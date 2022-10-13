package org.kuro.recruit.ui;

import android.os.Bundle;
import android.widget.ImageView;

import org.kuro.recruit.R;
import org.kuro.recruit.base.BaseUIActivity;

public class CompanyDetailActivity extends BaseUIActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());
    }
}