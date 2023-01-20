package com.kenschool.Error;

import com.kenschool.Model_POJOs.NewPOJOContactEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.kenschool.Model_POJOs.POJOContact;

@Slf4j
@Controller
public class Contact_ErrorController {
    private NewPOJOContactEntity pojoContact;


    @GetMapping("/errorCtrl")
    public String ContactError(Model model, @RequestParam(value = "var1") String name, @RequestParam(value = "var2") int number) {
        model.addAttribute("error", "Oops! Hey " + pojoContact.getName() + " your message was not send!");
        log.info(name + "\t" + number);
        return "Contact_Error.html";

    }

    public void setAttributes(NewPOJOContactEntity pojoContact) {
        this.pojoContact = pojoContact;
    }

}
