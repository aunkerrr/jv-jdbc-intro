package model;

import java.util.Objects;

public class Book {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int publishedYear;
    private float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) {
            return false;
        }
        return publishedYear == book.publishedYear
                && Float.compare(price, book.price)
                == 0 && Objects.equals(id, book.id)
                && Objects.equals(title, book.title)
                && Objects.equals(author, book.author)
                && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isbn, publishedYear, price);
    }

    @Override
    public String toString() {
        return "Book{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", author='" + author + '\''
                + ", isbn='" + isbn + '\''
                + ", publishedYear=" + publishedYear
                + ", price=" + price
                + '}';
    }
}
