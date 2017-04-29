package com.thoughtworks.felix.resource;

import com.thoughtworks.felix.domain.User;
import com.thoughtworks.felix.exception.RequestInvalidException;
import com.thoughtworks.felix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HttpStatus addUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors())
            throw new RequestInvalidException(result);
        User saved = userService.save(user);
        return HttpStatus.CREATED;
    }
}
