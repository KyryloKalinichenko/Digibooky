package com.firefox5.digibooky.api.book;

import com.firefox5.digibooky.domain.book.Author;

public class UpdateBookDTO {
    private final String title;
    private final Author author;
    private final String smallSummary;

    public UpdateBookDTO(String title, Author author, String smallSummary) {
        this.title = title;
        this.author = author;
        this.smallSummary = smallSummary;
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

}
