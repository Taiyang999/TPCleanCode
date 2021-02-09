import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        MyLibrary ml = new MyLibrary();
        System.out.println("Step 0: Everyone can see the content of the library");
        MyLibrary.showBookList();
        Book book2 = MyLibrary.books.get(1);
        System.out.println();
        User u1 = User.connect("mars");
        User u2 = User.connect("jupiter");
        assert u1 != null;
        assert u2 != null;
        //User u3 = new User("mercury");
        System.out.println(u1);
        System.out.println(u2);
        //System.out.println();

        System.out.println("Step 1: borrow");

        u1.borrow(book2);
        MyLibrary.showBookList();
        System.out.println(u1);
        System.out.println();

        System.out.println("Step 2: return");
        u1.return_book(book2);
        MyLibrary.showBookList();
        System.out.println(u1);
        System.out.println();
/*
        System.out.println("Step 3: can't get more");
        u1.borrow(book2);
        u1.borrow(MyLibrary.books.get(2));
        u1.borrow(MyLibrary.books.get(3));
        u1.borrow(MyLibrary.books.get(4));
        u1.borrow(MyLibrary.books.get(5));
        MyLibrary.showBookList();
        System.out.println(u1);
        System.out.println();

        System.out.println("Step 4: same user");
        User u3 = new User("mars");
        System.out.println();
        System.out.println("Step 5: addbook");
        u1.addBook(new Book("booook","author same",false));
        u2.addBook(new Book("not","author same",false));
        MyLibrary.showBookList();
        */
    }
}
