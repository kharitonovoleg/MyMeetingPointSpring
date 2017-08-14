package by;

import by.kharitonov.model.Event;
import by.kharitonov.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {

            session.beginTransaction();

//            Event event = (Event) session.get(Event.class, 1);
            User user = (User) session.get(User.class, 6);
            Event event = new Event();
            event.setEventName("test");
            event.setMobilePhone("test");
            List<Event> list = user.getEvents();
            list.add(event);
            user.setEvents(list);

            session.save(event);
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            session.flush();

        } finally {
            session.close();
        }
    }
}