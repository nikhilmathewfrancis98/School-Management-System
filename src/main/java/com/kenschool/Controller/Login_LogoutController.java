package com.kenschool.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class Login_LogoutController {
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView LoginController(@RequestParam(value = "error", required = false) String error, @RequestParam
            (value = "logout", required = false) String logout, @RequestParam(value = "register", required = false) String regs, Model model) {
        ModelAndView modelAndView = new ModelAndView("login.html");
        String Login_error_Messge = null;
        String Logout_Success = null;
        String registerSuccess = null;
        if (error != null) {
            Login_error_Messge = "Either Username or Password is incorrect !!";
            model.addAttribute("errorMessge", Login_error_Messge);
        } else if (logout != null) {
            Logout_Success = "You have been successfully logged out !!";
            model.addAttribute("successmsg", Logout_Success);

        } else if (regs != null) {
            registerSuccess = "You Registered Successfully. Login with Credentials !!";
            model.addAttribute("regisSuccess", registerSuccess);

        }

        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";

    }
}
