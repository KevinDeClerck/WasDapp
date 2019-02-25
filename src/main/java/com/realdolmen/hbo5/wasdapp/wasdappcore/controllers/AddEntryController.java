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
public class AddEntryController {

    @Autowired
    WasdappServiceImpl wasdappService;

    @Autowired
    WasdappEntryRepository repo;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createForm(Model model) {
        return "add.xhtml";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String entrySubmit(
            @RequestParam String name, @RequestParam String locatie,
            @RequestParam String straat, @RequestParam String nummer,
            @RequestParam String postCode, @RequestParam String gemeente,
            @RequestParam String land, @RequestParam String omschrijving,
            @RequestParam String telefoon, @RequestParam String email,
            @RequestParam Double latitude, @RequestParam Double longitude,
            Model model) {
        WasdappEntryResponse entry = new WasdappEntryResponse();
        Long id = 0L;
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
        entry.setLat(latitude);
        entry.setLon(longitude);
        wasdappService.update(entry);

        model.addAttribute("entry", entry);

        return "redirect:/wasdapp";
    }
    @GetMapping("addinternational")
    public String getInternationalPage(Model model) {
        model.addAttribute("entries", wasdappService.findAll());
        return "add.xhtml";
    }
}
