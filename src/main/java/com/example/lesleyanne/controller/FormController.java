package com.example.lesleyanne.controller;

import com.example.lesleyanne.model.UserForm;
import com.example.lesleyanne.repository.UserFormRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FormController {

    @Autowired
    private UserFormRepository userFormRepository;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "form";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // renders login.html
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @PostMapping("/submit")
    public String submitForm(@Valid @ModelAttribute UserForm userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "form";
        }
        userFormRepository.save(userForm);
        model.addAttribute("message", "Form submitted successfully!");
        return "form";
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam(name = "query", required = false) String query, Model model) {
        List<UserForm> results;
        if (query != null && !query.isBlank()) {
            results = userFormRepository.findByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(query, query);
        } else {
            results = userFormRepository.findAll();
        }
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "search";
    }
}