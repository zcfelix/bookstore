package com.thoughtworks.felix.resource;

import com.thoughtworks.felix.domain.User;
import com.thoughtworks.felix.exception.RequestInvalidException;
import com.thoughtworks.felix.service.UserService;
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
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Resource addUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors())
            throw new RequestInvalidException(result);
        User saved = userService.save(user);
        Link link = linkTo(methodOn(UserResource.class).addUser(user, result)).withSelfRel();
        return new Resource<>(saved, link);
    }
}
