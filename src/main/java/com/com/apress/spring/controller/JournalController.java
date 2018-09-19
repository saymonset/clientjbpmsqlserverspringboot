package com.com.apress.spring.controller;
import com.com.apress.spring.repository.JournalRepository;
import      org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import       org.springframework.ui.Model;
import      org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by simon on 19/09/18.
 */
@Controller
public class JournalController {
    @Autowired
    private JournalRepository repo;
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("journal", repo.findAll());
        return "index";
    }
}
