package com.ysk.ex_0118;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class WorkActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, restart;
    TextView result;
    int n;
    int rnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        restart = findViewById(R.id.restart);

        result = findViewById(R.id.result);

        btn1.setOnClickListener(click);
        btn2.setOnClickListener(click);
        btn3.setOnClickListener(click);
        btn4.setOnClickListener(click);

        restart.setOnClickListener(reclick);

        //랜덤 수를 뽑는다.
        rnd = new Random().nextInt(4)+1;

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.btn1:
                    n = 1;
                    break;
                case R.id.btn2:
                    n = 2;
                    break;
                case R.id.btn3:
                    n = 3;
                    break;
                case R.id.btn4:
                    n = 4;
                    break;
            }
            if(n == rnd){
                result.setText("당첨!");
            }else{
                result.setText("꽝");
            }


        }
    };

    View.OnClickListener reclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            result.setText("Result");
            rnd = new Random().nextInt(4)+1;
        }
    };


}