package com.firefox5.digibooky.service.book;


import com.firefox5.digibooky.api.book.dto.*;
import com.firefox5.digibooky.domain.book.Author;
import com.firefox5.digibooky.domain.book.Book;
import com.firefox5.digibooky.domain.book.BookRepository;
import com.firefox5.digibooky.domain.book.LendingInformation;
import com.firefox5.digibooky.domain.user.UserRepository;
import com.firefox5.digibooky.service.book.exceptions.EntryNotFoundException;
import com.firefox5.digibooky.service.security.Feature;
import com.firefox5.digibooky.service.security.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
            throw new EntryNotFoundException("No book found.");
        }
    }

    public List <DetailedRentedBookDTO> getOverdueBooks(@RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.OVERDUE_BOOKS);
        List <LendingInformation> lendingInformation = bookRepository.getRentedBooksList().keySet().stream()
                .filter(keyInfo-> keyInfo.getDueDate().isAfter(LocalDate.now()))
                .toList();
        return mapper.toDetailsRentedBookDTOList(lendingInformation, bookRepository.getRentedBooksList(), userRepository);
    }


    private LendingInformation getKeysByValue(Map<LendingInformation, Book> map, Book book) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), book))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();
    }

    public DetailedRentedBookDTO getEnhancedDetailedBookByIsbn(String isbn, @RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.ENHANCED_BOOK_DETAILS);
        Book rentedBook = bookRepository.getRentedBooksList().values()
                .stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow();
        LendingInformation lendingInformation = getKeysByValue(bookRepository.getRentedBooksList(), rentedBook);
        return mapper.toDetailedRentedBookDTO(lendingInformation, rentedBook, userRepository.getUserById(lendingInformation.getUserId()));
    }

    public DetailedBookDTO updateABook(UpdateBookDTO updateBookDTO, @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.UPDATE_A_BOOK);
        return mapper.toDetailedDto(bookRepository.updateBook(new Book(
                updateBookDTO.getTitle(),
                updateBookDTO.getIsbn(),
                updateBookDTO.getAuthor(),
                updateBookDTO.getSmallSummary(),
                updateBookDTO.isAvailability()
        )));
    }
    public DetailedRentedBookDTO lendABook(LendBookDTO lendBookDTO, @RequestHeader String authorization){
        int userID = securityService.validateAuthorization(authorization, Feature.LEND_A_BOOK);

        LendingInformation lendingInformation = new LendingInformation(userID, lendBookDTO.getIsbn());
        return mapper.toDetailedRentedBookDTO(lendingInformation,
                bookRepository.lendBook(new Book(
                        "test",
                        lendBookDTO.getIsbn(),
                        new Author("test","test"),
                        "test",
                        false),
                        lendingInformation),
                userRepository.getUserById(userID));
    }

    public BookDTO registerABook(CreateBookDTO createBookDTO, @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.REGISTER_A_NEW_BOOK);
        return mapper.toDto(bookRepository.save(new Book(
                createBookDTO.getTitle(),
                createBookDTO.getIsbn(),
                createBookDTO.getAuthor(),
                createBookDTO.getSmallSummary(),
                true)));
    }

    public BookDTO deleteABook(int id, @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.DELETE_A_BOOK);
        return mapper.toDto(bookRepository.delete(bookRepository.getById(id)));
    }


    public ReturnedBookDTO returnABook(int lendingID, @RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.RETURN_A_BOOK);
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
