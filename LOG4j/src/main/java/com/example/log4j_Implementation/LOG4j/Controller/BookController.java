package com.example.log4j_Implementation.LOG4j.Controller;

import com.example.log4j_Implementation.LOG4j.Entity.Book;
import com.example.log4j_Implementation.LOG4j.Service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {

    private static final Logger logger = LogManager.getLogger(BookController.class);

    public BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    //http://localhost:8080/api/books
    public ResponseEntity<List<Book>> getAllBooks()
    {
        logger.info("Request recieved to fetch all books");
        List<Book> allBooks = bookService.getAllBooks();
        return ResponseEntity.ok().body(allBooks);
    }

    @GetMapping("/{id}")
    //http://localhost:8080/api/books/2
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        logger.info("Request received to fetch book with ID: {}", id);
        Book book = bookService.getBookById(id);
        if (book != null) {
            return ResponseEntity.ok().body(book);
        } else {
            logger.error("Book with ID {} not found", id);
            return ResponseEntity.notFound().build();
            //The ResponseEntity.notFound().build() method is used to construct a response entity with an HTTP status of 404 Not Found. This is typically returned when a requested resource is not found.
        }
    }

    @PostMapping
    //http://localhost:8080/api/books
    public ResponseEntity<Book> createBook(@RequestBody Book book)
    {
        logger.info("Request received to create a new book");
        Book createBook=bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createBook);


    }

    @PutMapping("/{id}")
    //http://localhost:8080/api/books/1
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook)
    {
        logger.info("Request received to update book with ID: {}", id);
        Book updated = bookService.updateBook(id, updatedBook);

        if(updated !=null)
        {
            return ResponseEntity.ok().body(updated);
        }
        else {
            logger.error("Book with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    //http://localhost:8080/api/books/3
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        logger.info("Request received to delete book with ID: {}", id);
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            logger.error("Book with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }
}
