package com.nanpa.nanpa_picks.service.impl;

import com.nanpa.nanpa_picks.form.ArticleForm;
import com.nanpa.nanpa_picks.model.Article;
import com.nanpa.nanpa_picks.repositries.ArticleRepository;
import com.nanpa.nanpa_picks.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public void create(ArticleForm articleform) {
        Article article = new Article();
        article.url = articleform.url;
        article.title = articleform.title;
        article.imageUrl = articleform.imageUrl;
        articleRepository.save(article);
    }
}
