package parse;

import com.ysk.ex_0122_naverapi.MainActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vo.BookVO;

public class Parser {

    //xml파싱(웹에서 요소(제목, 저자, 가격...)를 검색하여 vo에 담는 과정)을 위한 클래스

    BookVO vo;
    String myQuery="";//검색어

    public ArrayList<BookVO> connetNaver(ArrayList<BookVO> list){

        try {

            //EditText에 쓰여져 있는 문장을 UTF-8구조로 인코딩해서 서버로 전송
            myQuery = URLEncoder.encode(MainActivity.search.getText().toString(),"UTF8");

            int count = 100;//검색결과를 100건 표시

            //정보를 얻기위한 URL준비
            String urlStr = "https://openapi.naver.com/v1/search/book.xml?query="+myQuery+"&display="+count;

            //위의 경로를 URL클래스를 통해서 웹으로 연결
            URL url = new URL(urlStr); //해당 url로 접속을 시도한다.

            //연결객체를 통해 실제로 url경로로의 접속을 시도한다.
            HttpURLConnection connection =(HttpURLConnection)url.openConnection();

            //발급받은 아이디를 connection에게 전달
            connection.setRequestProperty("X-Naver-Client-Id","aGtIaf8xVkWks5P80FLd");

            //발급받은 시크릿을 connection에게 전달
            connection.setRequestProperty("X-Naver-Client-Secret","8nVqbiHee4");

            //이후로 item정보를 꺼내올 수 있음..(connection에게 아이디와 시크릿을 전달한 후에.. 가능)
            //인증까지 완료한 결과를 xml을 통해 받게 되는데, 이를 받기위한 클래스
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();//jsp의 프리페이스테이트먼트와 같은 역할
            XmlPullParser parser = factory.newPullParser();//jsp의 리절트셋과 같은 역할

            parser.setInput(connection.getInputStream(),null);

            //parser를 통해 서버에서 얻어온 각각의 요소들을 반복수행 처리
            int parserEvent = parser.getEventType();

            //현재의 parser의 커서 위치가 XML문서의 끝을 만날때 까지만 while문을 반복
            while(parserEvent != XmlPullParser.END_DOCUMENT){

                //시작태그(<aa>)를 만났을 때 이름을 얻어낸다.(</aa> 끝나는 태그)
                if(parserEvent == XmlPullParser.START_TAG){//시작태그를 만났다면..

                    String tagName = parser.getName();//시작태그의 이름(<title>, <link>,<author>...)

                    if(tagName.equalsIgnoreCase("title")){ //시작태그 이름이 title이라면..
                        vo = new BookVO();
                        String title = parser.nextText(); //시작태그와 끝태그의 값을 가져온다.

                        //네이버는 검색한 단어를 강조하기 위해 <b>태그로 묶어서 결과를 돌려준다.
                        // 이를 제거하기 위해 정규식을 사용해야한다.
                        //정규식을 사용하기 위해서는 Pattern클래스를 사용해야한다.
                        Pattern pattern = Pattern.compile("<.*?>");//</태그> 한글자짜리 태그를 모두 잡아낸다.
                        Matcher matcher = pattern.matcher(title); //자바의 test와 같은 역할을 한다.(pattern.test())
                        
                        if(matcher.find()){
                            String s_title = matcher.replaceAll("");//정규식에 해당하는 패턴을 찾아서 모두 ""빈값으로 바꾼다.
                            vo.setB_title(s_title); //제거할 태그가 있다면 태그를 없애고 출력
                        }else {
                            vo.setB_title(title);//제거할 태그가 없다면 그냥 출력
                        }

                    }else if(tagName.equalsIgnoreCase("image")){ //지정된 요소이름으로 순차적으로 값을 얻어야한다.
                        String img = parser.nextText();
                        vo.setB_img(img);

                    }else if(tagName.equalsIgnoreCase("author")){
                        String author = parser.nextText();
                        vo.setB_author(author);

                    }else if(tagName.equalsIgnoreCase("price")){
                        String price = parser.nextText();
                        vo.setB_price(price);

                        //모든 정보를 저장한 vo를 ArrayList에 담는다.
                        list.add(vo);
                    }

                }//if : XmlPullParser.START_TAG

                //다음 요소로 커서를 이동! (title 요소 -> image 요소 -> auther요소 ->...)
                //필요없는 요소는 건너뛰면서 필요한 요소만 가져온다.
                parserEvent = parser.next();

            }//while


        }catch (Exception e){


        }

            return list;

    }//connetNaver
}
