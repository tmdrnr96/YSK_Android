package com.ysk.ex_0120;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class IntentMainActivity extends AppCompatActivity {

    Button btn_date, btn_send;
    EditText edit_name, edit_age, edit_tel, edit_b_day;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);

        btn_date = findViewById(R.id.btn_date);
        btn_send = findViewById(R.id.btn_send);

        edit_name = findViewById(R.id.edit_name);
        edit_age = findViewById(R.id.edit_age);
        edit_tel = findViewById(R.id.edit_tel);
        edit_b_day =findViewById(R.id.edit_b_day);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //IntentSubActvity로 화면 전환(값을 가지고 전환)
                Intent i = new Intent(IntentMainActivity.this,IntentSubActivity.class);

                //editText에서 나오는 값은 모두 String 타입이다.
                String name = edit_name.getText().toString();
                String age = edit_age.getText().toString();
                String tel = edit_tel.getText().toString();
                String birth = edit_b_day.getText().toString();

                //값 전달을 위한 방법 1 : Intent에 직접 저장하기
                //i.putExtra("m_name",name);
                //i.putExtra("m_age",age);
                //i.putExtra("m_tel",tel);

                //값 전달을 위한 방법2 : Bundle에 저장하기(보완성이 더 좋음..)
                //Bundle은 여러가지 타입의 값을 저장하기 위한 Map구조의 클래스
                Bundle bundle = new Bundle();
                bundle.putString("n", name);
                bundle.putString("a", age);
                bundle.putString("t", tel);
                bundle.putString("b", birth);

                //Intent에 Bundle을 저장
                i.putExtras(bundle);

                startActivity(i);
            }
        });
        //btn_date버튼 클릭시 다이얼로그 생성
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //휴대폰에 설정되어 있는 오늘 날짜를 가지고 온다.
                Calendar now = Calendar.getInstance();
                int y = now.get(Calendar.YEAR); //년
                int m = now.get(Calendar.MONTH);//월
                int d = now.get(Calendar.DAY_OF_MONTH);//일

                //달력 다이얼로그에 오늘 날짜와 감지자를 등록
                dialog = new DatePickerDialog(IntentMainActivity.this, dateSetListener, y,m,d);

                dialog.show();//다이얼로그 노출

            }
        });

    }//onCreate()

    //달력의 날짜변경을 감지하는 감지자
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {

            //파라미터로 넘어오는 m(월)은 1월 - > 0, 2월 -> 1, 12월 -> 11....
            //하나씩 값이 부족하게 넘어온다.

            String strDate = String.format("%d-%02d-%02d",y,m+1,d);

            edit_b_day.setText(strDate);
        }
    };
}