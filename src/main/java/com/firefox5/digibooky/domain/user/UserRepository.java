package com.firefox5.digibooky.domain.user;

import org.springframework.stereotype.Repository;

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
}
