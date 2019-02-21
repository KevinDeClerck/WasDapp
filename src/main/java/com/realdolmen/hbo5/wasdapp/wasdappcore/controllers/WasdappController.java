
package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WasdappController {

    @Autowired
    WasdappServiceImpl wasdappService;

    @Autowired
    WasdappEntryRepository repo;

    @RequestMapping(value = "/wasdapp", method = RequestMethod.GET)
    public String List(Model model) {
        model.addAttribute("entries", wasdappService.findAll());
        return "wasdapp.xhtml";
    }
    
    @RequestMapping(value = "/delete_item", method = RequestMethod.GET)
    public String handleDeleteItem(@RequestParam(name = "itemId") Long itemId, Model model) {
        repo.deleteById(itemId);
        return "redirect:/wasdapp";
    }

    @RequestMapping(value = "/deleteItems", method = RequestMethod.GET)
    public String handleDeleteItems(@RequestParam("id") List<Long> ids, Model model) {
        for(Long id: ids){
            if(id == -1){
                return "redirect:/wasdapp"; 
            }else if(id != 0){  
                repo.deleteById(id);
            }else
                repo.deleteAll();
        }
        return "redirect:/wasdapp";
    }

    @RequestMapping("/upload")
    public String List3(Model model) {
        return "upload.xhtml";
    }
}
