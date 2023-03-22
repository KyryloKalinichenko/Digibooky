package com.firefox5.digibooky.api.book;

import com.firefox5.digibooky.domain.book.Author;

public class UpdateBookDTO {
    private final String title;
    private final Author author;
    private final String smallSummary;
    private final String isbn;
    private final boolean availability;

    public UpdateBookDTO(String title, Author author, String smallSummary, String isbn, boolean availability) {
        this.title = title;
        this.author = author;
        this.smallSummary = smallSummary;
        this.isbn = isbn;
        this.availability = availability;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getSmallSummary() {
        return smallSummary;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailability() {
        return availability;
    }
}
