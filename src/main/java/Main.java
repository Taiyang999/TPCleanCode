public class Main {

    public static void main(String[] args){
        MyLibrary ml = new MyLibrary();
        MyLibrary.showBookList();
        Book book2 = MyLibrary.books.get(1);

        User u1 = User.connect("mars");
        User u2 = User.connect("jupiter");
        System.out.println(u1);
        System.out.println(u2);

        System.out.println("Step 1: borrow");
        assert u1 != null;
        u1.borrow(book2);
        MyLibrary.showBookList();
        System.out.println(u1);

        System.out.println("Step 2: return");
        u1.return_book(book2);
        MyLibrary.showBookList();
        System.out.println(u1);

        System.out.println("Step 3: can't get more");
        u1.borrow(book2);
        u1.borrow(MyLibrary.books.get(2));
        u1.borrow(MyLibrary.books.get(3));
        u1.borrow(MyLibrary.books.get(4));
        u1.borrow(MyLibrary.books.get(5));
        MyLibrary.showBookList();
        System.out.println(u1);

        System.out.println("Step 4: same user");
        User u3 = new User("mars");
    }
}
