package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteEntryController {

    @Autowired
    CurrentUser currentUser;

    @Autowired
    WasdappServiceImpl wasdappService;

    @RequestMapping(value = "/deleteItems", method = RequestMethod.GET)
    public String handleDeleteItems(@RequestParam("id") List<Long> ids, Model model) {
        if (currentUser.getCurrentUser() != null) {
            if (currentUser.getCurrentUser().getRole().equals("admin")) {
                for (Long id : ids) {
                    if (id == -1) {
                        return "redirect:/wasdapp";
                    } else if (id != 0) {
                        wasdappService.deleteById(id);
                    } else {
                        wasdappService.deleteAll();
                    }
                }
            } else {
                return "redirect:/wasdapp";
            }
        } else {
            return "redirect:/wasdapp";
        }

        return "redirect:/wasdapp";
    }
}
