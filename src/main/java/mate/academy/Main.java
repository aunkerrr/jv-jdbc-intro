package mate.academy;

import Model.Book;
import mate.academy.Dao.BookDao;
import mate.academy.lib.Injector;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        BookDao bookDao = (BookDao) injector.getInstance(BookDao.class);

        System.out.println("Testing create operation: ");
        Book newBook = new Book();
        newBook.setTitle("Clean Code");
        newBook.setAuthor("Robert C. Martin");
        newBook.setIsbn("978-0132350884");
        newBook.setPublished_year("2008");
        newBook.setPrice(35.50f);

        Book savedbBook = bookDao.cr
    }
}
