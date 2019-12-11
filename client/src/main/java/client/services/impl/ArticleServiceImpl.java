package client.services.impl;

import db.entities.Article;
import client.services.ArticleService;
import db.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@EntityScan(basePackageClasses = Article.class)
@EnableJpaRepositories(basePackageClasses = ArticleRepository.class)
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
