package com.kenschool.Controller;

import com.kenschool.Model_POJOs.PersonPojo;
import com.kenschool.Repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Slf4j
@Controller
public class DashboardController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET, RequestMethod.POST})
    public String ViewDashboard(Authentication authentication, Model model, HttpSession httpSession) {
        PersonPojo personDetails = personRepository.getByemail(authentication.getName());
        model.addAttribute("username", personDetails.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        httpSession.setAttribute("loggedInPerson", personDetails);
        // Here am going to give a RunTime exception to show the customized error page
//      throw   new RuntimeException("Hey Something is wrong with this request"); // This is working
        return "dashboard.html";
    }
}
