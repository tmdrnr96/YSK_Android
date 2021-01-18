package com.ysk.ex_0118;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class VisibleActivity extends Activity {

    Button back1, back2, back3, btn_bottom;
    ImageView i_back1, i_back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visible);

        back1 = findViewById(R.id.back1);
        back2 = findViewById(R.id.back2);
        back3 = findViewById(R.id.back3);
        btn_bottom = findViewById(R.id.btn_bottom);

        i_back1 = findViewById(R.id.i_back1);
        i_back2 = findViewById(R.id.i_back2);

        back1.setOnClickListener(click);
        back2.setOnClickListener(click);
        back3.setOnClickListener(click);
        btn_bottom.setOnClickListener(click);

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.back1:
                    i_back1.setVisibility(View.VISIBLE);
                    i_back2.setVisibility(View.INVISIBLE);
                    break;

                case R.id.back2:
                    i_back1.setVisibility(View.INVISIBLE);
                    i_back2.setVisibility(View.VISIBLE);
                    break;

                case R.id.back3:
                    //btn_bottom이 gone이라면.. (보이지 않을 때)
                    if(btn_bottom.getVisibility() != View.VISIBLE){ // == View.GONE
                        btn_bottom.setVisibility(View.VISIBLE);
                    }else{
                        btn_bottom.setVisibility(View.GONE);
                    }
                    break;

                case R.id.btn_bottom:
                    Toast.makeText(getApplicationContext(),"Hello!",Toast.LENGTH_SHORT).show();
                    break;

            }

        }
    };


}