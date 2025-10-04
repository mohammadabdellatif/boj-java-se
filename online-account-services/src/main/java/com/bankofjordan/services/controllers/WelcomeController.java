package com.bankofjordan.services.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sample")
public class WelcomeController {

    @GetMapping(path = "/welcome_user", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView welcome(
            @RequestParam(value = "username") String name,
            @RequestParam(value = "age") int age) {
        name = name.toUpperCase();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");
        modelAndView.addObject("requestedName", name);
        modelAndView.addObject("requestedAge", age);

        return modelAndView;
    }
}
