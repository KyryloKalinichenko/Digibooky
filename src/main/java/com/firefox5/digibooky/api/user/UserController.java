package com.firefox5.digibooky.api.user;

import com.firefox5.digibooky.domain.user.Address;
import com.firefox5.digibooky.domain.user.User;
import com.firefox5.digibooky.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("")
    public List<UserDTO> getAllUsers(){
        return userService.getALlUsers();
    }


    @PostMapping(value = "register" , produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserPostDTO userPostDTO){
        return userService.createUser(userPostDTO);
    }
    
}
