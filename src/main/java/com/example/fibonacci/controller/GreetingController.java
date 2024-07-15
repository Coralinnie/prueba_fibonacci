package com.example.fibonacci.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    @ResponseBody
    public String greeting( @RequestParam(name = "dato1", required = false) String dato1,
            @RequestParam(name = "dato2", required = false) String dato2) {

        return "greeting" + " " +dato1 +" "+ dato2;
    }
}
