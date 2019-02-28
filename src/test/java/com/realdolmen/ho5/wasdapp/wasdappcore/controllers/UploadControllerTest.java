/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.ho5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.controllers.UploadController;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.CsvParserImpl;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;

@RunWith(MockitoJUnitRunner.class)
@SpringBootApplication
public class UploadControllerTest {
    @Mock
    Model model;

    @Mock
    WasdappServiceImpl wasdappService;

    @Mock
    CurrentUser currentUser;
    
    @Mock
    CsvParserImpl csvParser;

    @InjectMocks
    UploadController uploadController;
}
