package com.firefox5.digibooky.service.book;

import com.firefox5.digibooky.api.book.BookDTO;
import com.firefox5.digibooky.domain.book.BookRepository;

import java.util.List;

public class Parsing {

    private final BookRepository repository;
    private final BookMapper mapper;

    public Parsing(BookRepository repository, BookMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<BookDTO> checkForWildcards(String stringToBeChecked){
        if(stringToBeChecked.startsWith("*")){
            mapper.toDtos(repository.getAll()).stream()
                    .
        }
        if(stringToBeChecked.endsWith("*")){

        }
        if(stringToBeChecked.contains("*")){

        }
        else{

        }
    }
}
