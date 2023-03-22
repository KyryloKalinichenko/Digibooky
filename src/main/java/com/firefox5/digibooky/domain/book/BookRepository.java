package com.firefox5.digibooky.domain.book;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {
    private final List<Book> bookList;
    private final List<Book> softDeletedBookList;
    
    public BookRepository(){
        this.bookList = new ArrayList<>();
        this.softDeletedBookList = new ArrayList<>();
    }
    
    
    public Book save(Book book){
        bookList.add(book);
        return book;
    }
    public void delete(int id){
        bookList.remove(id);
    }


    public Book getById(int id){
        return bookList.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .get();
    }
    public List<Book> getByAuthorFirstName(String author){
        return bookList.stream()
                .filter(book -> Objects.equals(book.getAuthor().getFirstName(), author))
                .toList();
    }

    public List<Book> getByAuthorLastName(String author){
        return bookList.stream()
                .filter(book -> Objects.equals(book.getAuthor().getLastName(), author))
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
