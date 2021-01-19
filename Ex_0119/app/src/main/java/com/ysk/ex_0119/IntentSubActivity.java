package com.ysk.ex_0119;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class IntentSubActivity extends AppCompatActivity {

    Button btn_priv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub);

        btn_priv = findViewById(R.id.prev);
        btn_priv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(IntentSubActivity.this,IntentMainActivity.class);
                //startActivity(i);
                finish();
            }
        });

    }//onCreate()

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Toast.makeText(IntentSubActivity.this,"종료",Toast.LENGTH_SHORT).show();
        finish();//현재 액티비티를 종료하는 메서드
    }
}