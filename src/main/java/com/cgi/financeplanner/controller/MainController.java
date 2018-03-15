package com.cgi.financeplanner.controller;

import com.cgi.financeplanner.models.Transaction;
import com.cgi.financeplanner.services.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String redirecthome() {
        return "redirect:/start";
    }

    @GetMapping("/start")
    public String home(Model model) {
        logger.info("home methode wurde aufgerufen.");
        model.addAttribute("transaction", new Transaction());
        return "homeview";
    }
}
