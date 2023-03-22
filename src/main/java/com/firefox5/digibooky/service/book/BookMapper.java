package com.firefox5.digibooky.service.book;

import com.firefox5.digibooky.api.book.BookDTO;
import com.firefox5.digibooky.api.book.DetailedBookDTO;
import com.firefox5.digibooky.domain.book.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public BookDTO toDto(Book book){
        return new BookDTO(
                book.getTitle(),
                book.getIsbn(),
                book.getAuthor()
        );
    }

    public DetailedBookDTO toDetailedBookDto(Book book){
        return new DetailedBookDTO(
                book.getTitle(),
                book.getIsbn(),
                book.getAuthor(),
                book.getSmallSummary()
        );
    }

    public List<BookDTO> toDtos(List<Book> bookList){
        return bookList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<DetailedBookDTO> toDetailedBookDtos(List<Book> bookList){
        return bookList.stream()
                .map(this::toDetailedBookDto)
                .collect(Collectors.toList());
    }
}
