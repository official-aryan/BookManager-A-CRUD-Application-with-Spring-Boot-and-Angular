package com.example.log4j_Implementation.LOG4j.Service;

import com.example.log4j_Implementation.LOG4j.Entity.Book;
import com.example.log4j_Implementation.LOG4j.Repository.BookRepository;
import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private static final Logger logger = LogManager.getLogger(BookService.class);


    public BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks()
    {
        logger.info("Fetching all  the books");
        return bookRepository.findAll();
    }
    public Book getBookById(Long id)
    {
        logger.info("Fetching Book by id");
        return bookRepository.findById(id).orElse(null);
    }

    public Book createBook(Book book)
    {
        logger.info("Crete a book",book);
        return bookRepository.save(book);
    }
    public Book updateBook(Long id,Book updatedBook)
    {
        logger.info("Updating book with ID: {}", id);
        Book existingBook=bookRepository.findById(id).orElse(null);

        if (existingBook!=null)
        {
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setTitle(updatedBook.getTitle());
            return bookRepository.save(existingBook);


        }
        else
        {
            logger.error("Book with ID{} not found",id);
            return null;
        }


    }

    public boolean deleteBook(Long id)
    {
        logger.info("Deleting book with ID: {}", id);
        if(bookRepository.existsById(id))
        {
            bookRepository.deleteById(id);
            return true;
        }
        else {
            logger.error("Book with ID {} not found", id);
            return false;
        }

    }


}
