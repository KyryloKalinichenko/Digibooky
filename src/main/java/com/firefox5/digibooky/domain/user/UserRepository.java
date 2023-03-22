package com.firefox5.digibooky.domain.user;

import com.firefox5.digibooky.api.user.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> listOfUsers = new ArrayList<>
            (List.of(new User("0104199901234", "Jon", "Weak", "killyou@gmail.com",
                            new Address("Stationstraat", 12, "1800", "Brussels")),
                    new User("0104199901235", "Bob", "Weak", "hotdog@gmail.com",
                            new Address("Stationstraat", 7, "1800", "Brussels"))
            ));

    public List<User> getAllUsers(){
        return listOfUsers;
    }

    public User addUser(User user) {
        listOfUsers.add(user);
        return user;
    }
    public User findUserByEmail(String emailAddress) throws RuntimeException{
        return listOfUsers.stream()
                .filter(user -> user.getEmailAddress().equals(emailAddress))
                .findFirst()
                .orElseThrow();
    }


}
