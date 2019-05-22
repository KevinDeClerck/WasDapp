package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.google.firebase.auth.FirebaseAuthException;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.UserRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.FireBaseService;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddUserController {

    @Autowired
    CurrentUser currentUser;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepo;
    
    @Autowired
    FireBaseService fireBaseService;

    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String createForm(Model model) throws FirebaseAuthException {
        if (currentUser.getCurrentUser() != null) {
            if (currentUser.getCurrentUser().getRole().equals("admin")) {
                List<UserWassdapp> list = fireBaseService.findAllUsers();
                model.addAttribute("entries", list);
                model.addAttribute(currentUser);
                return "createUser.xhtml";
            } else {
                return "redirect:/wasdapp";
            }
        } else {
            return "redirect:/wasdapp";
        }

    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String entrySubmit(
            @RequestParam String name, @RequestParam String email,
            @RequestParam String password, @RequestParam String achternaam,
            Model model) throws FirebaseAuthException {
        String displayName = name + " " + achternaam;
        fireBaseService.createUser(email, password, displayName);
        UserWassdapp entry = new UserWassdapp();
        Long id = 0L;
        entry.setId(id);
        entry.setName(name);
        entry.setEmail(email);
        entry.setPassword(password);
        entry.setAchterNaam(achternaam);
        entry.setRole("admin");
        userService.save(entry);
        model.addAttribute("entry", entry);
        return "redirect:/createUser";
    }
}
