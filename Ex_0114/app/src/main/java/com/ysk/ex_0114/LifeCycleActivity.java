package com.ysk.ex_0114;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class LifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        //onCreate()메서드는 액티비티 실행시 가장 먼저 호출되는 메서드
        Log.i("MY","--onCreate()--");

        //Context: 화면제어권자(현재 액티비티에 화면에 대한 모든 저보를 담고 있는 클래스)
        Toast.makeText(LifeCycleActivity.this,"액티비티가 실행됨",Toast.LENGTH_SHORT).show();

    }//onCreate()

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MY","--onDestroy()--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MY","--onPause()--");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MY","--onRestart()--");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MY","--onResume()--");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MY","--onStart()--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MY","--onStop()--");
    }

    /*
    액티비티의 생명주기
    1. 앱을 실행했을 때(화면이 보이는 경우)
     onCreate() --> 최초로 앱을 실행시켰을 때 딱 한번만 호출출
     onStart()
     onResume()

    2. 홈 버튼을 통해 일시적으로 앱이 중단되었을 때(완전히 꺼진 상태가 아님)
     onPause()
     onStop()

    3. 일시적으로 중단되었던 앱이 재실행되었을 때
     onRestart()
     onStart()
     onResume()

     4. 뒤로가기 버튼을 클릭하여 앱이 완전히 종료 되었을 때(시야에서 사라졌을 때)
     onPause()
     onStop()
     onDestroy() --> 앱이 완전히 종료되면 딱 한번 호출되는 메서드

    */

}