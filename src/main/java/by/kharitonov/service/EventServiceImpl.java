package by.kharitonov.service;

import by.kharitonov.dao.EventDao;
import by.kharitonov.model.Event;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private EventDao eventDao;

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    @Transactional
    public void addEvent(Event event) {
        this.eventDao.addEvent(event);
    }

    @Override
    @Transactional
    public void updateEvent(Event event) {
        this.eventDao.updateEvent(event);
    }

    @Override
    @Transactional
    public void removeEvent(int id) {
        this.eventDao.removeEvent(id);
    }

    @Override
    @Transactional
    public Event getEventById(int id) {
        return this.eventDao.getEventById(id);
    }

    @Override
    @Transactional
    public List<Event> listEvent() {
        return this.eventDao.listEvent();
    }


}
