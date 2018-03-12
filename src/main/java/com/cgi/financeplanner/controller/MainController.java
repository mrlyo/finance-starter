package com.cgi.financeplanner.controller;

import com.cgi.financeplanner.models.ViewModel;
import com.cgi.financeplanner.services.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DatabaseService db_service;

    @GetMapping("/")
    public String redirecthome(){
        return "redirect:/start";
    }

    @GetMapping("/start")
    public String home(Model model){

        logger.info("home methode wurde aufgerufen.");

        ViewModel viewmodel = new ViewModel();
        model.addAttribute("daten", viewmodel);
        return "homeview";
    }


    @PostMapping("/sendFormData")
    public String sendFormData(@ModelAttribute ViewModel dataModel, Model model){


        return "homeview";
    }

    @GetMapping("/connect")
    public String connect(Model model){

    logger.info("Die Controllermethode connect wurde aufgerufen.");
        String result = "";

        result =  db_service.connect();
        logger.info("Das Ergebnis der Verbindung war {} und wurde dem COntroller zurückgeben", result);
        model.addAttribute("result", result);
        return "result";
    }
}
