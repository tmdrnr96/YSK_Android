package com.ysk.ex_0126;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ajalt.reprint.core.AuthenticationFailureReason;
import com.github.ajalt.reprint.core.AuthenticationListener;
import com.github.ajalt.reprint.core.Reprint;

public class MainActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);

        //지문사용을 위한 초기 설정
        //Reprint 가 없으면, 라이브러리 설치가 제대로 안된 것임..
        Reprint.initialize(MainActivity.this);

        //지문사용 가능 기기 체크
        if(checkDeviceSpec()){//지문 사용 가능

            Reprint.authenticate(new AuthenticationListener() {
                @Override
                public void onSuccess(int moduleTag) {
                    //인증 성공시 호출
                    text.setText("인증 성공");
                }

                @Override
                public void onFailure(AuthenticationFailureReason failureReason, boolean fatal, CharSequence errorMessage, int moduleTag, int errorCode) {
                    //인증 실패시 호출
                    text.setText("인증 실패\n다시 시도하세요!");

                    if(fatal){//5회 지문인식 실패 fatal이 true가 된다.
                        text.setText("35초 뒤에 다시 시도하세요!");

                    }
                }
            });

        }else{
            //현재 지문 사용 불가능
            finish();
        }

    }//onCreate()

    //지문인식이 가능한지 판단하는 메서드
    public boolean checkDeviceSpec(){

        //기기 자체에서 지문인식을 지원하는지 여부를 판단 (있으면 true, 없으면 false)
        boolean hardware = Reprint.isHardwarePresent();

        //기기에 등록해놓은 지문이 있는지를 판단 (있으면 true, 없으면 false)
        boolean register = Reprint.hasFingerprintRegistered();

        if(!hardware){//hardware == false
            //지문인식을 지원하지 않는 기기
            Toast.makeText(MainActivity.this, "지문인식을 지원하지 않는 기기입니다.", Toast.LENGTH_SHORT).show();
        }else{
            //지문인식을 지원하는 기기
            if(!register){//register 지문기능은 지원하는데 등록된 지문이 없는 경우
                Toast.makeText(MainActivity.this,
                                "등록된 지문이 없습니다.\n기기에 지문을 먼저 등록해주세요.",
                                Toast.LENGTH_SHORT).show();
            }
        }

        return hardware && register;//둘 중에 하나 라도 거짓이라면 fasle가 반환된다.

    }//checkDeviceSpec

}