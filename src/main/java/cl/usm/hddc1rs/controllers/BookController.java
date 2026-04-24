package cl.usm.hddc1rs.controllers;

import cl.usm.hddc1rs.entities.Book;
import cl.usm.hddc1rs.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        // avoid collision before saving
        // this is better than the default because it returns a code
        // unlike the default which returns nothing at all
        if(bookService.getBookByIsbn(book.getIsbn()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        boolean res = bookService.createBook(book);
        if(res) return ResponseEntity.ok().build(); // success!

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
