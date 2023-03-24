package com.firefox5.digibooky.api.book.dto;

public class LendBookDTO {
    private final String isbn;

    public LendBookDTO(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }
}
