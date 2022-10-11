package org.kuro.recruit.ui;

import android.os.Bundle;
import android.widget.ImageView;

import org.kuro.recruit.R;
import org.kuro.recruit.base.BaseUIActivity;
import org.kuro.recruit.utils.SystemUI;

import me.gujun.android.taggroup.TagGroup;

public class SearchActivity extends BaseUIActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SystemUI.fixSystemUI(this, true);

        initView();
    }


    private void initView() {
        TagGroup hotTag = findViewById(R.id.hot_tag);
        TagGroup historyTag = findViewById(R.id.history_tag);
        TagGroup likeTag = findViewById(R.id.like_tag);
        ImageView back = findViewById(R.id.back);

        hotTag.setTags("嵌入式", "插画设计", "UI设计");
        historyTag.setTags("嵌入式Linux", "Web前端", "Golang", "android开发", "C++服务器开发", "Java后端");
        likeTag.setTags("嵌入式", "Web前端", "android开发");

        back.setOnClickListener(v -> finish());
    }
}