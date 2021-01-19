package com.ysk.ex_0119;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WorkActivity extends AppCompatActivity {

    TextView show_num, question;
    Button btn_yes, btn_no, btn_restart;
    LinearLayout linear;

    int result = 0; //결과출력용 변수
    int phase = 1; //단계

    final int YES = 1;
    final int NO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        show_num = findViewById(R.id.show_num);
        question = findViewById(R.id.question);

        btn_yes = findViewById(R.id.btn_yes);
        btn_no = findViewById(R.id.btn_no);
        btn_restart = findViewById(R.id.btn_restart);

        linear = findViewById(R.id.linear);

        btn_yes.setOnClickListener(click);
        btn_no.setOnClickListener(click);
        btn_restart.setOnClickListener(click);

    }//onCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch ( view.getId()){
                case R.id.btn_yes:
                    showPhase(YES);
                    break;

                case R.id.btn_no:
                    showPhase(NO);
                    break;

                case R.id.btn_restart:
                    question.setVisibility(View.VISIBLE);
                    btn_restart.setVisibility(View.GONE);
                    linear.setVisibility(View.VISIBLE);
                    show_num.setText("04 05 06 07 12 \n13 14 15 20 21 \n22 23 28 29 30");
                    phase = 1;
                    result = 0;
                    break;

            }//switch
        }
    };
    //단계구별 메서드
    private void showPhase(int select){//YES:1, NO:0 가 select로 넘어온다.
        String str = "";
        switch(phase){
            case 1:
                if(select == YES)
                    result+=4;

                    //다음 단계의 숫자목록을 준비
                    str="16 17 18 19 20 \n21 22 23 24 25 \n26 27 28 29 30";
                break;

            case 2:
                if(select == YES)
                    result+=16;

                    str="01 03 05 07 09 \n11 13 15 17 19 \n21 23 25 27 29";
                break;

            case 3:
                if(select == YES)
                    result+=1;
                str = "08 09 10 11 12 \n13 14 15 24 25 \n26 27 28 29 30";
                break;

            case 4:
                if(select == YES)
                    result+=8;
                str = "02 03 06 07 10 \n11 14 15 18 19 \n22 23 26 27 30";
                break;
            case 5:
                if(select == YES)
                    result+=2;
                //result의 결과가 0이거나 31인 경우
                if(result == 0 || result == 31){
                    str = "잘 못 선택한 문항이 존재합니다.";
                }else{
                    str = "당신이 생각한 숫자는 \n"+result+"입니다.";
                }
                question.setVisibility(View.INVISIBLE);
                linear.setVisibility(View.INVISIBLE);
                btn_restart.setVisibility(View.VISIBLE);
                break;

        }//switch
        show_num.setText(str);
        phase++;

    }
}