package client.services.impl;

import client.models.Article;
import client.repositories.ArticleRepository;
import client.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> getById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article;
    }
}
