package com.kenschool.Controller;

import com.kenschool.Imagemanipulations.ImageUtil;
import com.kenschool.Model_POJOs.AddressPoJO;
import com.kenschool.Model_POJOs.PersonPojo;
import com.kenschool.Model_POJOs.Profile;
import com.kenschool.Repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;

@Controller
@Slf4j
public class EditProfile {

    @Autowired(required = false)
    PersonRepository personRepository;

    @RequestMapping(value = "/EditProfile")
    public ModelAndView EditProfile(Model model, HttpSession httpSession) {
        PersonPojo person = (PersonPojo) httpSession.getAttribute("loggedInPerson");
        Profile pojoprofile1 = new Profile();
        pojoprofile1.setName(person.getName());
        pojoprofile1.setEmail(person.getEmail());
        pojoprofile1.setMobileNumber(person.getMobileNumber());
        if (person.getAddress() != null && person.getAddress().getAddressId() > 0) {
            pojoprofile1.setAddress1(person.getAddress().getAddress1());
            pojoprofile1.setAddress2(person.getAddress().getAddress2());
            pojoprofile1.setCity(person.getAddress().getCity());
            pojoprofile1.setState(person.getAddress().getState());
            pojoprofile1.setZipCode(person.getAddress().getZip_code());
            pojoprofile1.setProfilePic(Base64.getEncoder().encode(person.getAddress().getProfilePic()));

//            pojoprofile1.setProfile_pic(person.getAddress().getProfile_pic());
        }
        ModelAndView editprofile = new ModelAndView("EditProfile.html");
        editprofile.addObject("editprofile", pojoprofile1);
        model.addAttribute("validateProfile", new Profile());
        return editprofile;
    }

    //
    @RequestMapping(value = "/updateProfile")
    public String updateProfile(@RequestParam("fileImage") MultipartFile file, @Valid @ModelAttribute("validateProfile") Profile profile, Errors errors,
                                HttpSession httpSession) throws IOException {
        if (errors.hasErrors()) {
            return "EditProfile.html";
        }
        PersonPojo person = (PersonPojo) httpSession.getAttribute("loggedInPerson");
        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());
        if (person.getAddress() == null || !(person.getAddress().getAddressId() > 0)) {
            person.setAddress(new AddressPoJO());
//            System.out.println("Address is null");
        }

        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZip_code(profile.getZipCode());
        person.getAddress().setProfilePic(ImageUtil.compressImage(file.getBytes()));
        personRepository.save(person);
        httpSession.setAttribute("loggedInPerson", person);
        return "redirect:/dashboard";
    }


}
