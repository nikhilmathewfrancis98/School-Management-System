package com.kenschool.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.exceptions.TemplateInputException;


@Controller
@RequestMapping(value = {"/", "home"}) // now we are going to access the page by this
public class HomeController {
    // Going to give multiple paths to the method
    // we can use this <mvc:view-controller path="/" view-name="home"/> in config.xml so that any request for / will be
    // redirected to home

    @GetMapping(value = {"/", "home"})
    public String displayHome() throws TemplateInputException { // If using ModelView use ModelView type in return
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("home.html");
//        model.addAttribute("username","Math Guru");
//        model.addAttribute("age",24);

        return "home.html";
    }

}
