package com.kenschool.Controller;

import com.kenschool.Model_POJOs.ClassPojo;
import com.kenschool.Model_POJOs.PersonPojo;
import com.kenschool.Repository.ClassRepository;
import com.kenschool.Repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
// Main controller class for the admin configurations
@RequestMapping("admin")
public class AdminController {
    @Autowired(required = false)
    ClassRepository classRepository;

    @Autowired(required = false)
    PersonRepository personRepository;

    // This method handles the request for displaying the classes return the Classes.html and display all the classes in a table
    @RequestMapping("/displayClasses")
    public ModelAndView DisplayClasses(Model model) {
        List<ClassPojo> ClassList = classRepository.findAll();
        ModelAndView Kenclass = new ModelAndView("Classes.html");
        Kenclass.addObject("kenClasses", ClassList);
        Kenclass.addObject("kenClass", new ClassPojo());
        return Kenclass;
    }

    // This handles the request for handling adding class request in classes.html itself
    @PostMapping(value = "/addNewClass")
    public String AddClass(@ModelAttribute("kenClass") ClassPojo classPojo, Model model) {
        classRepository.save(classPojo);
//        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
//        return modelAndView;
        return "redirect:/admin/displayClasses";
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<ClassPojo> eazyClass = classRepository.findById(id);
        for (PersonPojo person : eazyClass.get().getPersons()) {
            person.setKenClass(null);
            personRepository.save(person);
        }
        classRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<ClassPojo> eazyClass = classRepository.findById(classId);
        modelAndView.addObject("eazyClass", eazyClass.get());
        modelAndView.addObject("person", new PersonPojo());
        session.setAttribute("eazyClass", eazyClass.get());
        if (error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") PersonPojo person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        ClassPojo eazyClass = (ClassPojo) session.getAttribute("eazyClass");
        PersonPojo personEntity = personRepository.getByemail(person.getEmail());
        if (personEntity == null || !(personEntity.getPersonId() > 0)) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + eazyClass.getClassId()
                    + "&error=true");
            return modelAndView;
        }
        personEntity.setKenClass(eazyClass);
        personRepository.save(personEntity);
        eazyClass.getPersons().add(personEntity);
        classRepository.save(eazyClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + eazyClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
        ClassPojo eazyClass = (ClassPojo) session.getAttribute("eazyClass");
        Optional<PersonPojo> person = personRepository.findById(personId);
        person.get().setKenClass(null);
        eazyClass.getPersons().remove(person.get());
        ClassPojo eazyClassSaved = classRepository.save(eazyClass);
        session.setAttribute("eazyClass", eazyClassSaved);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId=" + eazyClass.getClassId());
        return modelAndView;
    }
}
