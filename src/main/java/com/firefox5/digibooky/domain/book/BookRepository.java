package com.firefox5.digibooky.domain.book;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {
    private final List<Book> bookList;
    
    public BookRepository(){
        this.bookList = new ArrayList<>();
    }
    
    
    public Book save(Book book){
        bookList.add(book);
        return book;
    }
    public Book delete(Book book){
        book.setAvailability(false);
        return book;
    }
    public Book recoverDeletedBook(Book book){
        book.setAvailability(true);
        return book;
    }

    public Book getById(int id){
        return bookList.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .get();
    }
    public List<Book> getByAuthor(String author){
        return bookList.stream()
                .filter(book -> Objects.equals(book.getAuthor().toString(), author))
                .toList();
    }
    public List<Book> getByIsbn(String isbn){
        return bookList.stream()
                .filter(book -> Objects.equals(book.getIsbn(), isbn))
                .toList();
    }
    public List<Book> getByTitle(String title){
        return bookList.stream()
                .filter(book -> Objects.equals(book.getTitle(), title))
                .toList();
    }
    
    public List<Book> getAll(){
        return bookList;
    }
}
