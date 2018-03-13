package com.cgi.financeplanner.controller;

import com.cgi.financeplanner.models.ViewModel;
import com.cgi.financeplanner.services.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
//        ViewModel viewmodel = new ViewModel();
//        model.addAttribute("daten", viewmodel);
        return "homeview";
    }


    @PostMapping("/sendFormData")
    public String sendFormData(@ModelAttribute ViewModel daten, Model model){
        db_service.insert(daten);
        return "homeview";
    }

    @GetMapping("/connect")
    public String connect(Model model){
        logger.info("Die Controllermethode connect wurde aufgerufen.");
        String result = "";
        model.addAttribute("daten", new ViewModel());
        List<String> liste = db_service.readDatabases();
        model.addAttribute("databases",liste );
        logger.info("Die Datenbankabfrage hat {} Datenbanken gefunden", liste);
        return "homeview";
    }

    @GetMapping("/listTransactions")
    public String listTransactions(Model model){

       model.addAttribute("transactions", db_service.ReturnAllTransactions());
        return "homeview";
    }

    @GetMapping("/disconnect")
    public String disconnect(Model model){
        db_service.disconnect();
        return "homeview";
    }
}
