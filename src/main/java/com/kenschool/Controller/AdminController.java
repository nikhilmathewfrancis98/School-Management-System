package com.kenschool.Controller;

import com.kenschool.Model_POJOs.ClassPojo;
import com.kenschool.Model_POJOs.Courses;
import com.kenschool.Model_POJOs.PersonPojo;
import com.kenschool.Repository.ClassRepository;
import com.kenschool.Repository.CourseRepository;
import com.kenschool.Repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// Main controller class for the admin configurations
@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController  {
    @Autowired
    ClassRepository classRepository;

    @Autowired(required = false)
    PersonRepository personRepository;

    @Autowired(required = false)
    CourseRepository courseRepository;

    // This method handles the request for displaying the classes return the Classes.html and display all the classes in a table
    @RequestMapping("/displayClasses")
    public ModelAndView DisplayClasses(Model model) {
//        List<ClassPojo> ClassList = classRepository.findAll();
        ModelAndView Kenclass = new ModelAndView("Classes.html");
        List<ClassPojo> classes=classRepository.findAll();
        Kenclass.addObject("kenClasses", classes);
 //       Since am not using the @Model attribute to the below req controller i commented
//        Also i edited the Classes.html th:filed for the form that appears
//        Kenclass.addObject("kenClass", new ClassPojo()); // Here i can use the model.addAttribute also instead of addObject
        return Kenclass;
    }

    // This handles the request for handling adding class request in classes.html itself
    @PostMapping(value = "/addNewClass")
//    Here i can use the @ModelAttribute to get the class name and bind it in the field
//    Since we can use the addObject of ModelAndView and addAttribute of model to get the values in the View both posses the instance
    public String AddClass(ClassPojo classPojo, Model model) {
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
        ModelAndView modelAndView = new ModelAndView("Student.html");
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

//    This is the Configuration for the courses enhancement
    @GetMapping(value = "/displayCourses")
    public ModelAndView DisplayCourses(Model model){
        ModelAndView courses=new ModelAndView("courses_secure.html");
//        List<Courses> list_courses=courseRepository.findAll();// Simply displaying the names of the course
//        List<Courses> list_courses=courseRepository.findByOrderByNameDesc();// Displaying the name in descending order (Sorting)
//        List<Courses> list_courses=courseRepository.findByOrderByName();// Display name in Ascending order
//        Using the Sort Class for the dynamic sorting using JPA
        Sort sort= Sort.by("name").descending(); //This can be directly given in the below code
        List<Courses> list_courses=courseRepository.findAll(sort);

        courses.addObject("courses",list_courses);
        courses.addObject("course",new Courses());

        return courses;
    }
    @PostMapping(value = "/addNewCourse")
    public ModelAndView AddCourses(@ModelAttribute("course") Courses courses,Model model){
        ModelAndView modelAndView=new ModelAndView();
        courseRepository.save(courses);
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }
    @GetMapping(value = "/viewStudents")
    public ModelAndView ViewStudents(@RequestParam("id") int courseid,@RequestParam(required = false) String error,HttpSession httpSession){
        ModelAndView modelAndView=new ModelAndView("course_student.html");
        Optional<Courses>Course=courseRepository.findById(courseid);
        httpSession.setAttribute("currentCourse",Course.get());
        modelAndView.addObject("courses",Course.get());
        modelAndView.addObject("person",new PersonPojo());
        if(error!=null){
            modelAndView.addObject("errorMessage","Invalid Email address");
        }
        return modelAndView;
    }
        @PostMapping(value = "/addStudentToCourse")
        public ModelAndView AddStudentToCourse(@ModelAttribute("person") PersonPojo person,HttpSession httpSession){
        ModelAndView modelAndView=new ModelAndView();
        Courses course=(Courses) httpSession.getAttribute("currentCourse");
       PersonPojo PersonEntity= personRepository.getByemail(person.getEmail());
        if(PersonEntity==null||!(PersonEntity.getPersonId()>0)){
            modelAndView.setViewName("redirect:/admin/viewStudents?id="+course.getCourseId()+"&error=true");
            return modelAndView;
        }
        PersonEntity.getCourses().add(course);
        course.getPersons().add(PersonEntity);
        personRepository.save(PersonEntity);
//        courseRepository.save(course);
        httpSession.setAttribute("currentCourse",course);
    modelAndView.setViewName("redirect:/admin/viewStudents?id="+course.getCourseId());
    return modelAndView;
        }

        @RequestMapping(value = "/deleteStudentFromCourse")
        public ModelAndView DeleteStudentFromCourse(@RequestParam int personId,Model model,HttpSession httpSession){
        Optional<PersonPojo> person=personRepository.findById(personId);
       Courses course= (Courses) httpSession.getAttribute("currentCourse");
            person.get().getCourses().remove(course);
       course.getPersons().remove(person);
//       Courses courses=courseRepository.save(course); // with this only the code will work
            personRepository.save(person.get());
       httpSession.setAttribute("currentCourse",course);

        ModelAndView modelAndView=new ModelAndView("redirect:/admin/viewStudents?id="+course.getCourseId());
            return modelAndView;
        }
}
