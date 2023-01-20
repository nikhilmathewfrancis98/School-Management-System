package com.kenschool.Controller;

import com.kenschool.Model_POJOs.PersonPojo;
import com.kenschool.Repository.PersonRepository;
import com.kenschool.Services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("public")
public class NewUserRegistrationController {
    @Autowired(required = false)
    PersonService personService;
    @Autowired(required = false)
    PersonRepository personRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView RegisterUser(@RequestParam(name = "register", required = false) String val,
                                     @RequestParam(name = "registered", required = false) String alreadyRegistered
            , Model model) {
        model.addAttribute("person", new PersonPojo());
        if (val != null) {
            String Login_error_Messge = "Registration failed! Try again";
            model.addAttribute("Register_error", Login_error_Messge);
        } else if (alreadyRegistered != null) {
            String alrdyRegistered = "Email already exists";
            model.addAttribute("user_exists", alrdyRegistered);
        }
        return new ModelAndView(
                "Register.html");
    }

    //    @RequestMapping(value = "/createUser",method = RequestMethod.POST)
//    public ModelAndView SaveUser(@Valid ModelAttribute("person"),PersonPojo personpojo, Model model){
//
//        return "redirect:/Register.html";
//    }
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String SaveUser(@Valid @ModelAttribute("person") PersonPojo personPojo, Errors errors) {
        if (errors.hasErrors()) {
//            log.info("Contact form validation failed due to : " +errors.toString());
            return "Register.html";
//            return "redirect:/public/register?register=false";

        }

        var person = personRepository.getByemail(personPojo.getEmail());
        if (Objects.isNull(person)) {
            boolean isSaved = personService.CreateNewUser(personPojo);
            if (isSaved) {
                return "redirect:/login?register=true";
            } else {
                return "redirect:/public/register?register=false";
            }

        } else {
            return "redirect:/public/register?registered=false";
        }

    }
}
