package com.thoughtworks.felix.interfaces.rest;

import com.thoughtworks.felix.domain.user.User;
import com.thoughtworks.felix.domain.user.UserService;
import com.thoughtworks.felix.interfaces.validation.IdRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.thoughtworks.felix.application.service.BindingResultResolver.parseErrors;
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
        if (result.hasErrors()) {
            throw new BadRequestException(parseErrors(result));
        }
        User saved = userService.save(user);
        Link link = linkTo(methodOn(UsersApi.class).addUser(user, result)).withSelfRel();
        return new Resource<>(saved, link);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getUser(@PathVariable @IdRange Integer id) {
        return "cool user";
    }
}
