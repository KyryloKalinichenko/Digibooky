package com.firefox5.digibooky.api.book;

import com.firefox5.digibooky.domain.book.Author;
import com.firefox5.digibooky.service.book.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<DetailedBookDTO> getAllBooks(){return bookService.getAllBooks();}


    @GetMapping(params = "isbn")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBookByIsbn(@RequestParam String isbn){
        return bookService.getABookByIsbn(isbn);
    }

    @GetMapping(params = "authorFirstName")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBookByAuthorFirstName(@RequestParam String authorFirstName){
        return bookService.getABookByAuthorFirstName(authorFirstName);
    }
    @GetMapping(params = "authorLastName")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getABookByAuthorLastName(@RequestParam String authorLastName){
        return bookService.getABookByAuthorLastName(authorLastName);
    }
    @GetMapping(params = {"title", "showDetails"})
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBookByTitle(@RequestParam String title){
        return bookService.getABookByTitle(title);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public DetailedBookDTO createBook(@RequestBody DetailedBookDTO detailedBookDTO){
        return bookService.registerABook(detailedBookDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteABook(@PathVariable("id")int id){
        bookService.deleteABook(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO updateABook(@PathVariable("id") int id, @RequestBody UpdateBookDTO updateBookDTO){
        return bookService.updateABook(id, updateBookDTO);
    }
}
