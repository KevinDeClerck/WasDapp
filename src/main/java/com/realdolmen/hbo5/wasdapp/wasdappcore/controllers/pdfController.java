
package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.GeneratePdfReport;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class pdfController {
    
    @Autowired
    WasdappServiceImpl wasdappService;

    @Autowired
    GeneratePdfReport pdf;
    
    
    public void test() throws IOException{
        pdf.generatePdf();
    }
    
    }

    
    

