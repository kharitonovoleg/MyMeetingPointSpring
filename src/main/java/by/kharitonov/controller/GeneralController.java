package by.kharitonov.controller;

import by.kharitonov.model.User;
import by.kharitonov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GeneralController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUsername(
                ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }


}
