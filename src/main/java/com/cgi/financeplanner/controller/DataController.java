package com.cgi.financeplanner.controller;

import com.cgi.financeplanner.models.Transaction;
import com.cgi.financeplanner.services.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DataController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DatabaseService db_service;

    @GetMapping("/listTransactions")
    public String listTransactions(Model model) {

        model.addAttribute("transactions", db_service.ReturnAllTransactions());
        model.addAttribute("transaction", new Transaction());
        return "homeview";
    }

    @PostMapping("/sendFormData")
    public String sendFormData(@ModelAttribute Transaction transaction, Model model) {

        logger.info("Eine transaction wurde gepostet {}", transaction.getSubject());
        db_service.insert(transaction);
        return "homeview";
    }
}
