package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UploadController {

    @Autowired
    WasdappServiceImpl wasdappService;

    @Autowired
    WasdappEntryRepository repo;

        
    @RequestMapping("/upload")
    public String showUpload(Model model) {
        return "upload.xhtml";
    }
       @GetMapping("uploadinternational")
    public String getInternationalPage(Model model) {
        model.addAttribute("entries", wasdappService.findAll());
        return "upload.xhtml";
    }
    
    
}
