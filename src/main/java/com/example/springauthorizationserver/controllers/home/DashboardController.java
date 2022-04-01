package com.example.springauthorizationserver.controllers.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/home")
public class DashboardController {

    @RequestMapping("/dashboard")
    public String dashboard() {
        return "/html/dashboard.html";
    }


}
