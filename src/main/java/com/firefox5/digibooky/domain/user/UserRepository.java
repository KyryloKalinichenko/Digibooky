package com.firefox5.digibooky.domain.user;

import com.firefox5.digibooky.api.user.UserPostDTO;
import com.firefox5.digibooky.security.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private List<User> listOfUsers = new ArrayList<>
            (List.of(new User("0104199901234", "Jon", "Weak", "killyou@gmail.com",
                            new Address("Stationstraat", 12, "1800", "Brussels"), "123"),
                    new User("0104199901235", "Bob", "Weak", "hotdog@gmail.com",
                            new Address("Stationstraat", 7, "1800", "Brussels"), "123")
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
                .orElseThrow(()  -> new UserNotFoundException("Unknown username. Please try again"));
    }

    public boolean isUnique(UserPostDTO user) {
        return listOfUsers.stream()
                .filter(x -> x.getInss() == user.getInss())
                .findFirst()
                .isPresent();
    }


}
