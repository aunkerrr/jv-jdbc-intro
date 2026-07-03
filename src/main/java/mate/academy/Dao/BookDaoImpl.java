package mate.academy.Dao;

import Model.Book;
import mate.academy.lib.Dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@Dao
public class BookDaoImpl implements BookDao {

    @Override
    public Book create(Book book) {
        String sql = "INSERT INTO book (id, title, author, isbn, published_year, price) VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection connection = connecti ) {

        }

        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Book> findAll() {
        return List.of();
    }

    @Override
    public Book update(Book book) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
