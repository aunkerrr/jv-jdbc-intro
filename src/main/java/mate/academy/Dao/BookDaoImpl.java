package mate.academy.Dao;

import Model.Book;
import mate.academy.Util.ConnectionUtil;
import mate.academy.lib.Dao;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Dao
public class BookDaoImpl implements BookDao {

    @Override
    public Book create(Book book) {
        String query = "INSERT INTO book (id, title, author, isbn, published_year, price) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionUtil.getConncetion();
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setObject(4, book.getPublished_year());
            preparedStatement.setDouble(5, book.getPrice());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows <= 0) {
                throw new RuntimeException("Create Book failed");
            }

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Long id = resultSet.getObject(1, Long.class);
                book.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't insert book into DB", e);
        }

        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        String query
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
