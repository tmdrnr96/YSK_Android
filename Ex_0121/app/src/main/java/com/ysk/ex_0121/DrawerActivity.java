package com.ysk.ex_0121;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class DrawerActivity extends AppCompatActivity {

    DrawerLayout drawer_layout;
    LinearLayout drawer;
    Button open, close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        drawer_layout = findViewById(R.id.drawer_layout);
        drawer = findViewById(R.id.drawer);
        open = findViewById(R.id.opendrawer);
        close = findViewById(R.id.closedrawer);

        //서랍열기
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭으로 해당 서랍이 열린다.
                drawer_layout.openDrawer(drawer);
            }
        });

        //서랍 닫기
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭으로 해당 서랍 닫는다.(지정한 서랍 닫기)
               //drawer_layout.closeDrawer(drawer);

                //열려있는 모든 서랍을 닫고 싶을때!(모든 서랍 닫기)
                drawer_layout.closeDrawers();
            }
        });

        //손가락 드래그로 메뉴 열고 닫기를 방지(버튼 클릭으로만 서랍을 제어)
        //                                                    특정 서랍에 값이 없다면, 모든 서랍 방지
        //drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,특정 서랍);
        //drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,drawer);

        //손가락 드래그로 메뉴 열고 닫기 가능
       // drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,drawer);

        //메뉴가 열리고 닫힘을 감지하는 이벤트 감지자
        drawer_layout.setDrawerListener(listener);

    }//onCreate()

    int menu = 0;

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {

        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            //메뉴가 열리는 중일 때, 감지하는 메서드
        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
            //메뉴가 완전히 열려있을 때, 감지하는 메서드
            menu = 1;
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,drawer);
        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {
            //메뉴가 완전히 닫혀있을 때, 감지하는 메서드
            menu = 2;
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,drawer);
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            //서랍의 상태를 감지하는 메서드
        }
    };

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
        if(menu == 2){
            finish();
        }
        drawer_layout.closeDrawers();

        }

    }
