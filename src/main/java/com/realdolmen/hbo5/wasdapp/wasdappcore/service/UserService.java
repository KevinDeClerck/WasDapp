/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.hbo5.wasdapp.wasdappcore.service;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.UserRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.rest.WasdappEntryMapper;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
     public UserWassdapp save(UserWassdapp entry) {
        return userRepo.save(entry);
    }
    
         public List<UserWassdapp> findAll() {
        List<UserWassdapp> entries = userRepo.findAll();
        return entries;
    }
    
}
