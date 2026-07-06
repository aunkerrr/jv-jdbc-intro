package mate.academy.Dao;

import Model.Book;
import mate.academy.Util.ConnectionUtil;
import mate.academy.lib.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class BookDaoImpl implements BookDao {

    @Override
    public Book create(Book book) {
        String query = "INSERT INTO book (title, author, isbn, published_year, price) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionUtil.getConncetion();
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

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
        String query = "SELECT * FROM book WHERE id = ?";

        try (Connection connection = ConnectionUtil.getConncetion();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Book book = mapResultSetToBook(resultSet);
                return Optional.of(book);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Can't find Book by ID", e);
        }

        return Optional.empty();
    }

    @Override
    public List<Book> findAll() {
        String query = "SELECT * FROM book";
        List<Book> books = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getConncetion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                books.add(mapResultSetToBook(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Can't get all books from DB", e);
        }

        return books;
    }

    @Override
    public Book update(Book book) {
        String query = "UPDATE book SET title = ?, author = ?, isbn = ?, published_year = ?, price = ? WHERE id = ?;";

        try (Connection connection = ConnectionUtil.getConncetion();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setObject(4, book.getPublished_year());
            preparedStatement.setDouble(5, book.getPrice());
            preparedStatement.setObject(6, book.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows <= 0) {
                throw new RuntimeException("Update Book failed");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Can't insert book into DB", e);
        }
        return book;
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "DELETE FROM book WHERE id = ?";

        try (Connection connection = ConnectionUtil.getConncetion();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setLong(1, id);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows <= 0) {
                return false;
            }

        } catch (SQLException e) {
            throw new  RuntimeException("Can't delete Book by ID", e);
        }

        return true;
    }

    private Book mapResultSetToBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setPublished_year(resultSet.getInt("published_year"));
        book.setPrice(resultSet.getFloat("price"));
        return book;
    }
}
