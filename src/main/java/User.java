import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class User {
    String login;
    ArrayList<Book> borrowed_book;
    boolean librarian;


    public User(String login) throws IOException {
        for(User user : MyLibrary.users){
            if(user.login.equals(login)){
                System.out.println("login invalid");
                return;
            }
        }
        this.login = login;
        this.borrowed_book = new ArrayList<>();
        this.librarian=false;
        MyLibrary.usersContent.add(login+";false;");
        MyLibrary.updateUsers();
    }

    public User(String login,ArrayList<Book> borrowed_books) {
        this.login = login;
        this.borrowed_book = borrowed_books;
        this.librarian=false;
    }

    public void setLibrarian(boolean librarian) {
        this.librarian = librarian;
    }

    public void borrow(Book book) throws IOException {
        if(!book.borrowed && borrowed_book.size()<4 && MyLibrary.books.contains(book)){
            this.borrowed_book.add(book);
            MyLibrary.books.get(MyLibrary.books.indexOf(book)).borrowed=true;
            String replaceInBookstxt = book.title+";"+book.author_name+";"+true;
            int bookIndice = MyLibrary.findLineByBook(book);
            MyLibrary.booksContent.set(bookIndice,replaceInBookstxt);
            MyLibrary.updateBooks();
            int userIndice = MyLibrary.findLineByLogin(this.login);
            MyLibrary.usersContent.set(userIndice,MyLibrary.usersContent.get(userIndice)+bookIndice+";date");
            MyLibrary.updateUsers();
        }
    }

    public void return_book(Book book) throws IOException {
        this.borrowed_book.remove(book);
        MyLibrary.books.get(MyLibrary.books.indexOf(book)).borrowed=false;
        String replaceInBookstxt = book.title+";"+book.author_name+";"+false;
        int bookIndice = MyLibrary.findLineByBook(book);
        MyLibrary.booksContent.set(bookIndice,replaceInBookstxt);
        MyLibrary.updateBooks();
        int userIndice = MyLibrary.findLineByLogin(this.login);
        MyLibrary.usersContent.set(userIndice,MyLibrary.usersContent.get(userIndice).replace(String.valueOf(bookIndice)+";date",""));
        MyLibrary.updateUsers();
    }

    public void addBook(Book book) throws IOException {
        if(this.librarian){
            MyLibrary.books.add(book);

            System.out.println("Success");
            MyLibrary.booksContent.add(book.title+";"+book.author_name+";false");
            MyLibrary.updateBooks();
        }else{
            System.out.println("acces denied");
        }
    }

    public String toString(){
        String isLibrarian = !this.librarian ?"user":"librarian";
        String res = "User: "+this.login+"("+isLibrarian+")";
        for(int i = 0;i<this.borrowed_book.size();i++){
            Book book = borrowed_book.get(i);
            String toString_book = ", book"+(i+1)+": "+book.title+"("+book.author_name+")";
            res = res + toString_book;
        }
        return res;
    }

    public static User connect(String login){
        for (User user:MyLibrary.users) {
            if(user.login.equals(login)){
                return user;
            }
        }
        return null;
    }

}
