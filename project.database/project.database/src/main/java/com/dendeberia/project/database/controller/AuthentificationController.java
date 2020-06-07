package com.dendeberia.project.database.controller;

import com.dendeberia.project.database.entity.User;
import com.dendeberia.project.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthentificationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistrationView(Model model){
        model.addAttribute("userForm", new User());
        return "html/registration";
    }

    @PostMapping("/registration")
    public String createAccount(@ModelAttribute("userForm") @Valid User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "html/registration";
        }

        if(!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("passwordError", "Password mismatch");
            return "html/registration";
        }

        if(!userService.saveUser(user)){
            model.addAttribute("usernameError", "User already exists");
            return "html/registration";
        }

        return "html/login";
    }

    @GetMapping("/")
    public String getDefaultMain(){
        return "html/default";
    }

    @GetMapping("/login")
    public String getLoginView(){
        return "html/login";
    }
}
