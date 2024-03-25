package com.example.log4j_Implementation.LOG4j.Repository;

import com.example.log4j_Implementation.LOG4j.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
