package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyErrorController implements ErrorController {

    @Autowired
    CurrentUser currentUser;

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String showError(Model model) {
        if (currentUser.getCurrentUser() != null) {
            model.addAttribute(currentUser);
            return "error.xhtml";
        } else {
            return "redirect:/login";
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
