package com.firefox5.digibooky.api.book;

import com.firefox5.digibooky.service.book.BookService;
import com.firefox5.digibooky.service.security.Feature;
import com.firefox5.digibooky.service.security.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;
    private final SecurityService securityService;

    public BookController(BookService bookService, SecurityService securityService) {
        this.bookService = bookService;
        this.securityService = securityService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAllBooks(){return bookService.getAllBooks();}

    @GetMapping(path = "/{isbn}/showDetails")
    public DetailedBookDTO getOneBook(@PathVariable String isbn){
        return bookService.getDetailedBookByIsbn(isbn);
    }

    @GetMapping(path = "/{isbn}/showEnhancedDetails")
    public DetailedRentedBookDTO getOneRentedBook(@PathVariable String isbn, @RequestHeader String authorization){
        return bookService.getEnhancedDetailedBookByIsbn(isbn, authorization);
    }
    @GetMapping(path = "/showOverdueBooks")
    public List<DetailedRentedBookDTO> getAllOverdueBooks(@RequestHeader String authorization){
        return bookService.getOverdueBooks(authorization);
    }
    @GetMapping(params = "isbn")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBookByIsbn(@RequestParam String isbn){
        return bookService.getABookByIsbn(isbn);
    }

    @GetMapping(params = "title")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBookByTitle(@RequestParam String title){
        return bookService.getABookByTitle(title);
    }

    @GetMapping(params = "author")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBookByAuthor(@RequestParam String author){
        return bookService.getABookByAuthor(author);
    }

    /*---Handling exception---*/
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody CreateBookDTO createBookDTO, @RequestHeader String authorization){
        return bookService.registerABook(createBookDTO, authorization);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO deleteABook(@PathVariable("id") int id, @RequestHeader String authorization){
        return bookService.deleteABook(id, authorization);
    }

    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public DetailedBookDTO updateABook(@RequestBody UpdateBookDTO updateBookDTO, @RequestHeader String authorization){
        return bookService.updateABook(updateBookDTO, authorization);
    }

    @PutMapping(path = "/lend", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public DetailedRentedBookDTO lendABook(@RequestBody String isbn, @RequestHeader String authorization){
        return bookService.lendABook(isbn, authorization);
    }

    @PutMapping(path = "/return", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ReturnedBookDTO returnABook(@RequestBody int lendingID, @RequestHeader String authorization){
        return bookService.returnABook(lendingID, authorization);
    }
}
