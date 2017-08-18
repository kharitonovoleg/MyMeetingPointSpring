package by.kharitonov.controller;

import by.kharitonov.model.Event;
import by.kharitonov.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventlistcontroller")
public class EventRestController {

    private EventService eventService;

    @Autowired
    @Qualifier(value = "eventService")
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/event", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Event>> listEvent() {
        List<Event> list = eventService.listEvent();
        return new ResponseEntity<List<Event>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Event> eventById(@PathVariable("id") int id) {
        Event event = eventService.getEventById(id);
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }

    @RequestMapping(value = "/event/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Event> deleteEventById(@PathVariable("id") int id) {
        Event event = eventService.getEventById(id);
        if (event == null) {
            System.out.println("event " + id + " not found");
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }
        eventService.removeEvent(id);
        return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/event/{id}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Event> updateEventById(@PathVariable("id") int id, @RequestBody Event newEvent){
        Event event = eventService.getEventById(id);
        if (event == null) {
            System.out.println("event " + id + "not found");
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }
        event.setDate(newEvent.getDate());
        event.setEventName(newEvent.getEventName());
        event.setEventStartTime(newEvent.getEventStartTime());
        event.setMobilePhone(newEvent.getMobilePhone());
        eventService.updateEvent(event);
        return new ResponseEntity<Event>(HttpStatus.OK);
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        eventService.addEvent(event);
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }

}
