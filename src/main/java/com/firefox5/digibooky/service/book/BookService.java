package com.firefox5.digibooky.service.book;


import com.firefox5.digibooky.api.book.*;
import com.firefox5.digibooky.domain.book.Book;
import com.firefox5.digibooky.domain.book.BookRepository;
import com.firefox5.digibooky.domain.book.LendingInformation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;
    private final BookMapper mapper;
    private final Parsing parsing;

    public BookService(BookRepository repository, BookMapper mapper, Parsing parsing) {
        this.repository = repository;
        this.mapper = mapper;
        this.parsing = parsing;
    }


    public List<BookDTO> getAllBooks() {
        return mapper.toDtos(repository.getAll());
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
            return mapper.toDetailedDto(repository.getByIsbn(isbn).get(0));
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("No book found.");
        }
    }

//    public BookDTO getEnhancedDetailedBookByIsbn(String isbn){
//
//    }

    /*---Throw exception when no book is found---*/
    public DetailedBookDTO updateABook(UpdateBookDTO updateBookDTO) {
        try {
            Book updatedBook = repository.getByIsbn(updateBookDTO.getIsbn()).get(0);
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
        repository.save(newBook);
        return mapper.toDto(newBook);
    }

    public BookDTO deleteABook(int id) {
        return mapper.toDto(repository.delete(repository.getById(id)));
    }


//    public DetailedRentedBookDTO lendABook(String isbn){
//
//    }

    public ReturnedBookDTO returnABook(int lendingID){
        LendingInformation keyMap = repository.getRentedBooksList()
                .keySet()
                .stream()
                .filter(lendingInformation -> lendingInformation.getLendingId() == lendingID)
                .findFirst()
                .orElseThrow();

        Book returnedBook = repository.getRentedBooksList().get(keyMap);
        returnedBook.setAvailability(true);
        repository.getRentedBooksList().remove(keyMap);
        return mapper.toReturnedBookDTO(returnedBook);
    }
}
