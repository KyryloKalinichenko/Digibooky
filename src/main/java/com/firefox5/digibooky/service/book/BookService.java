package com.firefox5.digibooky.service.book;


import com.firefox5.digibooky.api.book.BookDTO;
import com.firefox5.digibooky.api.book.DetailedBookDTO;
import com.firefox5.digibooky.api.book.UpdateBookDTO;
import com.firefox5.digibooky.domain.book.Book;
import com.firefox5.digibooky.domain.book.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;
    private final BookMapper mapper;

    public BookService(BookRepository repository, BookMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<DetailedBookDTO> getAllBooks() {
        return mapper.toDetailedBookDtos(repository.getAll());
    }

    public List<BookDTO> getABookByIsbn(String isbn){
        return mapper.toDtos(repository.getByIsbn(isbn));
    }

    public List<BookDTO> getABookByTitle(String title){
        return mapper.toDtos(repository.getByTitle(title));
    }

    public List<BookDTO> getABookByAuthorFirstName(String author){
        return mapper.toDtos(repository.getByAuthorFirstName(author));
    }

    public List<BookDTO> getABookByAuthorLastName(String author){
        return mapper.toDtos(repository.getByAuthorLastName(author));
    }

    public BookDTO updateABook(int id, UpdateBookDTO updateBookDTO){
        Book updatedBook = repository.getById(id);
        updatedBook.setTitle(updateBookDTO.getTitle());
        updatedBook.setAuthor(updateBookDTO.getAuthor());
        updatedBook.setSmallSummary(updateBookDTO.getSmallSummary());
        repository.save(updatedBook);
        return mapper.toDto(updatedBook);
    }

    /*---If not librarian get a 403 custom message---*/
    public DetailedBookDTO registerABook(DetailedBookDTO detailedBookDTO){
        Book newBook = new Book(
                detailedBookDTO.getTitle(),
                detailedBookDTO.getIsbn(),
                detailedBookDTO.getAuthor(),
                detailedBookDTO.getSmallSummary());
        repository.save(newBook);
        return mapper.toDetailedBookDto(newBook);
    }

    public void deleteABook(int id){
        repository.delete(id);
    }
}
