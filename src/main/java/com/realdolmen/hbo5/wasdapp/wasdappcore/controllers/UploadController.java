package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CsvParser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.CsvParserImpl;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    @Autowired
    WasdappServiceImpl wasdappService;

    @Autowired
    WasdappEntryRepository repo;
    
    @Autowired
    CsvParserImpl csvParser;
       
    @RequestMapping("/upload")
    public String showUpload(Model model) {
        return "upload.xhtml";
    }
       @GetMapping("uploadinternational")
    public String getInternationalPage(Model model) {
        model.addAttribute("entries", wasdappService.findAll());
        return "upload.xhtml";
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam MultipartFile file, Model model) throws IOException{
        String path = file.getOriginalFilename();
        try{
        csvParser.importCsv(path);
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return "redirect:/wasdapp";
    }
    
    
}
