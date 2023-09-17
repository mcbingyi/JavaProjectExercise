package system;

import java.sql.ResultSet;

public class Book {
    private String ISBN;
    private String bookName;
    private String author;
    private String press;
    private String type;
    private int stock;

    public Book() {
    }

    public Book(String ISBN, String bookName, String author, String press, String type, int stock) {
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.author = author;
        this.press = press;
        this.type = type;
        this.stock = stock;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Book queryBook(String isbn) {
        // 查询图书
        SQLServerConnect sqlServerConnect = new SQLServerConnect();
        ResultSet resultSet = sqlServerConnect.queryBookFromDatabase(isbn);
        try {
            while (resultSet.next()) {
                Book book = new Book();
                book.setISBN(resultSet.getString("ISBN"));
                book.setBookName(resultSet.getString("bookName"));
                book.setAuthor(resultSet.getString("author"));
                book.setPress(resultSet.getString("press"));
                book.setType(resultSet.getString("type"));
                book.setStock(resultSet.getInt("stock"));
                return book;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        // 测试查询图书
//        Book book = new Book();
//        book = book.queryBook("123");
//        System.out.println(book.getBookName());
//    }
}
