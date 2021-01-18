package com.ysk.ex_0118;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

//AndroidMainfest에서 앱 테마를 수동으로 지정하였으므로,
//appcompatActivity가 아닌 일반 Activity를 상속 받아야 한다.(자동 임포트 Alt+Enter)
public class ButtonEffectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_effect);
    }
}