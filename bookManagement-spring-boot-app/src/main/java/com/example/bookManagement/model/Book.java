// represent the book entity in the database
// contains the id, title, author, and price of the book

package com.example.bookManagement.model;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    private Long id;

    @NotEmpty(message = "Title is mandatory")
    @Size(min = 3, message = "Title must be at least 3 characters long")
    private String title;

    @NotEmpty(message = "Author is mandatory")
    private String author;

    @Min(value = 0, message = "Price must be a positive number")
    private double price;
}
