package com.firefox5.digibooky.api.book;

import com.firefox5.digibooky.domain.book.Author;
import com.firefox5.digibooky.domain.book.Book;
import com.firefox5.digibooky.domain.book.LendingInformation;
import com.firefox5.digibooky.domain.user.User;

public class DetailedRentedBookDTO {
    private final String title;
    private final String isbn;
    private final Author author;
    private final int rentalId;
    private boolean available;
    private User user;

    public DetailedRentedBookDTO(String title, String isbn, Author author, int id, boolean available, User user) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.rentalId = id;
        this.available = available;
        if (!available) {
            this.user = user;
        }
    }


}
