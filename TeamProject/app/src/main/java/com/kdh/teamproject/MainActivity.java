package com.kdh.teamproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import adaoter.ImageAdapter;

public class MainActivity extends AppCompatActivity {

    Button opendrawer;
    SharedPreferences pref;
    TextView txt_time;
    TextView txt_breaktime;
    TextView txt_round;
    String txt_num;
    Button btn_start, btn_reset, open, btn_stop;
    DrawerLayout drawer_layout;
    LinearLayout drawer;
    ListView listView;
    //ImageAdapter adapter;

    int count = 20;//시간설정에서 시간 가져와 설정하기
    int min = 0;
    int hour = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //xml파일의 id검색
        txt_time = findViewById(R.id.txt_time);
        txt_breaktime = findViewById(R.id.txt_breaktime);
        txt_round = findViewById(R.id.txt_round);
        txt_num = txt_round.getText().toString();

        drawer_layout = findViewById(R.id.drawer_layout);
        drawer = findViewById(R.id.drawer);
        listView = findViewById(R.id.listView);

        btn_start = findViewById(R.id.btn_start);
        btn_reset = findViewById(R.id.btn_reset);
        btn_stop = findViewById(R.id.btn_stop);
        open = findViewById(R.id.opendrawer);

        //버튼 감지자 설정
        /*btn_reset.setOnClickListener(click);
        btn_start.setOnClickListener(click);
        btn_stop.setOnClickListener(click);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LIstViewActivity.class);
                startActivity(i);
            }
        }); */


        opendrawer = findViewById(R.id.opendrawer);

        opendrawer.setOnClickListener(click);

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


        //(임시)새타이머 추가 기능 페이지로 이동
            pref = getSharedPreferences("SHARE",MODE_PRIVATE);
            SharedPreferences.Editor save = pref.edit();

            save.putString("time1","00:01");
            save.putString("time2","00:01");
            save.putString("time3","00:01");
            save.putString("time4","1");

            save.commit();

            Intent i = new Intent(MainActivity.this,TimerAddActivity.class);
            startActivity(i);
        }
    };

    //버튼 클릭 감지자
    View.OnClickListener click2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_start:
                    handler.sendEmptyMessage(0);
                    btn_start.setVisibility(View.GONE);
                    btn_stop.setVisibility(View.VISIBLE);
                    break;

                case R.id.btn_reset:
                    handler.removeMessages(0);
                    count = 0;
                    int sec = 0;
                    int min = 0;
                    int hour = 0;
                    String result = String.format("운동 \n%02d : %02d : %02d", hour, min, sec);
                    String reset = String.format("휴식 \n%02d : %02d : %02d", hour, min, sec);
                    txt_time.setText(result);
                    handler.removeMessages(0);
                    handler_breaktime.removeMessages(1);
                    txt_breaktime.setText(reset);
                    btn_stop.setVisibility(View.GONE);
                    btn_start.setVisibility(View.VISIBLE);
                    break;

                case R.id.btn_stop:
                    handler.removeMessages(0);
                    handler_breaktime.removeMessages(1);
                    btn_stop.setVisibility(View.GONE);
                    btn_start.setVisibility(View.VISIBLE);
                    break;
            }//switch
        }
    };//OnClickListener

    Handler handler = new Handler(){

        @Override
        public void handleMessage( Message msg) {
            //1초에 한번씩 자신을 반복 수행
            handler.sendEmptyMessageDelayed(0, 1000);
            if ( min < 0  ){

            }else{
                hour = 0;
                min = 0;//시간설정 test때문에 10으로 통일해놓음 실제로는 -1 해야됨

            }
            count--;

            int sec = count;

            if (count < 1){

                count = 60;
                min-=1;
            };
            if (min < 0){
                count = 59;
                min = 59;
                hour-=1;
            };

            String result = String.format("운동 \n%02d : %02d : %02d", hour, min, sec);
            txt_time.setText(result);
            if (hour == -1){
                handler.removeMessages(0);
                handler_breaktime.sendEmptyMessage(1);
                int count = 0;//설정으로 시간설정
                int min = 0;
                int hour = 0;
                String reset = String.format("운동 \n%02d : %02d : %02d", hour, min, sec);
                txt_time.setText(reset);

            }
        }
    };
    Handler handler_breaktime = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {

            handler_breaktime.sendEmptyMessageDelayed(1,1000);
            if ( min < 0  ){

            }else{
                min = 0;//시간설정 test때문에 10으로 통일해놓음 실제로는 -1 해야됨

            }

            int sec = count;
            count--;
            if (count < 1){

                count = 60;
                min-=1;
            };
            String result = String.format("휴식 \n %02d : %02d", min, sec);
            txt_breaktime.setText(result);
            if ( min < 0 ) {
                txt_round.setText("2");//다음 라운드 설정해야하는곳 변수에다 +1로 맞춰주시면 될듯?
                int count = 0;
                sec = 0;
                int min = 0;
                int hour = 0;
                handler.sendEmptyMessage(0);
                handler_breaktime.removeMessages(1);



                String ressult = String.format("운동 \n%02d : %02d : %02d", hour, min, sec);
                txt_time.setText(ressult);
                String reset = String.format("휴식 \n %02d : %02d", min, sec);
                txt_breaktime.setText(reset);


                if (txt_round.getText().toString() == "3"){//라운드가 끝났을 때
                    handler_breaktime.removeMessages(1);
                    handler.removeMessages(0);
                    Toast.makeText(getApplicationContext(), "수고하였습니다.", Toast.LENGTH_SHORT).show();

                    sec = 0;
                    min = 0;
                    String result1 = String.format("휴식 \n %02d : %02d", min, sec);
                    txt_breaktime.setText(result1);
                };
            }
        }
    };
}
