package com.kenschool.Controller;

import com.kenschool.Error.Contact_ErrorController;
import com.kenschool.Model_POJOs.NewPOJOContactEntity;
import com.kenschool.Repository.NewContactRepository;
import com.kenschool.Services.NewContactServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

//import com.kenschool.Model_POJOs.POJOContact;
//import com.kenschool.Services.ContactServices;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class ContactController extends Thread {

    @Autowired(required = false)
    private NewPOJOContactEntity pojoContact;
    @Autowired(required = false)
    @Qualifier("ServicesContact")
    private NewContactServices contactServices;
    @Autowired(required = false)
    private Contact_ErrorController contact_errorController;


    // Constructor Wiring is done
//    @Autowired
//    public void ContactController(ContactServices srv){contactServices=srv;}
//    private Logger log = Logger.getLogger(ContactController.class.getName()); // Since we are using @Slf4j of lombok we don't need this
    @GetMapping("/contact")
    public String displayContacts(Model model) {
        model.addAttribute("contact", pojoContact);
        return "contact.html";
    }

//    @RequestMapping(value = "/saveMsg", method= RequestMethod.POST)
////    @PostMapping("/saveMsg")
//    //here there are more variables inside the parameter list do we are trying to avoid this using the POJO classes
//    public ModelAndView SaveContactFeedback(@RequestParam(name = "name") String Username,@RequestParam String mobileNum,
//                                            @RequestParam(name = "email") String Uemail,@RequestParam String subject,
//                                            @RequestParam String message){
//
//        log.info("Name : " + Username);
//        log.info("Mobile Number : " + mobileNum);
//        log.info("Email Address : " + Uemail);
//        log.info("Subject : " + subject);
//        log.info("Message : " + message);
//        return new ModelAndView("redirect:/contact");
//    }
    // Giving the POJO so code rewritten as
//    @PostMapping("/saveMsg")
//    public ModelAndView SaveContactFeedback(POJOContact pojoContact){
////        log.info(pojoContact.toString());
//       if(contactServices.SaveMessageContacts(pojoContact)){
//           return new ModelAndView("redirect:/contact");
//       }else {
////            redirectAttributes.addFlashAttribute("error", "Oops! Hey "+pojoContact.getName()+" your message was not send!");
//           contact_errorController.setAttributes(pojoContact);
//
//           return new ModelAndView("redirect:/errorCtrl?var1=Nikhil&var2=123");
//       }
//    } // We commented this because we are trying to use the @Valid and leveraging the POJO with the  view

    // If we give @RequestMapping we need to specify the requestmethod.POST or GET
//    Instead of Errors we can also use BindingResult object also
    @PostMapping(value = "/saveMsg")
    public String SaveContactFeedback(@Valid @ModelAttribute("contact") NewPOJOContactEntity contacts, Errors errors) {
//        log.info(pojoContact.toString());
//        log.info(String.valueOf(contact.hashCode()+"\n"+pojoContact.hashCode()));
        if (errors.hasErrors()) {
            log.info("Contact form validation failed due to : " + errors.toString());
            return "contact.html";
        } else {
            //Now when we enter details 2 times the count will be 2 ie same instance of service class is used
            // But with the the @RequestScope when ever new request comes new bean will be created
            contactServices.SaveMessageContacts(contacts);
//           contactServices.setCounter(contactServices.getCounter()+1);
//            log.info("Number of times the Contact form is submitted : "+contactServices.getCounter());
//           log.info("No Errors");
            return "redirect:/contact";
        }
    }

    @RequestMapping(value = "/displayMessages/page/{page_no}")
    public ModelAndView DisplayMessages(@PathVariable("page_no") int pageNo,@RequestParam("sortField") String SortField,
                                        @RequestParam("sortDir") String SortDir,Model model) {
        ModelAndView modelAndView=new ModelAndView("Messages.html");

        Page<NewPOJOContactEntity> contactMsgs = contactServices.findOpenMessages(pageNo,SortField,SortDir);
        List<NewPOJOContactEntity> contactPages=contactMsgs.getContent();
        modelAndView.addObject("contactMsgs", contactPages);
        model.addAttribute("totalPages", contactMsgs.getTotalPages());
        model.addAttribute("totalMsgs", contactMsgs.getTotalElements());
        model.addAttribute("sortField",SortField);
        model.addAttribute("sortDir",SortDir);
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("reverseSortDir",(SortDir.equals("desc")) ? "asc" :   "desc");
        return modelAndView;
    }

    @RequestMapping(value = "/closeMsg/{id}", method = RequestMethod.GET)
    public String UpdateMessage(@PathVariable(name = "id") int ContactId) {
//        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
//        log.info(String.valueOf(ContactId));
        contactServices.UpdateMessageStatus(ContactId);
//        return "redirect:/displayMessages/page/' + ${currentPage}+'?sortField=' + ${sortField} + '&sortDir=' + ${sortDir}";
        return "redirect:/displayMessages/page/1?sortField=name&sortDir=desc";
    }
}
//  return new ModelAndView("redirect:/errorCtrl?var1=pojoContact&var2=123"); // Tried this query string by sending the query params we cannot send the object reference through query params