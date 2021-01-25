package com.ysk.ex_0122_naverapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import parse.Parser;
import parse.ViewModelAdapter;
import vo.BookVO;

public class MainActivity extends AppCompatActivity {

    public static EditText search;
    Button btn_search;
    ListView myListView;
    Parser parser;
    ArrayList<BookVO> list;
    ViewModelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
        btn_search = findViewById(R.id.btn_search);
        myListView = findViewById(R.id.myListView);

        parser = new Parser();

        //검색버튼
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리스트를 새로 만드는 이유?
                //버튼을 누를때마다 초기화를 해주지 않으면 검색했던 값들이 누적되기 때문에
                //검색을 할때마다 초기화를 시켜줌으로써 값이 누적되지 않게 만든다.
                list = new ArrayList<>();
                adapter = null;

                //Async클래스를 통한 서버통신
                new NaverAsync().execute();//doInBackground 메서드를 호출!

            }
        });

        //가상키보드에서 돋보기 버튼(actionSearch)을 클릭했는지 감지
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if( i == EditorInfo.IME_ACTION_SEARCH){
                    //돋보기 버튼 클릭시 btn_search를 강제로 클릭!
                    btn_search.performClick(); //해당버튼을 강제클릭하는 메서드
                }
                return true;
            }
        });

    }//onCreate()

    //통신 전용 스레드 생성(AsyncTask : 서버 통신을 위한 클래스)
    //파라미터 값으로 아무것도 넣고 싶지 않으면 null이 아닌 Void 클래스로 넣어줘야한다.)

    //AsyncTask클래스는 생성시 세 개의 제너릭 타입을 가진다.
    //1.doInBackground메서드의 파라미터 타입
    //2.UI의 진행상태를 관리하는 onProgressUpdate()라는 메서드가 오버라이딩 되어 있는경우
    //이 메서드에서 사용할 자료형 타입
    //3. doInBackground의 반환형이자, opPostExecute의 파라미터 타입 (활용빈도가 제일 높다.)
    class NaverAsync extends AsyncTask<Void,Void,ArrayList<BookVO>>{

        @Override
        protected ArrayList<BookVO> doInBackground(Void... voids) {//... : 알아서 배열로 바꿔준다.
            //doInBackground : 각종 반복이나 제어 등 서버접속과 관련된 주된 처리 로직을 담당하는 메서드
            //스레드의 런메서드와 같은 영역!
            return parser.connetNaver(list);//통신 시작!
        }//doInBackground

        //통신이 끝나면 완료된 결과가 자동으로 onPostExecute의 파라미터로 넘어온다.
        @Override
        protected void onPostExecute(ArrayList<BookVO> bookVOS) {
            //doInBackground의 작업결과가 현재 메서드의 파라미터로 넘어온다.

            adapter = new ViewModelAdapter(MainActivity.this,
                                            R.layout.book_item,//리스트뷰의 항목을 디자인한 xml파일
                                            bookVOS, myListView);
            //생성된 어댑터를 리스트 뷰에 세팅
            //setAdapter()메서드가 호출되면 해당 ViewModelAdapter의 getView()메서드가
            //bookVOS의 size()만큼 자동으로 반복된다.
            myListView.setAdapter(adapter);
        }//onPostExecute
    }//NaverAsync

}