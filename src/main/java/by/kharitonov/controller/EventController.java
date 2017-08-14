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

import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    private EventService eventService;
    private UserService userService;

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

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public String listEvent(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("listEvent", this.eventService.listEvent());

        return "event";
    }

    @RequestMapping(value = "/event/add", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute("event") Event event) {
        if (event.getId() == 0) {
            this.eventService.addEvent(event);
        } else {
            this.eventService.updateEvent(event);
        }
        return "redirect:/event";
    }

    @RequestMapping("/event/remove/{id}")
    public String removeEvent(@PathVariable("id") int id) {
        this.eventService.removeEvent(id);

        return "redirect:/event";
    }

    @RequestMapping("/event/update/{id}")
    public String updateEvent(@PathVariable("id") int id, Model model) {
        model.addAttribute("event", this.eventService.getEventById(id));
        model.addAttribute("listEvent", this.eventService.listEvent());

        return "event";
    }

    @RequestMapping("eventsdata/{id}")
    public String eventData(@PathVariable("id") int id, Model model) {
        model.addAttribute("event", this.eventService.getEventById(id));
        return "eventsdata";
    }

    @RequestMapping("/myevent/remove/{id}")
    public String removeMyEvent(@ModelAttribute Event event, @PathVariable("id") int id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUsername(
                ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
        List<Event> myEventList = user.getEvents();
        myEventList.remove(eventService.getEventById(id));
        user.setEvents(myEventList);
        userService.updateUser(user);
        return "redirect:/";
    }

    @RequestMapping("/myevent/add/{id}")
    public String updateMyEvent(@ModelAttribute Event event, @PathVariable("id") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUsername(
                ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
        List<Event> myEventList = user.getEvents();
        myEventList.add(eventService.getEventById(id));
        user.setEvents(myEventList);
        userService.updateUser(user);
        return "redirect:/";
    }

}
