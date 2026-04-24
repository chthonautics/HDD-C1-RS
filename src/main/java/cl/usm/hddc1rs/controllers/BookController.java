package cl.usm.hddc1rs.controllers;

import cl.usm.hddc1rs.entities.Book;
import cl.usm.hddc1rs.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/libros")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String search){
        try {
            if(search != null && !search.isEmpty()) {
                return ResponseEntity.ok(this.bookService.filter(search));
            } else {
                return ResponseEntity.ok(bookService.getAllBooks());
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/libros")
    public ResponseEntity<?> createBook(@RequestBody @Valid Book book){
        boolean res = bookService.createBook(book);

        if(res) return ResponseEntity.ok().build();

        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/libros/{author}")
    public ResponseEntity<?> getBooksByAuthor(@PathVariable String author){
        try{
            List<Book> books = bookService.getBooksByAuthor(author);

            if(books.isEmpty()) return ResponseEntity.notFound().build();

            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
