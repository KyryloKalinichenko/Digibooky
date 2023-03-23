package com.firefox5.digibooky.service.book;

import com.firefox5.digibooky.api.book.BookDTO;
import com.firefox5.digibooky.api.book.DetailedBookDTO;
import com.firefox5.digibooky.api.book.DetailedRentedBookDTO;
import com.firefox5.digibooky.api.book.ReturnedBookDTO;
import com.firefox5.digibooky.domain.book.Book;
import com.firefox5.digibooky.domain.book.LendingInformation;
import com.firefox5.digibooky.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public DetailedBookDTO toDetailedDto(Book book){
        return new DetailedBookDTO(
                book.getTitle(),
                book.getIsbn(),
                book.getAuthor(),
                book.getSmallSummary(),
                book.getId(),
                book.getAvailability()
        );
    }

    public BookDTO toDto(Book book){
        return new BookDTO(
                book.getTitle(),
                book.getIsbn(),
                book.getAuthor(),
                book.getSmallSummary()
        );
    }

    public List<BookDTO> toDtos(List<Book> bookList){
        return bookList.stream()
                .filter(Book::getAvailability)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public DetailedRentedBookDTO toDetailedRentedBookDTO(LendingInformation lendingInformation, Book book, User user){
        return new DetailedRentedBookDTO(
                book.getTitle(),
                book.getIsbn(),
                book.getAuthor(),
                lendingInformation.getLendingId(),
                book.getAvailability(),
                user
        );
    }

    public ReturnedBookDTO toReturnedBookDTO(Book returnedBook) {
        return new ReturnedBookDTO(
                returnedBook.getTitle(),
                returnedBook.getIsbn(),
                returnedBook.getAvailability()
        );
    }
}
