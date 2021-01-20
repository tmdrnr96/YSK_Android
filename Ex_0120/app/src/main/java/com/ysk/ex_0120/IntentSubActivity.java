package com.ysk.ex_0120;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class IntentSubActivity extends AppCompatActivity {

    TextView txt_name, txt_age, txt_tel, txt_birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub);

        txt_name = findViewById(R.id.txt_name);
        txt_age = findViewById(R.id.txt_age);
        txt_tel = findViewById(R.id.txt_tel);
        txt_birth = findViewById(R.id.txt_birth);

        //IntentMainActivity에서 넘겨준 값을 현재 액티비티에서 받는다.
        Intent intent = getIntent();

        //메인에서 보낸 값 추출하기 방법 1
        //String name = intent.getStringExtra("m_name");
        //String age = intent.getStringExtra("m_age");
        //String tel = intent.getStringExtra("m_tel");

        //메인에서 보낸 값 추출하기 방법 2
        Bundle bundle = intent.getExtras(); //intent에서 가져온 번들을 얻어 낸다.
        String name = bundle.getString("n");
        String age = bundle.getString("a");
        String tel = bundle.getString("t");
        String birth = bundle.getString("b");

        txt_name.setText(name);
        txt_age.setText(age);
        txt_tel.setText(tel);
        txt_birth.setText(birth);

    }//onCreate()
}