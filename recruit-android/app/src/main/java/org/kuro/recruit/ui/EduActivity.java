package org.kuro.recruit.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;

import com.github.gzuliyujiang.wheelpicker.OptionPicker;

import org.kuro.recruit.R;
import org.kuro.recruit.base.BaseUIActivity;

import java.util.ArrayList;
import java.util.List;

public class EduActivity extends BaseUIActivity {

    @SuppressLint({"SetTextI18n", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu);

        EditText eduInput = findViewById(R.id.edu_input);
        EditText timeInput = findViewById(R.id.time_input);

        List<String> list = new ArrayList<>();
        list.add("初中及以下");
        list.add("中专/中技");
        list.add("高中");
        list.add("全日制-大专");
        list.add("非全日制-大专");
        list.add("全日制-本科");
        list.add("非全日制-本科");
        list.add("硕士");
        list.add("博士");

        OptionPicker picker = new OptionPicker(this);
        picker.setTitle("学历");
        picker.setData(list);
        picker.setDefaultPosition(5);
        picker.setOnOptionPickedListener((position, item) -> {
            eduInput.setText((String) item);
        });

        eduInput.setOnClickListener(v -> picker.show());


        OptionPicker datePicker = new OptionPicker(this);
        List<Integer> timeList = new ArrayList<>();
        for (int i = 2000; i < 2030; i++) {
            timeList.add(i);
        }
        datePicker.setTitle("入学时间");
        datePicker.setData(timeList);
        datePicker.setDefaultPosition(15);

        timeInput.setOnClickListener(v -> datePicker.show());
        datePicker.setOnOptionPickedListener((position, item) -> {
            String edu = eduInput.getText().toString();
            if ("全日制-本科".equals(edu)) {
                timeInput.setText(item + "-" + ((int) item + 4));
            } else {
                timeInput.setText(item + "-" + ((int) item + 3));
            }
        });
    }
}