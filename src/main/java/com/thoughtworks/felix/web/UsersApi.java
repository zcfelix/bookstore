package com.thoughtworks.felix.web;

import com.thoughtworks.felix.domain.model.User;
import com.thoughtworks.felix.exception.RequestInvalidException;
import com.thoughtworks.felix.domain.service.UserService;
import com.thoughtworks.felix.util.IdValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/users")
public class UsersApi {

    private final UserService userService;

    @Autowired
    public UsersApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Resource addUser(@Valid @RequestBody User user, BindingResult result) {
        System.out.println(user);
        if (result.hasErrors())
                throw new RequestInvalidException(result);
        User saved = userService.save(user);
        Link link = linkTo(methodOn(UsersApi.class).addUser(user, result)).withSelfRel();
        return new Resource<>(saved, link);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getUser(@PathVariable @IdValid Integer id) {
        return "cool user";
    }

//    @PatchMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public Resource updateUser(@Valid @RequestBody User user, BindingResult result) {
//
//    }
}