package vo;

public class BookVO {

    private String b_title;//제목
    private String b_author;//저자
    private String b_price;//가격
    private String b_img;//이미지 경로

    //Alt + insert -> getter,setter를 만들 수 있음


    public String getB_title() {
        return b_title;
    }

    public void setB_title(String b_title) {
        this.b_title = b_title;
    }

    public String getB_author() {
        return b_author;
    }

    public void setB_author(String b_author) {
        this.b_author = b_author;
    }

    public String getB_price() {
        return b_price;
    }

    public void setB_price(String b_price) {
        this.b_price = b_price;
    }

    public String getB_img() {
        return b_img;
    }

    public void setB_img(String b_img) {
        this.b_img = b_img;
    }
}
