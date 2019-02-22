
package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WasdappController {

    @Autowired
    WasdappServiceImpl wasdappService;

    @Autowired
    WasdappEntryRepository repo;

    @RequestMapping(value = "/wasdapp", method = RequestMethod.GET)
    public String showList(Model model) {
        model.addAttribute("entries", wasdappService.findAll());
        return "wasdapp.xhtml";
    }
    @GetMapping("wasdappinternational")
    public String getInternationalPage(Model model) {
        model.addAttribute("entries", wasdappService.findAll());
        return "wasdapp.xhtml";
    }
}
