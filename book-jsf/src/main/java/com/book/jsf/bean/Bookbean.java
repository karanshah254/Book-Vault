package com.book.jsf.bean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.book.jsf.dao.BookDao;
import com.book.jsf.model.Book;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class Bookbean {
    private Book book = new Book();
    private List<Book> bookList;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getBookList() {
        try {
            bookList = new BookDao().getAllBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public void saveBook() {
        try {
            new BookDao().addBook(book);
            book = new Book(); // reset form
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        try {
            new BookDao().deleteBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
