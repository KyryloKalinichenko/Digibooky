package com.firefox5.digibooky.service.book;

import com.firefox5.digibooky.api.book.dto.BookDTO;
import com.firefox5.digibooky.api.book.dto.DetailedBookDTO;
import com.firefox5.digibooky.api.book.dto.DetailedRentedBookDTO;
import com.firefox5.digibooky.api.book.dto.ReturnedBookDTO;
import com.firefox5.digibooky.domain.book.Book;
import com.firefox5.digibooky.domain.book.LendingInformation;
import com.firefox5.digibooky.domain.user.User;
import com.firefox5.digibooky.domain.user.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
                lendingInformation.getDueDate(),
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

    public List<DetailedRentedBookDTO> toDetailsRentedBookDTOList(List<LendingInformation> lendingInformations, Map<LendingInformation, Book> bookMap, UserRepository userRepository) {
        List <DetailedRentedBookDTO> convertedList = new ArrayList<>();
        lendingInformations.stream()
                .forEach(keyInfo -> convertedList
                        .add(toDetailedRentedBookDTO(keyInfo, bookMap.get(keyInfo),
                                userRepository.getUserById(keyInfo.getUserId()))));
        return convertedList;
    }
}
