package by.kharitonov.controller;

import by.kharitonov.model.Event;
import by.kharitonov.model.User;
import by.kharitonov.service.EventService;
import by.kharitonov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private EventService eventService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "eventService")
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String listUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUser", this.userService.listUser());

        return "user";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            this.userService.addUser(user);
        } else {
            this.userService.updateUser(user);
        }
        return "redirect:/user";
    }

    @RequestMapping("/user/remove/{id}")
    public String removeUser(@PathVariable("id") int id){
        this.userService.removeUser(id);

        return "redirect:/user";
    }

    @RequestMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUser", this.userService.listUser());

        return "user";
    }

    @RequestMapping("usersdata/{id}")
    public String userData(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.getUserById(id));

        return "usersdata";
    }

    @RequestMapping(value = "/usereventlist")
    public String usereventlist(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("event", new Event());
        model.addAttribute("listEvent", this.eventService.listEvent());
        model.addAttribute("listUser", this.userService.listUser());
        return "usereventlist";
    }

    @RequestMapping("/adduserevent")
    public String adduserevent(@ModelAttribute Event event){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUsername(
                ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());

        eventService.addEvent(event);

        List<Event> list = user.getEvents();
        list.add(event);

        user.setEvents(list);

        userService.updateUser(user);
        return "redirect:/";
    }

}

