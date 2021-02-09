import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyLibrary {
    public static ArrayList<Book> books = new ArrayList<>();



    public MyLibrary(){
        initLibrary();
    }

    private void initLibrary() {
        //Todo find in files;
        File file = new File("src\\main\\java\\files\\books.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String i = sc.next();

                System.out.println(i);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void showBookList(){
        for(Book book:books){
            System.out.println(book);
        }
    }

}
