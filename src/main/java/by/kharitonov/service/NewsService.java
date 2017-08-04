package by.kharitonov.service;

import by.kharitonov.model.News;

import java.util.List;

public interface NewsService {

    void addNews(News news);

    void updateNews(News news);

    void removeNews(int id);

    News getNewsById(int id);

    List<News> listNews();
}
