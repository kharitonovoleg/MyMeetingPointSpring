package by.kharitonov.dao;

import by.kharitonov.model.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventDaoImpl implements EventDao{
    private static final Logger logger = LoggerFactory.getLogger(EventDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addEvent(Event event) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(event);
        session.getTransaction().commit();
        session.flush();
        session.close();
        logger.info("Event successfully saved. Event details: " + event);
    }

    @Override
    public void updateEvent(Event event) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Event event1 = (Event) session.get(Event.class, event.getId());
        event1.setEventName(event.getEventName());
        event1.setMobilePhone(event.getMobilePhone());
        event1.setEventStartTime(event.getEventStartTime());
        event1.setDate(event.getDate());
        session.saveOrUpdate(event1);
        session.getTransaction().commit();
        session.flush();
        session.close();
        logger.info("Event successfully update. Event details: " + event);

    }

    @Override
    public void removeEvent(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Event event = (Event) session.load(Event.class, new Integer(id));

        if (event != null) {
            session.delete(event);
        }
        logger.info("Event successfully removed. Event details: " + event);
    }

    @Override
    public Event getEventById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Event event = (Event) session.get(Event.class, new Integer(id));
        logger.info("Event successfully loaded. Event details: " + event);

        return event;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> listEvent() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Event> eventsList = session.createQuery("from Event").list();

        for (Event event : eventsList) {
            logger.info("Event list: " + event);
        }
        return eventsList;
    }

}