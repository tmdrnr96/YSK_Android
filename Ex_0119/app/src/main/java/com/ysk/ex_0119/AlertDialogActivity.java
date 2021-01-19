package com.ysk.ex_0119;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogActivity extends AppCompatActivity {

    Button btn_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

        btn_show =findViewById(R.id.btn_show);


        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AlertDialog 생성
                //AlertDialog는 최대 세개의 버튼을 포함할 수 있는 팝업창   context: 얼랏 다이얼로그를 적용할 페이지
                AlertDialog.Builder dialog = new AlertDialog.Builder(AlertDialogActivity.this);
                dialog.setTitle("카카오톡");                         //this만 쓰면 위에 있는 인터페이스로 인식함..
                dialog.setMessage("앱을 평가해주세요");

                //버튼을 3개까지 넣을 수 있음.. (listener : 이벤트 감지자)(긍정)
                dialog.setPositiveButton("지금할게요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AlertDialogActivity.this,"평가감사합니다.",Toast.LENGTH_SHORT).show();
                    }
                });

                //이벤트 감지자를 null로 넣어 주면 이벤트 처리 없이 다이얼로그만 종료(부정)
                dialog.setNegativeButton("안할래요",null);

                //중립버튼
                dialog.setNeutralButton("나중에", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"나중에",Toast.LENGTH_SHORT).show();
                    }
                });

                //뒤로가기나 주변터치로 인한 다이얼로그의 종료 방지(무조건 선택을 해야 다이얼로그가 꺼짐..)
                dialog.setCancelable(false);//true라면 처음 상태..

                dialog.show();//다이얼로그 노출
            }
        });

    }
}