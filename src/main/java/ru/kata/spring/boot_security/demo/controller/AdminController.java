package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin/users")
    public String getUsersAddForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("updatingUser", new User());
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("auth", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "/admin/users";
    }

    @PostMapping(value = "admin/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping(value = "admin/deleteUser/{id}")
    public String removeUserById(@PathVariable(value = "id") Long id) {
        userService.removeUserById(id);
        return "redirect:/admin/users";
    }

    @PutMapping(value = "/updateUser")
    public String updateUser(@ModelAttribute("updatingUser") User user) {
        userService.updateUser(user);
        return "redirect:/admin/users";
    }
}
