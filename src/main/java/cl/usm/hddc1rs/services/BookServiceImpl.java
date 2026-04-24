package cl.usm.hddc1rs.services;

import cl.usm.hddc1rs.entities.Book;
import cl.usm.hddc1rs.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Override
    public boolean createBook(Book book) {
        try {
             this.bookRepository.insert(book);
             return true;
         } catch (Exception e) {
             return false;
         }
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
       return this.bookRepository.findByAutor(author) ; // ?
    }

    @Override
    public List<Book> filter(String query) {
        String queryLower = query.toLowerCase(); // allows case insensitive search
        List<Book> books = this.bookRepository.findAll();

        return books.stream()
                .filter(book ->
                        book.getAutor().toLowerCase().contains(queryLower)
                        || book.getTitulo().toLowerCase().contains(queryLower)
                ).collect(Collectors.toList());
    }
}
