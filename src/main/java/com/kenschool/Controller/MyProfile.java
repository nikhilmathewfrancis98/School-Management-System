package com.kenschool.Controller;

import com.kenschool.Model_POJOs.PersonPojo;
import com.kenschool.Model_POJOs.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Base64;


@Slf4j
@Controller
public class MyProfile {


    @RequestMapping("/displayProfile")
    public ModelAndView Myprofile(HttpSession httpSession) throws UnsupportedEncodingException {
        Profile pojoprofile = new Profile();
        PersonPojo person = (PersonPojo) httpSession.getAttribute("loggedInPerson");
        pojoprofile.setName(person.getName());
        pojoprofile.setEmail(person.getEmail());
        pojoprofile.setMobileNumber(person.getMobileNumber());
        if (person.getAddress() != null && person.getAddress().getAddressId() > 0) {
            log.info("Inside the if" + new MyProfile().getClass().toString());
            pojoprofile.setAddress1(person.getAddress().getAddress1());
            pojoprofile.setAddress2(person.getAddress().getAddress2());
            pojoprofile.setCity(person.getAddress().getCity());
            pojoprofile.setState(person.getAddress().getState());
            pojoprofile.setZipCode(person.getAddress().getZip_code());
//            byte[] decomImage=ImageUtil.decompressImage(person.getAddress().getProfile_pic());
            byte[] encodeBase64 = Base64.getEncoder().encode(person.getAddress().getProfilePic());
//            String base64Encoded = new String(encodeBase64, "UTF-8");
            pojoprofile.setProfilePic(encodeBase64);
        } else {
            pojoprofile.setAddress1(" Not available ");
            pojoprofile.setAddress2(" Not Available");
            pojoprofile.setZipCode(" Not Available");
            pojoprofile.setState(" Not Available");
            pojoprofile.setCity(" Not Available");
        }

        return new ModelAndView("profile.html").addObject("profile", pojoprofile);
    }
}
