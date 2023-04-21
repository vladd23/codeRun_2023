package ro.cognizant.coderun2023.domain;

import java.sql.Array;
import java.util.List;
import java.util.Objects;

public class Book {
    private Long id;
    private String bookName;
    private String author;
    private List<String> publisherList;

    public Book(Long id, String bookName, String author, List<String> publisherList) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.publisherList = publisherList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookName, book.bookName) && Objects.equals(author, book.author) && Objects.equals(publisherList, book.publisherList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName, author, publisherList);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", publisherList=" + publisherList +
                '}';
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

    public List<String> getPublisherList() {
        return publisherList;
    }

}
