package com.skills.indicator.Controllers;


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


    @PostMapping("/saveData")
    public String postActivity(ActivityData data, Principal principal){
        System.out.println("Sport: " + data.getSport());
        System.out.println("Reading: " + data.getReading());
        System.out.println("English: " + data.getEnglish());
        System.out.println("Education: " + data.getEducation());
        System.out.println("Others: " + data.getOthers());

        String username = principal.getName();

        activityService.saveOrUpdate(username, data);

        return "success";
    }

}
