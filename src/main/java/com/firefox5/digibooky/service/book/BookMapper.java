package com.firefox5.digibooky.service.book;

import com.firefox5.digibooky.api.book.BookDTO;
import com.firefox5.digibooky.api.book.DetailedBookDTO;
import com.firefox5.digibooky.domain.book.Book;
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
}
