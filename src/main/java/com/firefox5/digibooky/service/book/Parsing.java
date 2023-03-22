package com.firefox5.digibooky.service.book;

import com.firefox5.digibooky.api.book.BookDTO;
import com.firefox5.digibooky.domain.book.BookRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Parsing {

    private final BookRepository repository;
    private final BookMapper mapper;

    public Parsing(BookRepository repository, BookMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<BookDTO> checkForWildcardsInIsbn(String stringToBeChecked) {
        if (stringToBeChecked.startsWith("*") && stringToBeChecked.length() == 1) {
            return mapper.toDtos(repository.getAll());
        }
        if (stringToBeChecked.startsWith("*")) {
            return mapper.toDtos(repository.getAll().stream()
                    .filter(book -> book.getIsbn().endsWith(stringToBeChecked.substring(1)))
                    .toList());
        }
        if (stringToBeChecked.endsWith("*")) {
            return mapper.toDtos(repository.getAll().stream()
                    .filter(book -> book.getIsbn().startsWith(stringToBeChecked.substring(0, stringToBeChecked.indexOf("*"))))
                    .toList());
        }
        return mapper.toDtos(repository.getByIsbn(stringToBeChecked));
    }

    public List<BookDTO> checkForWildcardsInTitle(String stringToBeChecked) {
        if (stringToBeChecked.startsWith("*") && stringToBeChecked.length() == 1) {
            return mapper.toDtos(repository.getAll());
        }
        if (stringToBeChecked.startsWith("*")) {
            return mapper.toDtos(repository.getAll().stream()
                    .filter(book -> book.getTitle().endsWith(stringToBeChecked.substring(1)))
                    .toList());
        }
        if (stringToBeChecked.endsWith("*")) {
            return mapper.toDtos(repository.getAll().stream()
                    .filter(book -> book.getTitle().startsWith(stringToBeChecked.substring(0, stringToBeChecked.indexOf("*"))))
                    .toList());
        }
        return mapper.toDtos(repository.getByTitle(stringToBeChecked));
    }

    public List<BookDTO> checkForWildcardsInAuthor(String stringToBeChecked) {
        if (stringToBeChecked.startsWith("*") && stringToBeChecked.length() == 1) {
            return mapper.toDtos(repository.getAll());
        }
        if (stringToBeChecked.startsWith("*")) {
            return mapper.toDtos(repository.getAll().stream()
                    .filter(book -> book.getAuthor().toString().endsWith(stringToBeChecked.substring(1)))
                    .collect(Collectors.toList()));
        }
        if (stringToBeChecked.endsWith("*")) {
            return mapper.toDtos(repository.getAll().stream()
                    .filter(book -> book.getAuthor().toString().startsWith(stringToBeChecked.substring(0, stringToBeChecked.indexOf("*"))))
                    .collect(Collectors.toList()));
        }
        return mapper.toDtos(repository.getByAuthor(stringToBeChecked));
    }
}
