package cl.usm.hddc1rs.services;

import cl.usm.hddc1rs.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    boolean createBook(Book book);
    List<Book> getAllBooks();
    List<Book> getBooksByAuthor(String author);
    List<Book> filter(String query);
    Optional<Book> getBookByIsbn(String isbn);
}
