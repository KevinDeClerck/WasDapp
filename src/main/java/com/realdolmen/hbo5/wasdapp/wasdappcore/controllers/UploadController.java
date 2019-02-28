package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CsvParser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.CsvParserImpl;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.JsonParserImpl;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
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
    CurrentUser currentUser;

    @Autowired
    CsvParserImpl csvParser;
    
    @Autowired
    JsonParserImpl jsonParser;

    @RequestMapping("/upload")
    public String showUpload(Model model) {
          if (currentUser.getCurrentUser() != null) {
            if (currentUser.getCurrentUser().getRole().equals("admin")) {
                model.addAttribute(currentUser);
                return "upload.xhtml";
            } else {
                return "redirect:/wasdapp";
            }
        } else {
            return "redirect:/login";
        }
    }
    
    @RequestMapping("/uploadError")
    public String uploadError(Model model) {
        String string = "Please upload a CSV or JSON file.";
        model.addAttribute("string", string);
        model.addAttribute(currentUser);
        return "upload.xhtml";
    }
    
    @RequestMapping("/uploadErrorWrongJSON")
    public String uploadErrorWrongJSON(Model model) {
        String image = "https://i.imgur.com/iKN0rGa.png";
        model.addAttribute("image", image);
        model.addAttribute(currentUser);
        return "upload.xhtml";
    }
    
    @RequestMapping("/uploadErrorWrongCSV")
    public String uploadErrorWrongCSV(Model model) {
        String validFile = "Please make sure your CSV file is valid:";
        String comma = "Seperate data fields with a comma.";
        String line = "Keep each record on a seperate line.";
        String carriage = "Do not follow the last record in a file with a carriage return.";
        String row = "Each row needs an equal amount of colums.";
        String title = "Make sure there is a title.";
        String emptyLines = "Make sure there are no empty lines in the file.";
        model.addAttribute("validFile", validFile);
        model.addAttribute("comma", comma);
        model.addAttribute("line", line);
        model.addAttribute("carriage", carriage);
        model.addAttribute("row", row);
        model.addAttribute("title", title);
        model.addAttribute("emptyLines", emptyLines);
        model.addAttribute(currentUser);
        return "upload.xhtml";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam MultipartFile file, Model model) throws IOException {
        String path = file.getOriginalFilename();
        InputStream is = file.getInputStream();
        if (path.endsWith(".csv")) {
            try {
                csvParser.importCsv(is);
                return "redirect:/wasdapp";
            } catch (Exception e) {
                return "redirect:/uploadErrorWrongCSV";
            } 
        } else if (path.endsWith(".json")) {
            try{
                jsonParser.importJson(is);
                return "redirect:/wasdapp";  
            }catch(Exception e){
            return "redirect:/uploadErrorWrongJSON";
            }
        }
        return "redirect:/uploadError";
    }
}
