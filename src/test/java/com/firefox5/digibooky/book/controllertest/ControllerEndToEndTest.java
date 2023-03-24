package com.firefox5.digibooky.book.controllertest;

import com.firefox5.digibooky.api.book.BookDTO;
import com.firefox5.digibooky.api.book.CreateBookDTO;
import com.firefox5.digibooky.domain.book.Author;
import com.firefox5.digibooky.domain.book.Book;
import com.firefox5.digibooky.domain.book.BookRepository;
import com.firefox5.digibooky.service.security.Feature;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookController EndToEnd Testing")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ControllerEndToEndTest {

    private Book someBook;
    @Autowired
    private BookRepository bookRepository;
    @Value("8085")
    private int port;
    @BeforeEach
    void setup(){
        someBook = new Book(
                "testingisgood",
                "1234567891234",
                new Author("Max", "Jean"),
                "book about testing",
                true);
        bookRepository.save(someBook);
    }


    @Nested
    class RegisterABook{
        @Test
        void registerBook_givenABookToCreate_thenNewlyCreatedBookIsSavedAndReturned(){
            CreateBookDTO createBookDTO = new CreateBookDTO(
                    "testingisgood",
                    "1234567891234",
                    new Author("Max", "Jean"),
                    "book about testing");

            BookDTO bookDTO =
                    RestAssured
                            .given()
                            .auth()
                            .preemptive()
                            .basic("librarian@gmail.com", "123")
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


    @Nested
    class GetAllBooks{
        @Test
        void getAllBooks(){
            BookDTO bookDTO = new BookDTO(
                    "testingisgood",
                    "1234567891234",
                    new Author("Max", "Jean"),
                    "book about testing");

            BookDTO[] result =
                    RestAssured
                            .given()
                            .when()
                            .port(port)
                            .get("/books")
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.OK.value())
                            .extract()
                            .as(BookDTO[].class);

            assertThat(result)
                    .isNotNull()
                    .isNotEmpty();
            /*---Check if its contained---*/
        }
    }


    @Nested
    class GetABookByISBN{
        @Test
        void getOneBookUsingISBN(){
            BookDTO[] result =
                    RestAssured
                            .given()
                            .when()
                            .port(port)
                            .get("/books?isbn=" + someBook.getIsbn())
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.OK.value())
                            .extract()
                            .as(BookDTO[].class);

            assertThat(result)
                    .isNotNull()
                    .isNotEmpty();
            /*---Test a list compared to an object---*/
        }
        @Test
        void getOneBookUsingISBN_withAWildCardToCompleteTheEndOfTheISBN(){
            BookDTO[] result =
                    RestAssured
                            .given()
                            .when()
                            .port(port)
                            .get("/books?isbn=123*")
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.OK.value())
                            .extract()
                            .as(BookDTO[].class);

            assertThat(result)
                    .isNotNull()
                    .isNotEmpty();
            /*---Test a list compared to an object---*/
        }

        @Test
        void getOneBookUsingISBN_withAWildCardToCompleteTheStartOfTheISBN(){
            BookDTO[] result =
                    RestAssured
                            .given()
                            .when()
                            .port(port)
                            .get("/books?isbn=*234")
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.OK.value())
                            .extract()
                            .as(BookDTO[].class);

            assertThat(result)
                    .isNotNull()
                    .isNotEmpty();
            /*---Test a list compared to an object---*/
        }

        @Test
        void getOneBookUsingISBN_withOnlyAWildCard(){
            BookDTO[] result =
                    RestAssured
                            .given()
                            .when()
                            .port(port)
                            .get("/books?isbn=*")
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.OK.value())
                            .extract()
                            .as(BookDTO[].class);

            assertThat(result)
                    .isNotNull()
                    .isNotEmpty();
            /*---Test a list compared to an object---*/
        }
    }


    @Nested
    class GetABookByTitle{
        @Test
        void getOneBookUsingTitle(){
            BookDTO[] result =
                    RestAssured
                            .given()
                            .when()
                            .port(port)
                            .get("/books?title=" + someBook.getTitle())
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.OK.value())
                            .extract()
                            .as(BookDTO[].class);

            assertThat(result)
                    .isNotNull()
                    .isNotEmpty();
            /*---Test a list compared to an object---*/
        }
        @Test
        void getOneBookUsingTitle_withAWildCardAtTheStartOfTheTitle(){
            BookDTO[] result =
                    RestAssured
                            .given()
                            .when()
                            .port(port)
                            .get("/books?title=*isgood")
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.OK.value())
                            .extract()
                            .as(BookDTO[].class);

            assertThat(result)
                    .isNotNull()
                    .isNotEmpty();
            /*---Test a list compared to an object---*/
        }
        @Test
        void getOneBookUsingTitle_withAWildCardAtTheEndOfTheTitle(){
            BookDTO[] result =
                    RestAssured
                            .given()
                            .when()
                            .port(port)
                            .get("/books?title=testingis*")
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.OK.value())
                            .extract()
                            .as(BookDTO[].class);

            assertThat(result)
                    .isNotNull()
                    .isNotEmpty();
            /*---Test a list compared to an object---*/
        }
        @Test
        void getOneBookUsingTitle_withOnlyAWildCard(){
            BookDTO[] result =
                    RestAssured
                            .given()
                            .when()
                            .port(port)
                            .get("/books?title=*")
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.OK.value())
                            .extract()
                            .as(BookDTO[].class);

            assertThat(result)
                    .isNotNull()
                    .isNotEmpty();
            /*---Test a list compared to an object---*/
        }
    }


    @Nested
    class GetABookByAuthor{
        @Test
        void getOneBookUsingAuthor(){
            BookDTO[] result =
                    RestAssured
                            .given()
                            .when()
                            .port(port)
                            .get("/books?author=" + someBook.getAuthor())
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.OK.value())
                            .extract()
                            .as(BookDTO[].class);

            assertThat(result)
                    .isNotNull()
                    .isNotEmpty();
            /*---Test a list compared to an object---*/
        }
        @Test
        void getOneBookUsingAuthor_withAWildCardAtTheStartOfTheAuthor(){
            BookDTO[] result =
                    RestAssured
                            .given()
                            .when()
                            .port(port)
                            .get("/books?author=*Jean")
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.OK.value())
                            .extract()
                            .as(BookDTO[].class);

            assertThat(result)
                    .isNotNull()
                    .isNotEmpty();
            /*---Test a list compared to an object---*/
        }
        @Test
        void getOneBookUsingAuthor_withAWildCardAtTheEndOfTheAuthor(){
            BookDTO[] result =
                    RestAssured
                            .given()
                            .when()
                            .port(port)
                            .get("/books?author=Max*")
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.OK.value())
                            .extract()
                            .as(BookDTO[].class);

            assertThat(result)
                    .isNotNull()
                    .isNotEmpty();
            /*---Test a list compared to an object---*/
        }

        @Test
        void getOneBookUsingAuthor_withOnlyAWildCard(){
            BookDTO[] result =
                    RestAssured
                            .given()
                            .when()
                            .port(port)
                            .get("/books?author=*")
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.OK.value())
                            .extract()
                            .as(BookDTO[].class);

            assertThat(result)
                    .isNotNull()
                    .isNotEmpty();
            /*---Test a list compared to an object---*/
        }
    }


    @Nested
    class GetABookWithDetails{}


    @Nested
    class GetARentedBook{}


    @Nested
    class GetAllOverdueBooks{}


    @Nested
    class DeleteABook{}


    @Nested
    class UpdateABook{}


    @Nested
    class LendABook{}


    @Nested
    class ReturnABook{}

}
