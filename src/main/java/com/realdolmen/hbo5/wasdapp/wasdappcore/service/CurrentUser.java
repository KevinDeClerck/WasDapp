/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.hbo5.wasdapp.wasdappcore.service;


import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

/**
 *
 * @author Joren
 */
@Service
@SessionScope
public class CurrentUser {
    
    UserWassdapp currentUser;

    public CurrentUser() {
    }

    public UserWassdapp getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserWassdapp currentUser) {
        this.currentUser = currentUser;
    }
    
    
    
    
    
}
