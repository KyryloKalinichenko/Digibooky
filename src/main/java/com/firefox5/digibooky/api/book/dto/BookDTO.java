package com.firefox5.digibooky.api.book.dto;

import com.firefox5.digibooky.domain.book.Author;


public class BookDTO {

    private final String title;
    private final String isbn;
    private final Author author;

    public BookDTO(String title, String isbn, Author author, String smallSummary) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
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
}
