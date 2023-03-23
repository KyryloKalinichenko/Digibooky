package com.firefox5.digibooky.api.book;

import com.firefox5.digibooky.domain.book.Author;
import com.firefox5.digibooky.domain.user.User;

import java.time.LocalDate;

public class DetailedRentedBookDTO {
    private final String title;
    private final String isbn;
    private final Author author;
    private final int rentalId;
    private final LocalDate dueDate;
    private boolean available;
    private User user;

    public DetailedRentedBookDTO(String title, String isbn, Author author, int rentalId, LocalDate dueDate, boolean available, User user) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.rentalId = rentalId;
        this.dueDate = dueDate;
        this.available = available;
        if (!available) {
            this.user = user;
        }
    }


}
