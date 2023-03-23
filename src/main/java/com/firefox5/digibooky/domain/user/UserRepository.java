package com.firefox5.digibooky.domain.user;

import com.firefox5.digibooky.api.user.AdminPostDTO;
import com.firefox5.digibooky.domain.user.roles.Admin;
import com.firefox5.digibooky.domain.user.roles.Member;
import com.firefox5.digibooky.service.security.exceptions.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> listOfUsers = new ArrayList<>
            (List.of(
                    new Member("0104199901234", "Jon", "Weak", "killyou@gmail.com",
                            new Address("Stationstraat", 12, "1800", "Brussels"), "123"),
                    new Admin("0104199901235", "Bob", "Weak", "hotdog@gmail.com",
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
    public User getUserById (int userId) throws RuntimeException {
        return listOfUsers.stream()
                .filter(user -> user.getUserId() == (userId))
                .findFirst()
                .orElseThrow(()-> new UserNotFoundException("Unknown userID. Please try again."));
    }
    public boolean isUserExist(AdminPostDTO user) {
        return listOfUsers.stream()
                .filter(x -> x.getInss() == user.getInss())
                .findFirst()
                .isPresent();
    }


}
