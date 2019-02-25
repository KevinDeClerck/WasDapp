package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UpdateEntryController {

    @Autowired
    WasdappServiceImpl wasdappService;

    @Autowired
    WasdappEntryRepository repo;

    @RequestMapping(value = "/edit")
    public String updateForm(@RequestParam(name = "id") Long id, Model model) {
        WasdappEntryResponse entry = wasdappService.findById(id);
        model.addAttribute("entry", entry);
        return "edit.xhtml";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String entrySubmit(
            @RequestParam Long id, @RequestParam String name,
            @RequestParam String locatie,
            @RequestParam String straat, @RequestParam String nummer,
            @RequestParam String postCode, @RequestParam String gemeente,
            @RequestParam String land, @RequestParam String omschrijving,
            @RequestParam String telefoon, @RequestParam String email,
            @RequestParam Double latitude, @RequestParam Double longitude,
            Model model) {
        WasdappEntryResponse entry = new WasdappEntryResponse();
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
       @GetMapping("editinternational")
    public String getInternationalPage(Model model) {
        model.addAttribute("entries", wasdappService.findAll());
        return "edit.xhtml";
    }
}
