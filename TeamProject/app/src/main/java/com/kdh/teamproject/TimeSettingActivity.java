package com.kdh.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TimeSettingActivity extends AppCompatActivity {

    EditText sec, min;
    Button btn_cancel, btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_setting);
        sec = findViewById(R.id.sec);
        min = findViewById(R.id.min);

        btn_save = findViewById(R.id.btn_save);
        btn_cancel = findViewById(R.id.btn_cancel);

        btn_save.setOnClickListener(click);
        btn_cancel.setOnClickListener(click);
    }//oncreate

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_save:

                    break;

                case R.id.btn_cancel:
                    finish();
                    break;
            }//switch
        }
    };
}