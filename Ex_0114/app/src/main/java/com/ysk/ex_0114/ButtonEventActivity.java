package com.ysk.ex_0114;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ButtonEventActivity extends AppCompatActivity {

    Button red, green, blue, send;
    EditText et;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_event);

        //이벤트 처리에 사용할 객체들 검색(R은 안드로이드에서 만들어져 있는 클래스...)
        red = findViewById(R.id.btn_red); //xml(layout) 파일에 있는 버튼의 주소를 넘긴다(정수 형태로 저장 1000063)
        green = findViewById(R.id.btn_green); //조회시 타입이 다른면 오류가 생긴다.
        blue = findViewById(R.id.btn_blue);
        send = findViewById(R.id.btn_send);

        et = findViewById(R.id.et);

        txt = findViewById(R.id.txt);

        //red버튼에 이벤트 감지자 등록
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //red버튼을 클릭했을 때 이벤트 처리를 위한 영역
                //Color.parseColor("#f00") <--이런식으로  줄여서 사용할 수 없음(반드시 16진수로 사용해야한다.)
                txt.setBackgroundColor(Color.parseColor("#ff0000"));
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setBackgroundColor(Color.parseColor("#0100FF"));
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setBackgroundColor(Color.parseColor("#1DDB16"));
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send버튼을 클릭하면 et의 값을 가져와서 txt에 세팅

                //et에 쓰여진 값을 가져오기
                String res = et.getText().toString();
                txt.setText(res)
                ;
            }
        });

    }//onCreate()
}