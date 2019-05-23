package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.FireBaseService;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WasdappController {

    @Autowired
    CurrentUser currentUser;

    @Autowired
    WasdappService wasdappService;

    @Autowired
    FireBaseService fsService;

    @RequestMapping(value = "/wasdapp", method = RequestMethod.GET)
    public String showList(Model model) throws InterruptedException, ExecutionException {
        if (currentUser.getCurrentUser() != null) {
            List<WasdappEntryResponse> allEntries = fsService.findAll();
            sortList(allEntries);
            model.addAttribute("entries", allEntries);
            model.addAttribute(currentUser);
            String nothing = "";
            model.addAttribute("nothing", nothing);
            return "wasdapp.xhtml";
        } else {
            return "redirect:/login";
        }
    }

    public List<WasdappEntryResponse> sortList(List<WasdappEntryResponse> list) {
        Collections.sort(list, (WasdappEntryResponse t1, WasdappEntryResponse t2) -> ComparisonChain.start()
                .compare(t1.getId(), t2.getId(), Ordering.natural().nullsLast())
                .result());
        return list;
    }
}
