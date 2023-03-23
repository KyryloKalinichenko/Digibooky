package com.firefox5.digibooky.book.controlertest;

import com.firefox5.digibooky.api.book.BookDTO;
import com.firefox5.digibooky.api.book.CreateBookDTO;
import com.firefox5.digibooky.domain.book.Author;
import com.firefox5.digibooky.domain.book.BookRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ControlerEndToEndTest {

    @Autowired
    private BookRepository bookRepository;

    @Value("8080")
    private int port;

    @Test
    void registerBook_givenABookToCreate_thenNewlyCreatedBookIsSavedAndReturned(){
        CreateBookDTO createBookDTO = new CreateBookDTO(
                "coucou",
                "5648151",
                new Author("Max", "Jean"),
                "super livre");

        BookDTO bookDTO =
                RestAssured
                        .given()
                        .body(createBookDTO)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/books")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(BookDTO.class);

        assertThat(bookDTO.getIsbn()).isNotBlank();
        assertThat(bookDTO.getTitle()).isEqualTo(createBookDTO.getTitle());
        assertThat(bookDTO.getIsbn()).isEqualTo(createBookDTO.getIsbn());
        assertThat(bookDTO.getAuthor()).isEqualTo(createBookDTO.getAuthor());
    }
}
