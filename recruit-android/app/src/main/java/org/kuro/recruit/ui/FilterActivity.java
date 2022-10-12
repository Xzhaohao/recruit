package org.kuro.recruit.ui;

import android.os.Bundle;
import android.widget.ImageView;

import org.kuro.recruit.R;
import org.kuro.recruit.base.BaseUIActivity;
import org.kuro.recruit.utils.SystemUI;

public class FilterActivity extends BaseUIActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        SystemUI.fixSystemUI(this, true);

        ImageView close = findViewById(R.id.close);
        close.setOnClickListener(v -> finish());
    }
}