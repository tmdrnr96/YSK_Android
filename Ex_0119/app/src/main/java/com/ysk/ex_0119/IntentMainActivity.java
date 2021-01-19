package com.ysk.ex_0119;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class IntentMainActivity extends AppCompatActivity {

    Button btn_call, btn_link, btn_sms, btn_camera, btn_gallery, btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);

        btn_call = findViewById(R.id.btn_call);
        btn_link = findViewById(R.id.btn_link);
        btn_sms = findViewById(R.id.btn_sms);
        btn_camera = findViewById(R.id.btn_camera);
        btn_gallery = findViewById(R.id.btn_gallery);
        btn_next = findViewById(R.id.btn_next);

        //전화걸기
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent클래스는 액티비티간의 전환만 사용된다 라고 생각하면 됨!!
                //Intent i = new Intent(Intent.ACTION_DIAL);
                // Uri.parse("tel:") 전화거는 페이지로 넘어간다.
               // i.setData(Uri.parse("tel:010-111-2222"));
             //   startActivity(i); //i의 정보를 가지고 화면을 전환
                // 전화거는 페이지(액티비티)가 현 액티비티 위에 쌓인다.

                //전화를 즉시 걸어주는 기능(매니페스트에 CALL_PHONE 권한 필수!!)
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:010-1111-1111"));
                startActivity(i);
            }
        });

        //웹페이지로 전환
        btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);//인터넷 페이지로 화면 전환
                // i.setData(Uri.parse("https://") 웹페이지로 접근하기 위한 식별자
                i.setData(Uri.parse("https://www.naver.com"));//전환할 url
                startActivity(i);
            }
        });

        //문자 전송 화면
        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("smsto:010-111-2222"));
                //위의 번호로 함꼐 전달할 메시지를 지정
                //map으로 저장(key값과 value값 지정)
                i.putExtra("sms_body","안녕하세요~");
                startActivity(i);
            }
        });

        //카메라 앱 접근
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//내장 카메라로 연결하는 설정
                //startActivity(i);

                //동영상 촬영 페이지로 전환
                Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE); //동영상 카메라로 연결하는 설정
                startActivity(i);
            }
        });

        //사진첩(갤러리)에 접근
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);//사진첨(갤러리)에 접근하기 위한 설정
                i.setType("image/*"); //모든타입의 이미지를 포함해서 가져온다.
                startActivity(i);

            }
        });

        //사용자 정의 액티비티로 전환
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //IntentSubActivity로 전환!
                //Intent i = new Intent( 현재 액티비티.this,전환할 액티비티.class );
                Intent i = new Intent( IntentMainActivity.this,IntentSubActivity.class );
                startActivity(i);
            }
        });
    }//onCreate()

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder dialog = new AlertDialog.Builder(IntentMainActivity.this);
        dialog.setTitle("Ex_0119");
        dialog.setMessage("앱을 종료하시겠습니까?");

        dialog.setPositiveButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        dialog.setNegativeButton("닫기", null);

        dialog.setCancelable(false);//true라면 처음 상태..

        dialog.show();//다이얼로그 노출
    }
}