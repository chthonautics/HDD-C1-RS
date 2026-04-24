package cl.usm.hddc1rs.repositories;

import cl.usm.hddc1rs.entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByAutor(String autor); // helper method to find by author
}
