package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WasdappController {

    @Autowired
    CurrentUser currentUser;

    @Autowired
    WasdappServiceImpl wasdappService;


    @RequestMapping(value = "/wasdapp", method = RequestMethod.GET)
    public String showList(Model model) {
        model.addAttribute("entries", wasdappService.findAll());
        if (currentUser.getCurrentUser() != null) {
            return "wasdapp.xhtml";
        } else {
            return "redirect:/login";
        }
    }

}
