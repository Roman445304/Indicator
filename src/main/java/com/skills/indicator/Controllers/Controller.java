package com.skills.indicator.Controllers;


import com.skills.indicator.Service.QuoteService;
import org.springframework.ui.Model;
import com.skills.indicator.Dto.ActivityData;
import com.skills.indicator.Service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor

public class Controller {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String getDashboard(){
        return "dashboard";
    }


    @GetMapping("/input")
    public String getInput(){
        return "input";
    }

    @GetMapping("/getStats")
    public String getStats (Principal principal, Model model) {
        String username = principal.getName();
        int totalActivity = activityService.sumActivityByUsername(username);
        model.addAttribute("totalActivity", totalActivity);
        model.addAttribute("username", username);
        return "stats";
    }


    @PostMapping("/saveData")
    public String postActivity(
            @RequestParam(required = false) Integer sport,
            @RequestParam(required = false) Integer education,
            @RequestParam(required = false) Integer others,
            @RequestParam(required = false) Integer english,
            @RequestParam(required = false) Integer reading,
            @RequestParam(required = false) Integer writeOff
            , Principal principal, Model model){

        String username = principal.getName();
        ActivityData data = new ActivityData(sport, education, others, english, reading, writeOff);
        activityService.saveOrUpdate(username, data);
        quoteService.getQuote(model);
        return "success";
    }
}
