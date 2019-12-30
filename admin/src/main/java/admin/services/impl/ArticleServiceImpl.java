package admin.services.impl;


import admin.forms.article.ArticleForm;
import admin.forms.article.ArticleRegisterForm;
import admin.forms.article.ArticleUpdateForm;
import db.entities.Article;
import db.repositories.ArticleRepository;
import admin.services.ArticleService;
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
    public void create(ArticleRegisterForm articleForm) {
        Article article = new Article();
        article.url = articleForm.url;
        article.title = articleForm.title;
        article.imageUrl = articleForm.imageUrl;
        articleRepository.save(article);
    }

    @Override
    public Optional<Article> getById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public ArticleUpdateForm updateForm(Long articleId) {
        Optional<Article> articleOpt = articleRepository.findById(articleId);
        if (articleOpt.isPresent()) {
            Article article = articleOpt.get();
            return ArticleUpdateForm.builder()
                    .id(article.id)
                    .url(article.url)
                    .title(article.title)
                    .imageUrl(article.imageUrl)
                    .build();
        }
        return null;
    }

    @Override
    public void update(ArticleUpdateForm articleForm) {
        Article article = new Article();
        article.id = articleForm.id;
        article.url = articleForm.url;
        article.title = articleForm.title;
        article.imageUrl = articleForm.imageUrl;
        articleRepository.saveAndFlush(article);
    }

    @Override
    public void delete(ArticleForm articleForm) {
        Optional<Article> articleOpt = articleRepository.findById(articleForm.id);
        articleOpt.ifPresent(article -> {
            articleRepository.delete(articleOpt.get());
        });
    }


}
