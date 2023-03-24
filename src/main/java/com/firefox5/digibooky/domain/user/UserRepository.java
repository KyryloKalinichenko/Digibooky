package com.firefox5.digibooky.domain.user;

import com.firefox5.digibooky.api.user.AdminPostDTO;
import com.firefox5.digibooky.domain.user.roles.Admin;
import com.firefox5.digibooky.domain.user.roles.Librarian;
import com.firefox5.digibooky.domain.user.roles.Member;
import com.firefox5.digibooky.service.security.exceptions.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> listOfUsers = new ArrayList<>
            (List.of(
                    new Member("96072911112", "Jon", "Weak", "member@gmail.com",
                            new Address("Stationstraat", 12, "1800", "Brussels"), "123"),
                    new Admin("96072911223", "Bob", "Weak", "admin@gmail.com",
                            new Address("Stationstraat", 7, "1800", "Brussels"), "123"),
                    new Librarian("96072915963", "Martha", "Weak", "librarian@gmail.com",
                            new Address("Stationstraat", 5, "1800", "Brussels"), "123")
            ));

    public List<User> getAllUsers() {
        return listOfUsers;
    }

    public User addUser(User user) {
        listOfUsers.add(user);
        return user;
    }

    public User getUserByEmail(String emailAddress) throws RuntimeException {
        return listOfUsers.stream()
                .filter(user -> user.getEmailAddress().equals(emailAddress))
                .findFirst()
                .orElseThrow(()  -> new UserNotFoundException("Unknown user email. Please try again"));
    }
    public User getUserById (int userId) throws RuntimeException {
        return listOfUsers.stream()
                .filter(user -> user.getUserId() == (userId))
                .findFirst()
                .orElseThrow(()-> new UserNotFoundException("Unknown userID. Please try again."));
    }
    public User getUserByRole(Role role) throws RuntimeException {
        return listOfUsers.stream()
                .filter(user -> user.getRole() == (role))
                .findFirst()
                .orElseThrow(()-> new UserNotFoundException("Unknown role. Please try again."));
    }
    public boolean isUserExist(AdminPostDTO user) {
        return listOfUsers.stream()
                .anyMatch(x -> x.getInss().equals(user.getInss()));
    }
}
