import java.util.ArrayList;

public class User {
    String login;
    ArrayList<Book> borrowed_book;
    boolean librarian;


    public User(String login) {
        this.login = login;
        this.borrowed_book = new ArrayList<>();
        this.librarian=false;
    }

    public void setLibrarian(boolean librarian) {
        this.librarian = librarian;
    }

    public void borrow(Book book){
        if(!book.borrowed && borrowed_book.size()<4 && MyLibrary.books.contains(book)){
            this.borrowed_book.add(book);
            MyLibrary.books.get(MyLibrary.books.indexOf(book)).borrowed=true;
        }
    }

    public void return_book(Book book){
        this.borrowed_book.remove(book);
        MyLibrary.books.get(MyLibrary.books.indexOf(book)).borrowed=false;
    }

    public void addBook(Book book){
        if(this.librarian){
            MyLibrary.books.add(book);
        }
    }

}
