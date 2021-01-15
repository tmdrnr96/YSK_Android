package com.ysk.ex_0115;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Listener1Activity extends AppCompatActivity {

    Button btn1,btn2,btn3;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener1);

        /*Alt +  Shift + 클릭*/
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        txt = findViewById(R.id.txt);

        //버튼에 대한 이벤트 감지자를 묶어서 한번에 처리
        btn1.setOnClickListener(click);
        btn2.setOnClickListener(click);
        btn3.setOnClickListener(click);


    }//onCreate
    //추상메서드를 강제로 오버라이딩!
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        /*Viwe : 눈으로 확인 가능한 모든객체의 부모*(Button,TextView,EditText... 등등)*/
        public void onClick(View view) {

            switch(view.getId()){//클릭 이벤트를 발생시킨 버튼의 아이디를 가져온다.
                case R.id.btn1: //아이디가 btn1인 버튼이 클릭되었을 때...
                    txt.setText("1번 버튼 누름");
                    break;

                case R.id.btn2:
                    txt.setText("2번 버튼 누름");
                    break;

                case R.id.btn3:
                    txt.setText("3번 버튼 누름");
                     break;

            }

            //Toast.makeText(Listener1Activity.this, "버튼 클릭됨", Toast.LENGTH_SHORT).show();
        }
    };
}