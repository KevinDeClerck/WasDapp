package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CsvParser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.CsvParserImpl;
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
    WasdappEntryRepository repo;

    @Autowired
    CsvParserImpl csvParser;

    @RequestMapping("/upload")
    public String showUpload(Model model) {
        return "upload.xhtml";
    }

    @RequestMapping("/uploadError")
    public String uploadError(Model model) {
        String string = "Please upload a valid file.";
        model.addAttribute("string", string);
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
        return "upload.xhtml";
    }

    @GetMapping("uploadinternational")
    public String getInternationalPage(Model model) {
        model.addAttribute("entries", wasdappService.findAll());
        return "upload.xhtml";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam MultipartFile file, Model model) throws IOException {
        String path = file.getOriginalFilename();
        //String fileInput = IOUtils.toString(file.getInputStream(),  StandardCharsets.UTF_8);
        InputStream is = file.getInputStream();
        if (path.endsWith(".csv")) {
            try {
                csvParser.importCsv(is);
            } catch (Exception e) {
                return "redirect:/uploadErrorWrongCSV";
            }
            return "redirect:/wasdapp";
        } else if (path.endsWith(".json")) {
            return "redirect:/uploadError";
        }
        return "redirect:/uploadError";
    }
}
