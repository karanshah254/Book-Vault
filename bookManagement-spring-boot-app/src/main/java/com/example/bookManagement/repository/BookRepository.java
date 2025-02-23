package com.example.bookManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.bookManagement.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT MAX(b.id) FROM Book b") // Query to find the maximum ID
    Long findMaxId();

    List<Book> findAllByOrderByIdAsc(); // Fetch all books sorted by ID
}
