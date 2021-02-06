package com.kdh.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainPageActivity extends AppCompatActivity {

    DrawerLayout drawer_layout;
    LinearLayout drawer;
    Button open, btn2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        drawer_layout = findViewById(R.id.drawer_layout);
        drawer = findViewById(R.id.drawer);
        open = findViewById(R.id.opendrawer);
        btn2 = findViewById(R.id.btn2);

        //서랍열기
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer_layout.openDrawer(drawer);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainPageActivity.this,TimerAddActivity.class);
                startActivity(i);
                finish();
            }
        });


    }// oncreate

    int menu = 0;

    DrawerLayout.DrawerListener listner = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            //메뉴가 열리고 있는 중일 때
        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
            //메뉴가 완전히 열려있을 때
            menu = 1;
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,drawer);
        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {
            //메뉴가 완전히 닫혀있을 때
            menu = 2;
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, drawer);
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            //서랍의 상태
        }
    };


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(menu == 2){
            finish();
        }
        drawer_layout.closeDrawers();

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };


    }


}