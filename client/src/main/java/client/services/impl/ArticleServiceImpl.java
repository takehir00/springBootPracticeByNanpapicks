package client.services.impl;

import client.model.ArticleModel;
import client.model.CommentModel;
import client.model.UserModel;
import client.responses.articles.ArticleDetailResponse;
import client.responses.articles.ArticleIndexResponse;
import client.util.PageUtil;
import db.entities.Article;
import client.services.ArticleService;
import db.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public ArticleIndexResponse listing(int offset, int limit) {
        ArticleIndexResponse response =
                ArticleIndexResponse.builder()
                        .articleList(
                                articleRepository.findByOffsetAndLimit(offset, limit))
                        .pageCount(PageUtil
                                .calculatePageCount(articleRepository.countAll(), limit))
                        .build();
        return response;
    }

    @Override
    public Article getById(Long id) {
        Article article = articleRepository.findById(id);
        return article;
    }

    @Override
    public ArticleDetailResponse detail(Long articleId) {
        Article article = articleRepository.findById(articleId);
        if (article != null) {
            return ArticleDetailResponse.builder()
                    .articleModel(ArticleModel.builder()
                            .id(article.id)
                            .url(article.url)
                            .title(article.title)
                            .imageUrl(article.imageUrl)
                            .commentList(
                                    article.commentList
                                    .stream()
                                    .map(comment -> {
                                        return CommentModel.builder()
                                                .id(comment.id)
                                                .content(comment.content)
                                                .user(UserModel.builder()
                                                        .id(comment.user.id)
                                                        .name(comment.user.name)
                                                        .mail(comment.user.mail)
                                                        .introduction(comment.user.introduction)
                                                        .imageUrl(comment.user.imageUrl)
                                                        .build())
                                                .build();
                                            }).collect(Collectors.toList()))
                            .build())
                    .build();
        } else {
            // 取得したい記事のurl踏んだら取得したい記事がない状況って基本的にはあり得ない。
            // ここでは例外を投げてどっかでハンドリングしてサーバーエラー画面を返すのが良さそう
            return null;
        }
    }
}
