package com.ysk.ex_0115;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Work1_1Activity extends AppCompatActivity {

    Button start;
    EditText et;
    TextView txt_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work1_1);

        start = findViewById(R.id.start);
        et = findViewById(R.id.et);
        txt_result = findViewById(R.id.txt_result);

        //시작 버튼에 이벤트 감지 등록자
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //EditText에 쓰여져 있는 원본 텍스트를 가져온다.
                String str = et.getText().toString().trim();//원본데이터
                String rev = "";

                //원본 문자열을 뒤집어서 rev변수에 저장하는 작업
                for(int i = str.length()-1; i >= 0; i--){
                    rev += str.charAt(i);
                }//for
                
                if (str.equals(rev)) {
                    txt_result.setText(str + "는 회문수");
                }else{
                    txt_result.setText(str + "는 회문수가 이님");
                }

            }
        });
    }//onCreate

}