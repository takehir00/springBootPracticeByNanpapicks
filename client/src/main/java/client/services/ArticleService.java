package client.services;

import client.responses.articles.ArticleDetailResponse;
import db.entities.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    /**
     * 記事一覧取得
     *
     * @return 記事一覧
     */
    public List<Article> getAll();

    /**
     * idで記事取得
     *
     * @param id
     * @return 記事
     */
    Optional<Article> getById(Long id);

    /**
     * 記事詳細画面のレスポンスを生成する
     *
     * @param articleId
     * @return
     */
    ArticleDetailResponse detail(Long articleId);
}
