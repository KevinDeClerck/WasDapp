
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
    
    @RequestMapping(value="/Wasdapp", method=RequestMethod.GET)
    public String List(Model model){
            model.addAttribute("entries", wasdappService.findAllExisting());
            return "WasDapp.xhtml";
        }
       @RequestMapping(value="/Add", method=RequestMethod.GET)
    public String List2(Model model){
        return "WasDapp.xhtml";
    }
    
    @RequestMapping(value = "/delete_item", method = RequestMethod.GET)
    public String handleDeleteItem(@RequestParam(name = "itemId") Long itemId, Model model) {
        repo.deleteById(itemId);
        return "redirect:/Wasdapp";
    }

    @RequestMapping(value = "/deleteItems", method = RequestMethod.GET)
    public String handleDeleteItems(@RequestParam("id") List<Long> ids, Model model) {
        for(Long id: ids){
            if(id == -1){
                return "redirect:/Wasdapp"; 
            }else if(id != 0){  
                repo.deleteById(id);
            }else
                repo.deleteAll();
        }
        return "redirect:/Wasdapp";
    }

    @RequestMapping("/upload")
    public String List3(Model model){
        return "upload.xhtml";
    }
}
    

