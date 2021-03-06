import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyLibrary {
    public static ArrayList<Book> books = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static List<String> usersContent;
    public static List<String> booksContent;

    static {
        try {
            usersContent = new ArrayList<>(Files.readAllLines(Paths.get("src\\main\\java\\files\\users"), StandardCharsets.UTF_8));
            booksContent = new ArrayList<>(Files.readAllLines(Paths.get("src\\main\\java\\files\\books.txt"), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyLibrary() {
        initLibrary();
    }

    private void initLibrary() {
        File books_file = new File("src\\main\\java\\files\\books.txt");
        File users_files = new File("src\\main\\java\\files\\users");
        try {
            Scanner sc = new Scanner(books_file);
            Scanner sc1 = new Scanner(users_files);
            //skip first line
            String string_book = sc.nextLine();
            String string_user = sc1.nextLine();

            while (sc.hasNextLine()) {
                string_book = sc.nextLine();
                String[] book_params = string_book.split(";");
                Book book = new Book(book_params[0], book_params[1], Boolean.parseBoolean(book_params[2]));
                books.add(book);
            }
            sc.close();

            while (sc1.hasNextLine()) {
                string_book = sc1.nextLine();
                String[] user_params = string_book.split(";");
                String login = user_params[0];
                boolean librarian = Boolean.parseBoolean(user_params[1]);
                ArrayList<Book> borrowed_books = new ArrayList<>();
                for (int i = 2; i < user_params.length; i+=2) {
                    Book borrowed_book = books.get(Integer.parseInt(user_params[i]));
                    borrowed_books.add(borrowed_book);
                }
                User user = new User(login,borrowed_books);
                if(Boolean.parseBoolean(user_params[1]))user.setLibrarian(true);
                users.add(user);
            }
            sc1.close();
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void showBookList() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void updateUsers() throws IOException {
        Files.write(Paths.get("src\\main\\java\\files\\users"), usersContent, StandardCharsets.UTF_8);
    }
    public static void updateBooks() throws IOException {
        Files.write(Paths.get("src\\main\\java\\files\\books.txt"), booksContent, StandardCharsets.UTF_8);
    }

    public static int findLineByLogin(String login){
        for(int i = 0 ;i<usersContent.size();i++){
            if(usersContent.get(i).contains(login)){
                return i;
            }
        }
        return -1;
    }

    public static int findLineByBook(Book book){
        for(int i = 0 ;i<booksContent.size();i++){
            if(booksContent.get(i).contains(book.title) && booksContent.get(i).contains(book.author_name)){
                return i;
            }
        }
        return -1;
    }

}
