package com.revature.mathtagon.user;


import com.revature.mathtagon.user.dtos.requests.NewUserRequest;
import com.revature.mathtagon.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Inject
    private final UserService userService;

    @Inject
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public @ResponseBody
    List<Users> getAllUser(){
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String register(@RequestBody NewUserRequest request){
        return userService.registerUser(request).getUserID();
    }


}
