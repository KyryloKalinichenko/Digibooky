package com.firefox5.digibooky.api.book;

public class ReturnedBookDTO {
    private final String title;
    private final String isbn;
    private final boolean availability;

    public ReturnedBookDTO(String title, String isbn, boolean availability) {
        this.title = title;
        this.isbn = isbn;
        this.availability = availability;
    }

    public String getTitle() {
            return title;
    }
    public String getIsbn() {
        return isbn;
    }
    public boolean isAvailability() {
        return availability;
    }
}

