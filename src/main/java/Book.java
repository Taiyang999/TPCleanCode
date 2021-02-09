import java.util.Date;

public class Book {
    String title,author_name;
    Date borrowing_date;
    boolean borrowed;

    public Book(String title, String author_name,boolean borrowed){
        this.title=title;
        this.author_name=author_name;
        this.borrowed=borrowed;
        this.borrowing_date=null;
    }

    public String toString(){
        return ("Book name: "+this.title+", author: "+this.author_name+", borrowed: "+this.borrowed);
    }
}
