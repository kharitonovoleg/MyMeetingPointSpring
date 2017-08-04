package by.kharitonov.service;

import by.kharitonov.model.Event;

import java.util.List;

public interface EventService {

    void addEvent(Event event);

    void updateEvent(Event event);

    void removeEvent(int id);

    Event getEventById(int id);

    List<Event> listEvent();
}
