package com.example.springauthorizationserver.controllers.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/authentication")
public class SecurityController {

    @GetMapping("/login")
    public String loginPage() {
        return "html/login.html";
    }

}
