package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WasdappController {
    
    @Autowired
    WasdappServiceImpl wasdappService;
    
    @Autowired
    WasdappEntryRepository repo;
    
    @RequestMapping(value="/Wasdapp", method=RequestMethod.GET)
    public String List(Model model){
            model.addAttribute("entries", wasdappService.findAllExisting());
            return "WasDapp.xhtml";
        }
    
        
    }
    

