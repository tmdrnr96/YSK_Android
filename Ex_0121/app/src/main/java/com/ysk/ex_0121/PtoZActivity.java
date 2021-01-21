package com.ysk.ex_0121;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class PtoZActivity extends AppCompatActivity {

    ImageView img;

    //라이브러리를 추가해서 생긴 클래스
    PhotoViewAttacher attacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pto_z);

        img = findViewById(R.id.img);

        //이미지 뷰와 PhotoViewAttcher를 연결
        attacher = new PhotoViewAttacher(img);
        attacher.update();
    }//onCreate()
}