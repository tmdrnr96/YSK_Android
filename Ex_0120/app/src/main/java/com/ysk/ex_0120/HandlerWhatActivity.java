package com.ysk.ex_0120;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HandlerWhatActivity extends AppCompatActivity {

    Button btn0, btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_what);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);

        btn0.setOnClickListener(click);
        btn1.setOnClickListener(click);
    }//onCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //상황에 따라 what은 여러개 핸들러를 묶어서 처리할 때, 식별할 수 있는 식별자
            //what으로 구별한다면 , 핸들러를 여러개 만들 필요가 없다.
            switch(view.getId()){
                case R.id.btn0:
                    handler.sendEmptyMessage(0);
                    break;

                case R.id.btn1:
                    handler.sendEmptyMessage(1);
                    break;
            }//switch
        }
    };

    //핸들러 생성
    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            //전달받은 what을 구별
            switch (msg.what){
                case 0:
                    Toast.makeText(HandlerWhatActivity.this, "what 0", Toast.LENGTH_SHORT).show();
                    break;

                case 1:
                    Toast.makeText(HandlerWhatActivity.this,"what 1",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}