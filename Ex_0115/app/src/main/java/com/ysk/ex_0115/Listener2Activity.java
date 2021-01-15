package com.ysk.ex_0115;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Listener2Activity extends AppCompatActivity {

    Button btn1, btn2;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener2);

        /* 검색시 레이아웃 1에 있는 id도 같이 검색됨.(객체의 id는 모두 R.java라는 곳에 모인다. )
        * 레이아웃 1에 있는 id를 레이아웃 2에서 사용할 시 오류.. 잘 확인할 것..*/

        /*findViewBtId()를 통해 검색하는 id는 반드시 현재 액티비티가 참조하고 있는
        레이아웃(activity_listener2)에 존재하는 것들만 검색이 가능하다.*/
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        tv = findViewById(R.id.tv);

        btn1.setOnClickListener(myclick);
        btn2.setOnClickListener(myclick);
    }//onCreate

    View.OnClickListener myclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //클릭한 버튼에 쓰여져 있는 텍스트를 가져온다.
            // (view에는 getText가 없다(부모는 자식객체의 것을 가지고 올 수 없음..))
            //String str = ((Button)view).getText().toString();
            //tv.setText(str+"을 클릭함");

            if(view  == btn1){
                tv.setText(btn1.getText().toString() + "을 클릭함");
            }else{
                tv.setText("버튼2를 클릭함");
            }
        }
    };
}