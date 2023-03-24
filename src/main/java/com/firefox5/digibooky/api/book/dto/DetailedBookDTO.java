package com.firefox5.digibooky.api.book.dto;

import com.firefox5.digibooky.domain.book.Author;

public class DetailedBookDTO {

    private final String title;
    private final String isbn;
    private final Author author;
    private final String smallSummary;
    private final int id;
    private boolean available;

    public DetailedBookDTO(String title, String isbn, Author author, String smallSummary, int id, boolean available) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.smallSummary = smallSummary;
        this.id = id;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Author getAuthor() {
        return author;
    }
    public String getSmallSummary(){
        return smallSummary;
    }
    public int getId(){
        return id;
    }
    public boolean getAvailability(){
        return available;
    }
}
