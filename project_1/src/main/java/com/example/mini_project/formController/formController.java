package com.example.mini_project.formController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class formController {

    @GetMapping("login-form")
    public String loginForm() {
        return "login-form";
    }

    @GetMapping("register-form")
    public String registerForm() {
        return "register-form";
    }
}
