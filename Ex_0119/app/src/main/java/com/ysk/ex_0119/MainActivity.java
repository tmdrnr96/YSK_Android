package com.ysk.ex_0119;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt, txt_q;
    Button btn_y, btn_n, btn_re;

    int result = 0;
    int step = 1;

    int yes = 1;
    int no = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txt);

        txt_q = findViewById(R.id.txt_q);

        btn_y = findViewById(R.id.btn_y);
        btn_n = findViewById(R.id.btn_n);
        btn_re = findViewById(R.id.btn_re);


        btn_y.setOnClickListener(click);
        btn_n.setOnClickListener(click);
        btn_re.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.btn_y:
                    showPage(yes);
                    break;
                case R.id.btn_n:
                    showPage(no);
                    break;
                case R.id.btn_re:
                    step = 1;
                    result = 0;

                    txt.setText("04 05 06 07 12\n13 14 15 20 21\n22 23 28 29 30");
                    txt_q.setVisibility(View.VISIBLE);

                    btn_re.setVisibility(View.GONE);
                    btn_y.setVisibility(View.VISIBLE);
                    btn_n.setVisibility(View.VISIBLE);
                    break;
            }


        }
    };


    public void showPage(int select){
        String str = "";
        switch(step){
            case 1:
                if(select == yes) result+=4;
                str = "16 17 18 19 20\n21 22 23 24 25\n26 27 28 29 30";
                break;
            case 2:
                if(select == yes) result+= 16;
                str = "01 03 05 07 09\n11 13 15 17 19\n21 23 25 27 29";
                break;
            case 3:
                if(select == yes) result += 1;
                str = "08 09 10 11 12\n13 14 15 24 25\n26 27 28 29 30";
                break;
            case 4:
                if(select == yes) result += 8;
                str = "02 03 06 07 10\n11 14 15 18 19\n22 23 26 27 30";
                break;
            case 5:
                if(select == yes)result += 2;

                if(result == 0 || result == 31){
                    str = "잘못 선택한 문항이 있습니다.";
                }else{
                     str = "당신이 선택한 숫자는 "+result+"입니다.";
                }

                btn_y.setVisibility(View.GONE);
                btn_n.setVisibility(View.GONE);
                txt_q.setVisibility(View.GONE);
                btn_re.setVisibility(View.VISIBLE);
                break;
        }
        txt.setText(str);
        step++;

    }
}