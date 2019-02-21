package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UpdateEntryController {

    @Autowired
    WasdappServiceImpl wasdappService;

    @Autowired
    WasdappEntryRepository repo;


    @RequestMapping(value = "/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("entry", wasdappService.findById(id));
        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String entrySubmit(
            @RequestParam Long id, @RequestParam String telefoon,
            @RequestParam String name, @RequestParam String locatie,
            @RequestParam String straat, @RequestParam String nummer,
            @RequestParam String postCode, @RequestParam String gemeente,
            @RequestParam String land, @RequestParam String omschrijving, 
            @RequestParam String email, Model model) {
        WasdappEntryResponse entry = wasdappService.findById(id);
        entry.setId(id);
        entry.setName(name);
        entry.setLocatie(locatie);
        entry.setStraat(straat);
        entry.setNummer(nummer);
        entry.setPostCode(postCode);
        entry.setGemeente(gemeente);
        entry.setLand(land);
        entry.setOmschrijving(omschrijving);
        entry.setTelefoonNummer(telefoon);
        entry.setEmail(email);
        wasdappService.update(entry);

        model.addAttribute("entry", entry);

        return "redirect:/wasdapp";
    }
}

