//package com.firefox5.digibooky.user.controllertest;
//
//import com.firefox5.digibooky.api.user.AdminPostDTO;
//import com.firefox5.digibooky.api.user.UserDTO;
//import com.firefox5.digibooky.domain.user.Role;
//import com.firefox5.digibooky.domain.user.User;
//import com.firefox5.digibooky.domain.user.UserRepository;
//import com.firefox5.digibooky.domain.user.roles.Admin;
//import io.restassured.RestAssured;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//
//import static com.firefox5.digibooky.domain.user.Role.ADMIN;
//import static com.firefox5.digibooky.domain.user.Role.LIBRARIAN;
//import static io.restassured.http.ContentType.JSON;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DisplayName("UserController EndToEnd Testing")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//public class UserControllerTest {
//
//    private User testingMember;
//    private User testingLibrarian;
//    private User testingAdmin;
//    @Autowired
//    private UserRepository userRepository;
//    @Value("8085")
//    private int port;
//    @BeforeEach
//    void setup(){
//        testingMember = userRepository.getUserByEmail("member@gmail.com");
//        testingLibrarian = userRepository.getUserByEmail("librarian@gmail.com");
//        testingAdmin = userRepository.getUserByEmail("admin@gmail.com");
//    }

//    @Nested
//    class getAllUsers{
//        @Test
//        void getAllUsers_AsAdmin(){
//            User user = testingAdmin;
//
//            UserDTO[] result =
//                    RestAssured
//                            .given()
//                            .auth()
//                            .preemptive()
//                            .basic("admin@gmail.com", "123")
//                            .body(testingAdmin)
//                            .accept(JSON)
//                            .contentType(JSON)
//                            .when()
//                            .port(port)
//                            .get("/users")
//                            .then()
//                            .extract()
//                            .as(UserDTO[].class);
//
//            assertThat(result)
//                    .isNotNull()
//                    .isNotEmpty();
//
//        }
//        @Test
//        void getAllUsers_AsMember_shouldFail(){
//            User user = testingMember;
//
//            UserDTO[] result =
//                    RestAssured
//                            .given()
//                            .auth()
//                            .preemptive()
//                            .basic("member@gmail.com", "123")
//                            .body(testingMember)
//                            .accept(JSON)
//                            .contentType(JSON)
//                            .when()
//                            .port(port)
//                            .get("/users")
//                            .then()
//                            .extract()
//                            .as(UserDTO[].class);
//
//            assertThat(result)
//                    .isNotNull()
//                    .isNotEmpty();
//
//        }
//    }
//
//    @Nested
//    class test {
//        @Test
//        void createAdmin() {
//            User user = testingAdmin;
//
//            AdminPostDTO adminPostDTO =
//                    (AdminPostDTO) RestAssured
//                            .given()
//                            .auth()
//                            .preemptive()
//                            .basic("admin@gmail.com", "123")
//                            .body(testingAdmin)
//                            .accept(JSON)
//                            .contentType(JSON)
//                            .when()
//                            .port(port)
//                            .get("/users")
//                            .then()
//                            .assertThat()
//                            .statusCode(HttpStatus.CREATED.value())
//                            .extract();
//
//            assertThat(adminPostDTO)
//                    .isNotNull()
//                    .isEqualTo(ADMIN);
//            assertThat(userRepository.getUserByEmail("admin@gmail.com").getRole()).isEqualTo(testingAdmin);
//        }
//        @Test
//        void createLibrarian() {
//            User user = testingAdmin;
//
//            AdminPostDTO adminPostDTO =
//                    (AdminPostDTO) RestAssured
//                            .given()
//                            .auth()
//                            .preemptive()
//                            .basic("admin@gmail.com", "123")
//                            .body(testingAdmin)
//                            .accept(JSON)
//                            .contentType(JSON)
//                            .when()
//                            .port(port)
//                            .get("/users")
//                            .then()
//                            .extract();
//
//            assertThat(adminPostDTO)
//                    .isNotNull()
//                    .isEqualTo(LIBRARIAN);
//        }
//    }
//}
