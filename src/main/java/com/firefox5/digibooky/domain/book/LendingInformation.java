package com.firefox5.digibooky.domain.book;

import java.time.LocalDate;
import java.util.Objects;

public class LendingInformation {
    private final int userId;
    private final int lendingId;
    private static int counter;
    private final String bookIsbn;
    private final LocalDate dueDate;

    public LendingInformation(int userId, String bookIsbn) {
        lendingId = ++counter;
        dueDate = LocalDate.now().plusWeeks(3);
        this.bookIsbn = bookIsbn;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public int getLendingId() {
        return lendingId;
    }

    public static int getCounter() {
        return counter;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LendingInformation that = (LendingInformation) o;
        return userId == that.userId && lendingId == that.lendingId && Objects.equals(bookIsbn, that.bookIsbn) && Objects.equals(dueDate, that.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, lendingId, bookIsbn, dueDate);
    }
}
