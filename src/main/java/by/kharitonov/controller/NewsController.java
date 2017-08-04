package by.kharitonov.controller;


import by.kharitonov.model.News;
import by.kharitonov.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewsController {
    private NewsService newsService;

    @Autowired(required = true)
    @Qualifier(value = "newsService")
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String listNews(Model model) {
        model.addAttribute("news", new News());
        model.addAttribute("listNews", this.newsService.listNews());

        return "news";
    }

    @RequestMapping(value = "/news/add", method = RequestMethod.POST)
    public String addNews(@ModelAttribute("news") News news) {
        if (news.getId() == 0) {
            this.newsService.addNews(news);
        } else {
            this.newsService.updateNews(news);
        }
        return "redirect:/news";
    }

    @RequestMapping("/news/remove/{id}")
    public String removeNews(@PathVariable("id") int id){
        this.newsService.removeNews(id);

        return "redirect:/news";
    }

    @RequestMapping("/news/update/{id}")
    public String updateNews(@PathVariable("id") int id, Model model){
        model.addAttribute("news", this.newsService.getNewsById(id));
        model.addAttribute("listNews", this.newsService.listNews());

        return "news";
    }

    @RequestMapping("newsdata/{id}")
    public String newsData(@PathVariable("id") int id, Model model){
        model.addAttribute("news", this.newsService.getNewsById(id));

        return "newsdata";
    }

}
