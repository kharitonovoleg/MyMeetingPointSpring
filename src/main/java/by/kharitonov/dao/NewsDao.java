package by.kharitonov.dao;

import by.kharitonov.model.News;

import java.util.List;

public interface NewsDao {

    void addNews(News news);

    void updateNews(News news);

    void removeNews(int id);

    News getNewsById(int id);

    List<News> listNews();
}
