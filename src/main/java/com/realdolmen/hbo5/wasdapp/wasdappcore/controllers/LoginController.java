/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    
    @Autowired
    private CurrentUser currentUser;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String login(Model model) {
        return "login.xhtml";
    }
    
    @GetMapping("/")
    public String login2(Model model) {
        return "login.xhtml";
    }
    
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String validateUser(Model model, @RequestParam("username")String username, @RequestParam("password")String password) {
        String msg = "";
        boolean isValid = userService.validUser(username, password); 
        if(isValid) {
            currentUser.setCurrentUser(userService.getUser(username));
            return "redirect:/wasdapp";
        } else {
            msg = "Invalid credentials";
        }
        model.addAttribute("msg", msg);
        return "redirect:/login";
    }
    
    @GetMapping("/logout")
    public String logout(Model model){
        currentUser.setCurrentUser(null);
        return "redirect:/login";
    }
}

