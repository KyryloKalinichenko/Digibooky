package com.firefox5.digibooky.service.book;


import com.firefox5.digibooky.api.book.*;
import com.firefox5.digibooky.domain.book.Book;
import com.firefox5.digibooky.domain.book.BookRepository;
import com.firefox5.digibooky.domain.book.LendingInformation;
import com.firefox5.digibooky.domain.user.UserRepository;
import com.firefox5.digibooky.service.security.Feature;
import com.firefox5.digibooky.service.security.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookMapper mapper;
    private final Parsing parsing;
    private final SecurityService securityService;

    public BookService(BookRepository repository, BookMapper mapper, Parsing parsing, SecurityService securityService, UserRepository userRepository) {
        this.bookRepository = repository;
        this.mapper = mapper;
        this.parsing = parsing;
        this.securityService = securityService;
        this.userRepository = userRepository;
    }


    public List<BookDTO> getAllBooks() {
        return mapper.toDtos(bookRepository.getAll());
    }

    public List<BookDTO> getABookByIsbn(String isbn) {
        return parsing.checkForWildcardsInIsbn(isbn);
    }

    public List<BookDTO> getABookByTitle(String title) {
        return parsing.checkForWildcardsInTitle(title);
    }

    public List<BookDTO> getABookByAuthor(String author) {
        return parsing.checkForWildcardsInAuthor(author);
    }

    public DetailedBookDTO getDetailedBookByIsbn(String isbn) {
        try {
            return mapper.toDetailedDto(bookRepository.getByIsbn(isbn).get(0));
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("No book found.");
        }
    }

    private LendingInformation getKeysByValue(Map<LendingInformation, Book> map, Book book) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), book))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();
    }

    public DetailedRentedBookDTO getEnhancedDetailedBookByIsbn(String isbn){
        Book rentedBook = bookRepository.getRentedBooksList().values()
                .stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow();
        LendingInformation lendingInformation = getKeysByValue(bookRepository.getRentedBooksList(), rentedBook);
        return mapper.toDetailedRentedBookDTO(lendingInformation, rentedBook, userRepository.getUserById(lendingInformation.getUserId()));
    }

    /*---Throw exception when no book is found---*/
    public DetailedBookDTO updateABook(UpdateBookDTO updateBookDTO) {
        try {
            Book updatedBook = bookRepository.getByIsbn(updateBookDTO.getIsbn()).get(0);
            updatedBook.setTitle(updateBookDTO.getTitle());
            updatedBook.setAuthor(updateBookDTO.getAuthor());
            updatedBook.setSmallSummary(updateBookDTO.getSmallSummary());
            updatedBook.setAvailability(updateBookDTO.isAvailability());
            return mapper.toDetailedDto(updatedBook);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("No book found.");
        }
    }

    /*---If not librarian get a 403 custom message---*/
    public BookDTO registerABook(CreateBookDTO createBookDTO) {
        Book newBook = new Book(
                createBookDTO.getTitle(),
                createBookDTO.getIsbn(),
                createBookDTO.getAuthor(),
                createBookDTO.getSmallSummary(),
                true);
        bookRepository.save(newBook);
        return mapper.toDto(newBook);
    }

    public BookDTO deleteABook(int id) {
        return mapper.toDto(bookRepository.delete(bookRepository.getById(id)));
    }


    public DetailedRentedBookDTO lendABook(String isbn, @RequestHeader String authorization){
        int userID = securityService.validateAuthorization(authorization, Feature.LEND_A_BOOK);
        LendingInformation lendingInformation = new LendingInformation(userID, isbn);
        Book rentedBook = bookRepository.getByIsbn(isbn).get(0);
        bookRepository.switchToRentList(lendingInformation, rentedBook);

        return mapper.toDetailedRentedBookDTO(
                lendingInformation,
                rentedBook,
                userRepository.getUserById(userID));
    }

    public ReturnedBookDTO returnABook(int lendingID){
        LendingInformation keyMap = bookRepository.getRentedBooksList()
                .keySet()
                .stream()
                .filter(lendingInformation -> lendingInformation.getLendingId() == lendingID)
                .findFirst()
                .orElseThrow();

        Book returnedBook = bookRepository.getRentedBooksList().get(keyMap);
        returnedBook.setAvailability(true);
        bookRepository.getRentedBooksList().remove(keyMap);
        return mapper.toReturnedBookDTO(returnedBook);
    }
}
