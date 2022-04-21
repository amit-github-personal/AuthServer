package com.example.springauthorizationserver.controllers.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @RequestMapping("/")
    public String dashboard() {
        return "/html/dashboard.html";
    }


}
