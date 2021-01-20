package com.ysk.ex_0120;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {
    //핸들러(안드로이드 전용 스레드) : 자바의 스레드 개념으로써 액티비티의 순환주기와는 별개로 독립적인 작업을 수행가능하게 하는 영역
    //(안드로이드에도 스레드가 존재함 -> 안드로이드 쪽에서는 과부하가 심함..)
    //상황에 따라 서버통신의 같은 경우에는 스레드를 쓸 때가 있음..

    TextView txt;
    Button btn_start, btn_stop;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        txt = findViewById(R.id.txt);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendEmptyMessage : 핸들러 handleMessage를 호출
                //what? -> HandlerWhatActivity 액티비티 참조
                handler.sendEmptyMessage(0);

                //연속으로 핸들러를 호출하지 못하도록 start 버튼을 비활성화
                //start버튼을 한번 누르고 비활성화..
                btn_start.setEnabled(false);

            }
        });

        //핸들러 정지
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeMessages(0);
                //start 버튼 다시 활성화
                btn_start.setEnabled(true);
            }
        });

    }//onCreate()


    //핸들러 생성
    Handler handler = new Handler(){
        //핸들러를 이용하기 위해서는 handleMessage가 필요하다.(쓰레드의 run과 같음)
        //@NonNull 코틀린으로 변환시킬때 오류 방지하기 위함..
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);

            //1 초간격으로 딜레이를 준다.
            //sendEmptyMessageDelayed: 아랫쪽에 있는 코드를 먼저 실행하고 1초 뒤에 자기 자신을 재호출한다.
            //1초에 한번식 핸들러 자신을 반복 수행
            handler.sendEmptyMessageDelayed(0,1000);

            count++;
            txt.setText(""+count);
        }
    };

}