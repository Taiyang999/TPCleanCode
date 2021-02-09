import java.util.ArrayList;

public class User {
    String login;
    ArrayList<Book> borrowed_book;
    boolean librarian;


    public User(String login) {
        for(User user : MyLibrary.users){
            if(user.login.equals(login)){
                System.out.println("login invalid");
                return;
            }
        }
        this.login = login;
        this.borrowed_book = new ArrayList<>();
        this.librarian=false;
        //Todo user books file
    }

    public User(String login,ArrayList<Book> borrowed_books) {
        this.login = login;
        this.borrowed_book = borrowed_books;
        this.librarian=false;
    }

    public void setLibrarian(boolean librarian) {
        this.librarian = librarian;
    }

    public void borrow(Book book){
        if(!book.borrowed && borrowed_book.size()<4 && MyLibrary.books.contains(book)){
            this.borrowed_book.add(book);
            MyLibrary.books.get(MyLibrary.books.indexOf(book)).borrowed=true;
            //Todo modify books file && users file
        }
    }

    public void return_book(Book book){
        this.borrowed_book.remove(book);
        MyLibrary.books.get(MyLibrary.books.indexOf(book)).borrowed=false;
        //Todo modify books file && users file
    }

    public void addBook(Book book){
        if(this.librarian){
            MyLibrary.books.add(book);
            System.out.println("Success");
        }else{
            System.out.println("acces denied");
        }
        //Todo modify books file
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
