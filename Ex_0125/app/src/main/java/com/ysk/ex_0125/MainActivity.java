package com.ysk.ex_0125;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipe = findViewById(R.id.swipe);

        //디스크 색상 변경
        swipe.setProgressBackgroundColorSchemeColor(Color.parseColor("#aaaaff"));

        //디스크 크기 변경(기본값이 DEFAULT, 큰사이즈가 LARGE)
        swipe.setSize(SwipeRefreshLayout.LARGE);

        //디스크의 끝 위치 변경
        swipe.setProgressViewEndTarget(true,300);

        //리소시스라고 써져있는건 참조파일을 통해 사용할 수 있음..
        //프로그래스바 안에 있는 프로그래스 색상 바꾸기 //색상은 무한정으로 들어감..
        swipe.setColorSchemeResources(R.color.c1,R.color.c2,R.color.c3,R.color.c4);

        //swiperefreshLayout이 당겨졌는지 감지하는 감지자
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //당겼다가 손을 떼는 순간 호출되는 메서드
                handler.sendEmptyMessageDelayed(0,2000);
                //new TestAsync().execute();
            }
        });

    }//onCreate()

     Handler handler = new Handler(){

        @Override
         public void handleMessage(@NonNull Message msg) {
             //약 2초간 로딩을 한다고 가정하여 만든 핸들러

            //서버통신이 마무리 되면 디스크를 제거
            swipe.setRefreshing(false);

         }
     };
}