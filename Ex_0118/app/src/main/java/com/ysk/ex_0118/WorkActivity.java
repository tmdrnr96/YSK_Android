package com.ysk.ex_0118;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

//AndroidMainfest에서 앱 테마를 수동으로 지정하였으므로,
//appcompatActivity가 아닌 일반 Activity를 상속 받아야 한다.(자동 임포트 Alt+Enter)
public class WorkActivity extends Activity {

    Button btn1, btn2, btn3, btn4, restart;
    TextView result;
    int rnd = 0; //난수 발생용 변수

    private void setRandom(){

        //랜덤 수를 뽑는다.
        rnd = new Random().nextInt(4)+1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        result = findViewById(R.id.result);

        //앱 실행과 동시에 1 ~ 4 사이의 난수 발생

        restart = findViewById(R.id.restart);
        setRandom();

        //이벤트 처리에 사용할 버튼들을 한군데에 묶어서 처리
        btn1.setOnClickListener(click);
        btn2.setOnClickListener(click);
        btn3.setOnClickListener(click);
        btn4.setOnClickListener(click);

        //난수 재 발생 버튼
        restart.setOnClickListener(reclick);
    }//onCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //클릭한 버튼에 쓰여져 있는 글자
             int myNum = Integer.parseInt(((Button)view).getText().toString());

             //TextView, Button, EditText 등의 객체에 setText()를 통해 값을 정의 하려면
            //문자열 형식으로만 지정이 가능
            //result.setText(myNum); <-- setText()에 정수값을 직접적으로 세팅하는 것은 불가능(오류발생)
            // result.setText(""+ myNum); <-- 정수값은 문자열로 변형하여 세팅해줘야 한다.
           // result.setText(String.valueOf(myNum)); //<-- 이것도 가능
            //result.setText(""+ myNum);

            if(myNum == rnd){
                result.setText("당첨");
            }else{
                result.setText("꽝");
            }

        }//onClick()
    };

    View.OnClickListener reclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            result.setText("Result");
            setRandom();
        }
    };


}