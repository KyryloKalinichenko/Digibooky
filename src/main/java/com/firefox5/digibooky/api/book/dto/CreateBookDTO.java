package com.firefox5.digibooky.api.book.dto;

import com.firefox5.digibooky.domain.book.Author;

public class CreateBookDTO {

    private final String title;
    private final String isbn;
    private final Author author;
    private final String smallSummary;

    public CreateBookDTO(String title, String isbn, Author author, String smallSummary) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.smallSummary = smallSummary;
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
}
