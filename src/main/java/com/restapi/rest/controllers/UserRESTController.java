package com.restapi.rest.controllers;

import com.restapi.rest.entities.User;
import com.restapi.rest.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController("/api/user")
@PreAuthorize("hasRole('USER')")
public class UserRESTController {
    private final UserService userService;

    @Autowired
    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> getUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findByEmail(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
