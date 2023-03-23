package com.firefox5.digibooky.api.book;

import com.firefox5.digibooky.service.book.BookService;
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

//    @GetMapping(path = "/{isbn}/showDetails")
//    public DetailedRentedBookDTO getOneRentedBook(@PathVariable String isbn){
//        return bookService.getEnhancedDetailedBookByIsbn(isbn);
//    }

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
    public BookDTO createBook(@RequestBody CreateBookDTO createBookDTO){
        return bookService.registerABook(createBookDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO deleteABook(@PathVariable("id") int id){
        return bookService.deleteABook(id);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public DetailedBookDTO updateABook(@RequestBody UpdateBookDTO updateBookDTO){
        return bookService.updateABook(updateBookDTO);
    }

//    @PutMapping(consumes = "application/json", produces = "application/json")
//    @ResponseStatus(HttpStatus.OK)
//    public DetailedRentedBookDTO lendABook(@RequestBody String isbn){
//        return bookService.lendABook(isbn);
//    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ReturnedBookDTO returnABook(@RequestBody int lendingID){
        return bookService.returnABook(lendingID);
    }
}
