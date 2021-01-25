package parse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ysk.ex_0122_naverapi.R;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;

import vo.BookVO;
//ArrayAdapter는 기본 생성자가 없기때문에 생성자를 생성 해줘야한다.
public class ViewModelAdapter extends ArrayAdapter<BookVO> {

    Context context; //화면 제어권자
    ArrayList<BookVO> list;
    BookVO vo;
    int resource;


    public ViewModelAdapter(Context context, int resource, ArrayList<BookVO> list, ListView mylistView) {
        super(context, resource, list);//ArrayAdapter는 super를 없애면 안된다.
        //리스트 뷰를 표현하기 위한 어댑터의 부모생성자는 반드시 세개의 파라미터(context,int,list)를 가지고 있어야 한다.

        this.context = context;
        this.list = list;
        this.resource = resource;

        //리스트뷰에 이벤트 감지자 등록
        mylistView.setOnItemClickListener( list_click );
    }

    //리스트뷰 클릭 이벤트 감지자

    AdapterView.OnItemClickListener list_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            String imgurl=list.get(i).getB_img();
            //서버에서 가져온 이미지 경로에서 bid만 추출
            String bookId = imgurl.substring(imgurl.lastIndexOf('/')+1,
                                            imgurl.lastIndexOf(".jpg"));

            //얻어낸 bid를 가지고 url로 연결
            String bookLink = "https://book.naver.com/bookdb/book_detail.nhn?bid=" + bookId;
            //새로운 창으로 화면 전환
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(bookLink));
            //context를 통해 도서 상세보기 페이지로 화면 전환
            context.startActivity(intent);
        }
    };

    @Override //xml 파일을 눈으로 볼 수 없기 때문에 View 형식으로 바꿔줘야한다.
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i("T","position : " + position);

        //xml문서를 View로 변환 시켜주는 클래스
        LayoutInflater linf = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        convertView = linf.inflate(resource,null);

        vo = list.get(position);

        TextView title = convertView.findViewById(R.id.book_title);
        TextView author = convertView.findViewById(R.id.book_auther);
        TextView price = convertView.findViewById(R.id.book_price);
        ImageView img = convertView.findViewById(R.id.book_img);

        title.setText(vo.getB_title());
        author.setText("저자 : "+vo.getB_author());
        price.setText("가격 : " +vo.getB_price());

        //서버통신을 통해 이미지 가지고 오기
        new ImgAsync(img).execute(vo.getB_img());//이미지 경로 :vo.getB_img()


        return convertView;//convertView에 담겨진 내용을 ListView로 변환(뿌려준다.)
    }//getView()

    class ImgAsync extends AsyncTask<String, Void, Bitmap>{

        Bitmap bitmap;
        ImageView imageView;

        public ImgAsync(ImageView imageView){
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {

                URL img_url = new URL(strings[0]);

                //inputStream을 통해 이미지 로드
                BufferedInputStream bis = new BufferedInputStream(img_url.openStream());

                bitmap = BitmapFactory.decodeStream(bis);//비트맵형식으로 바꾼다.

                bis.close();

           }catch (Exception e){

           }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            imageView.setImageBitmap(bitmap); //비트맵이 이미지 뷰로 전환

        }
    }//AsyncTask

}
