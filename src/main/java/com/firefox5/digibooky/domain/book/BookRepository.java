package com.firefox5.digibooky.domain.book;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {
    private final List<Book> bookList;
    private final Map<LendingInformation, Book> rentedBooksList;
    
    public BookRepository(){
        this.bookList = new ArrayList<>();
        this.rentedBooksList = new HashMap<>();
    }
    
    
    public Book save(Book book){
        bookList.add(book);
        return book;
    }
    public Book delete(Book book){
        book.setAvailability(false);
        return book;
    }
    public Book switchToRentList(LendingInformation lendingInformation, Book book){
        rentedBooksList.put(lendingInformation, book);
        book.setAvailability(false);
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

    public Map<LendingInformation, Book> getRentedBooksList() {
        return rentedBooksList;
    }
}
