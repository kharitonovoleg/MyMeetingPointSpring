package by.kharitonov.dao;

import by.kharitonov.model.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDaoImpl implements NewsDao {

    private static final Logger logger = LoggerFactory.getLogger(NewsDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addNews(News news) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(news);
        logger.info("News successfully saved. News details: " + news);
    }

    @Override
    public void updateNews(News news) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(news);
        logger.info("News successfully update. News details: " + news);

    }

    @Override
    public void removeNews(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        News news = (News) session.load(News.class, new Integer(id));

        if (news != null) {
            session.delete(news);
        }
        logger.info("News successfully removed. News details: " + news);
    }

    @Override
    public News getNewsById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        News news = (News) session.load(News.class, new Integer(id));
        logger.info("News successfully loaded. News details: " + news);

        return news;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<News> listNews() {
        Session session = this.sessionFactory.getCurrentSession();
        List<News> newsList = session.createQuery("from News").list();

        for (News news : newsList) {
            logger.info("News list: " + news);
        }
        return newsList;
    }

}
