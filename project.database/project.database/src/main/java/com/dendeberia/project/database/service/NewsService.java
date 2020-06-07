package com.dendeberia.project.database.service;

import com.dendeberia.project.database.entity.News;
import com.dendeberia.project.database.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public List<News> getNews(){
        return newsRepository.findAll();
    }

    public void save(News news){
        newsRepository.save(news);
    }

    public News getOne(Long id){
        return newsRepository.getOne(id);
    }

    public void delete(Long id){
        newsRepository.deleteById(id);
    }
}
