package by.kharitonov.controller;

import by.kharitonov.model.Event;
import by.kharitonov.model.User;
import by.kharitonov.service.EventService;
import by.kharitonov.service.SecurityServiceImpl;
import by.kharitonov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GeneralController {


    private UserService userService;
    private EventService eventService;

    @Autowired(required = true)
    @Qualifier(value = "eventService")
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    private SecurityServiceImpl securityServiceImpl;

    @Autowired
    @Qualifier("userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUsername(
                ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
        model.addAttribute("user", user);
        model.addAttribute("event", new Event());
        model.addAttribute("eventList", user.getEvents());
        model.addAttribute("listEvent", this.eventService.listEvent());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.addUser(userForm);

        securityServiceImpl.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/login";
    }
}
