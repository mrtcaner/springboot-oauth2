package com.my.edu.oauth2.server.oauth2server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}