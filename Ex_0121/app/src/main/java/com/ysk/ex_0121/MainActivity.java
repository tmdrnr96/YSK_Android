package com.ysk.ex_0121;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int num = 2;

    TextView txt;
    SharedPreferences pref; //SharedActivity에서 전달해준 값을 받기 위해 반드시 선언!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("SHARE", MODE_PRIVATE);

        txt = findViewById(R.id.txt);

        //외부에서 전달된 데이터를 Shared를 통해 로드
        String str = pref.getString("et",""); //et라는 값이 없을 수 있으니 디폴트 값으로 ""을 넣어준다.
        txt.setText(str);
    }//onCreate()

    @Override
    public void onBackPressed() {

        if(num != 2){
            finish();
        }else{
            Toast.makeText(MainActivity.this,"뒤로가기를 한번 더 누르면 종료합니다.",Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessage(0);//핸들러 호출
        }

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            handler.sendEmptyMessageDelayed(0,1000);
            if(num >= 0){
                --num;
            }else{
                num = 2;
                handler.removeMessages(0);
            }


        }
    };



}