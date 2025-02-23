// This class contains the business logic.

package com.example.bookManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookManagement.model.Book;
import com.example.bookManagement.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // add the book
    public Book add(Book book) {
        // Find the next available ID
        Long nextId = findNextAvailableId();
        book.setId(nextId); // Set the ID
        return bookRepository.save(book);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a book by ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Update a book
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setPrice(updatedBook.getPrice());
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    // Delete a book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
        reorderIds();
    }

    // Reorder IDs after deletion
    private void reorderIds() {
        List<Book> books = bookRepository.findAllByOrderByIdAsc(); // Fetch all books sorted by ID
        long newId = 1;
        for (Book book : books) {
            if (book.getId() != newId) {
                book.setId(newId); // Update the ID
                bookRepository.save(book); // Save the updated book
            }
            newId++;
        }
    }

    // Find the next available ID
    private Long findNextAvailableId() {
        List<Book> books = bookRepository.findAllByOrderByIdAsc();
        if (books.isEmpty()) {
            return 1L; // Start from 1 if the table is empty
        } else {
            // Find the maximum ID and increment it by 1
            Long maxId = books.get(books.size() - 1).getId();
            return maxId + 1;
        }
    }
}
