package com.ysk.ex_0115;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Work1Activity extends AppCompatActivity {

    Button btn;
    EditText et;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work1);

        btn = findViewById(R.id.btn);
        et = findViewById(R.id.et);
        txt = findViewById(R.id.txt);

        btn.setOnClickListener(click);

    }//onCreate

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String str = et.getText().toString();
            String rev = "";

            for(int i = str.length()-1; i >= 0; i--){
                rev += str.charAt(i);
            }

            if(str.equals(rev)){
                txt.setText("회문수");
            }else{
                txt.setText("안회문수");
            }
        }
    };


}