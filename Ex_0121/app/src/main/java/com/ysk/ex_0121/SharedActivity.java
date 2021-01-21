package com.ysk.ex_0121;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedActivity extends AppCompatActivity {

    //SharedPreferences: DB를 사용하기에는 너무 단순한 데이터를 저장하기위해
    //내부적으로 파일을 생성하여 간단한 내용들을 기록할 수 있도록 해 주는 인터페이스스
    Button btn_up, btn_down, btn_reset, btn_send;
    TextView value;
    EditText et;

    int n = 0; //텍스트뷰를 갱신할 변수

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        //저장을 위한 SharedPreferences객체를 생성
        //SHARE라는 이름으로 된 가상의 저장소를 만들고, 이 저장소가 존재하는 경우에만
        //저장 및 로드가 가능하도록 MODE_PRIVATE을 사용해야 한다.
        pref = getSharedPreferences("SHARE", MODE_PRIVATE);

        btn_up = findViewById(R.id.btn_up);
        btn_down = findViewById(R.id.btn_down);
        btn_reset = findViewById(R.id.btn_reset);
        btn_send = findViewById(R.id.btn_send);
        value = findViewById(R.id.value);
        et = findViewById(R.id.et);

        //로드
        n = pref.getInt("save", 0); //save라는 키값에 값이 없다면 0으로 초기화!

        value.setText(String.valueOf(n));

        btn_up.setOnClickListener(click);
        btn_down.setOnClickListener(click);
        btn_reset.setOnClickListener(click);

        //다른 액티비티로 값 전달하기->(번들에 담아서 전달 가능할까?)
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = et.getText().toString();
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("et",str);
                edit.commit();//str 저장!

                //Intent를 통해 화면을 전환하려면
                //전환하고자 하는 Activity들이 반드시 Manifast.xml에 등록되어 있어야한다.
                Intent i = new Intent(SharedActivity.this,MainActivity.class);
                startActivity(i);//화면전환
            }
        });

    }//onCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch(view.getId()){
                case R.id.btn_up:
                    value.setText(String.valueOf(n++));
                    break;

                case R.id.btn_down:
                    value.setText(String.valueOf(n--));
                    break;

                case R.id.btn_reset:
                    n = 0;
                    value.setText(String.valueOf(n));
                    break;
            }

        }
    };

    @Override
    protected void onPause() {
        super.onPause();//생명주기를 관리하는 코드는 super를 지우면 오류//

        //웹이 일시정지 되었을 때 현재 n값을 저장
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt("save",n);//save라는 이름으로 n값을 저장

        edit.putInt("save", 20); //save라는 같은 키값, 타입도 같으면 상관이 없음..(마지막에 put한 것으로 저장)
                                        //하지만 키값은 같고, 타입이 다르다면 오류...
        edit.commit();//commit을 하지 않으면 값이 물리적으로 저장되지 않는다.
    }
}