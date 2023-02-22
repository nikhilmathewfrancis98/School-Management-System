package com.kenschool.Controller;

import com.kenschool.Model_POJOs.PersonPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Slf4j
@Controller
@RequestMapping("student")
public class StudentEnrolledController {
    @GetMapping("/displayCourses")
    public ModelAndView StudentEnrolledCourses(HttpSession httpSession){
        ModelAndView modelAndView=new ModelAndView();
        PersonPojo person=(PersonPojo) httpSession.getAttribute("loggedInPerson");
        modelAndView.addObject("person",person);
        modelAndView.addObject("personCourses",person.getCourses());
        modelAndView.setViewName("courses_enrolled.html");

        return modelAndView;
    }
}
