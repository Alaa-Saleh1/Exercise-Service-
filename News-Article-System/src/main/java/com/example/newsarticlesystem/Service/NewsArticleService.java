package com.example.newsarticlesystem.Service;

import com.example.newsarticlesystem.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsArticleService {
    ArrayList<NewsArticle> newsArticles=new ArrayList<>();


    public ArrayList<NewsArticle> getNews(){
        return newsArticles;
    }

    public void addNews(NewsArticle newsArticle){
        newsArticles.add(newsArticle);
    }

    public boolean updateNews(String id, NewsArticle newsArticle){
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equalsIgnoreCase(id)){
                newsArticles.set(i,newsArticle);
                return true;
            }
        }
        return false;
    }

    public boolean deleteNews(String id){
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equalsIgnoreCase(id)){
                newsArticles.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updatePublish(String id){
        for (int i = 0; i < newsArticles.size(); i++) {
            if(newsArticles.get(i).getId().equalsIgnoreCase(id)){
                    newsArticles.get(i).setPublished(true);
                    return true;
            }

        }
        return false;
    }

    public ArrayList<NewsArticle> allPublish(){
        ArrayList<NewsArticle> allPublished=new ArrayList<>();
        for(NewsArticle news : newsArticles){
            if(news.isPublished()){
                allPublished.add(news);
            }
        }
        return allPublished;
    }

    public ArrayList<NewsArticle> searchByCategory(String category){
        ArrayList<NewsArticle> politicsNews = new ArrayList<>();
        ArrayList<NewsArticle> sportNews = new ArrayList<>();
        ArrayList<NewsArticle> tecNews = new ArrayList<>();

        for (NewsArticle news : newsArticles ){
            if(news.getCategory().equalsIgnoreCase("politics")){
                politicsNews.add(news);
            }
            if (news.getCategory().equalsIgnoreCase("sports")){
                sportNews.add(news);
            }
            if (news.getCategory().equalsIgnoreCase("technology")){
                tecNews.add(news);
            }
        }
        if (category.equalsIgnoreCase("politics")){
            return politicsNews;
        }
        if (category.equalsIgnoreCase("sports")){
            return sportNews;
        }
        if (category.equalsIgnoreCase("technology")){
            return tecNews;
        }
        return null;
    }
}
