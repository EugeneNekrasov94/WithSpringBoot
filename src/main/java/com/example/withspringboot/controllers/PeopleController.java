package com.example.withspringboot.controllers;

import com.example.withspringboot.model.User;
import com.example.withspringboot.service.UserService;
import com.example.withspringboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PeopleController {
    private UserService userService;

    @Autowired
    public void setUserService(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping(value = "/")
    public String listUsers(ModelMap model) {
        model.addAttribute("users", this.userService.findAll());
        this.userService.findAll().forEach(System.out::println);
        return "users";
    }

    @GetMapping("user-create")
    public String createUserForm(User user, ModelMap modelMap) {
        modelMap.addAttribute("user",user);
        return "/user-create";
    }

    @PostMapping("user-create")
    public String createUser(User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

    @GetMapping("user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model modelMap) {
        User user = userService.findUserById(id);
        modelMap.addAttribute("user", user);
        return "/user-update";
    }

    @PostMapping("user-update")
    public String updateUser(User user) {
        userService.updateUser(user.getId(), user);
        return "redirect:/";
    }

}
